package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class GrassField implements WorldMap{
    private final int grassNumber;
    protected Vector2d lowerLeft = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    protected Vector2d upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private final MapVisualizer visualizer;
    private final Map<Vector2d, Animal> animals;
    private final Map<Vector2d, Grass> grasses;
    public GrassField(int grassNumber, List<Vector2d> positions) {
        this.grassNumber = grassNumber;
        this.animals = new HashMap<>();
        this.grasses = new HashMap<>();
        this.visualizer = new MapVisualizer(this);

        Random random = new Random();
        int i=0;
        while (i <= this.grassNumber) {
            int x = random.nextInt((int) Math.sqrt(10 * this.grassNumber));
            int y = random.nextInt((int) Math.sqrt(10 * this.grassNumber));

            boolean flag = false;
            for (Vector2d position: positions) {
                if (Objects.equals(position, new Vector2d(x, y))) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                this.grasses.put(new Vector2d(x,y), new Grass(new Vector2d(x, y)));
                i++;
            }
        }
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeft)  && !(objectAt(position) instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);
        animals.remove(oldPosition);
        animals.put(animal.getPosition(), animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if(animals.get(position) != null) return animals.get(position);
        if(grasses.get(position) != null) return grasses.get(position);
        return null;
    }

    @Override
    public String toString() {
        Vector2d bottom = new Vector2d(upperRight.x, upperRight.y);
        Vector2d top = new Vector2d(lowerLeft.x, lowerLeft.y);
        for (WorldElement animal: animals.values()) {
            bottom = bottom.lowerLeft(animal.getPosition());
            top = top.upperRight(animal.getPosition());
        }
        for (WorldElement grass: grasses.values()) {
            bottom = bottom.lowerLeft(grass.getPosition());
            top = top.upperRight(grass.getPosition());
        }
        return visualizer.draw(bottom, top);
    }
}
