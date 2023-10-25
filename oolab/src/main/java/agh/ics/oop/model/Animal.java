package agh.ics.oop.model;

import agh.ics.oop.MapDirection;
import agh.ics.oop.Vector2d;

import java.util.function.Function;

public class Animal {
    private MapDirection direction;
    private Vector2d coordinates;

    public Animal(){
        this.direction = MapDirection.NORTH;
        this.coordinates = new Vector2d(2,2);
    }

    public Animal(MapDirection direction, Vector2d coordinates){
        this.direction = direction;
        this.coordinates = coordinates;
    }

    public Vector2d getCoordinates() {
        return this.coordinates;
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    public String toString(){
        //return "Zwierze znajduje sie na wspolrzednych: " + this.coordinates.toString() + " w kierunku: " + this.direction.toString();
        return this.direction.toString();
    }

    public boolean isAt(Vector2d position){
        return this.coordinates.equals(position);
    }

    public void move(MoveDirection direction, Function<Vector2d, Boolean> canMoveTo){
        Vector2d potentialNewPosition;

        switch (direction){
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case FORWARD:
                potentialNewPosition = this.coordinates.add(this.direction.toUnitVector());
                if (canMoveTo.apply(potentialNewPosition)) {
                    this.coordinates = potentialNewPosition;
                }
                break;
            case BACKWARD:
                potentialNewPosition = this.coordinates.subtract(this.direction.toUnitVector());
                if (canMoveTo.apply(potentialNewPosition)) {
                    this.coordinates = potentialNewPosition;
                }
                break;
            default:
                break;
        }
    }
}
