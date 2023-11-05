package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.*;

public class GrassField implements WorldMap{
    private final int grassNumber;
    private final int grassMaxSpawn;
    protected Vector2d lowerLeft = new Vector2d(0, 0);
    protected Vector2d upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private final MapVisualizer visualizer;
    private final Map<Vector2d, Animal> animals;
    private final Map<Vector2d, Grass> grasses;
    public GrassField(int grassNumber, List<Vector2d> positions) {
        this.grassNumber = grassNumber;
        this.grassMaxSpawn = (int) Math.sqrt(10 * this.grassNumber);
        this.animals = new HashMap<>();
        this.grasses = new HashMap<>();
        this.visualizer = new MapVisualizer(this);
        Random random = new Random();

        int i=0;
        while (i <= this.grassNumber) {
            int x = random.nextInt(this.grassMaxSpawn);
            int y = random.nextInt(this.grassMaxSpawn);

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrassField that = (GrassField) o;
        return grassNumber == that.grassNumber &&
                grassMaxSpawn == that.grassMaxSpawn &&
                lowerLeft.equals(that.lowerLeft) &&
                upperRight.equals(that.upperRight) &&
                visualizer.equals(that.visualizer) &&
                animals.equals(that.animals) &&
                grasses.equals(that.grasses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grassNumber, grassMaxSpawn, lowerLeft, upperRight, visualizer, animals, grasses);
    }

    public void RespawnGrass(Vector2d position) {
        grasses.remove(position);
        int[][] epsilon = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1,-1}, {1, 0}, {1, 1}};
        for (int[] e: epsilon) {
            Vector2d newPosition = position.add(new Vector2d(e[0], e[1]));
            if (!(isOccupied(newPosition))) {
                grasses.put(newPosition, new Grass(newPosition));
                break;
            }
        }
    }
}
