package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap{
    private final int width;
    private final int height;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final Map<Vector2d, Animal> animals;
    private final MapVisualizer visualizer;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(this.width-1, this.height-1);
        this.animals = new HashMap<>();
        this.visualizer = new MapVisualizer(this);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeft) && position.precedes(upperRight) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getCoordinates())) {
            animals.put(animal.getCoordinates(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getCoordinates();
        animal.move(direction, this::canMoveTo);
        animals.remove(oldPosition);
        animals.put(animal.getCoordinates(), animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    public String toString() {
        return visualizer.draw(lowerLeft, upperRight);
    }
}
