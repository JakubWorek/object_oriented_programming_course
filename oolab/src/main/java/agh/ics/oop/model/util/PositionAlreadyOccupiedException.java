package agh.ics.oop.model.util;

import agh.ics.oop.model.Vector2d;

public class PositionAlreadyOccupiedException extends Exception {
    private final Vector2d position;

    public PositionAlreadyOccupiedException(Vector2d position) {
        super("Position " + position + " is already occupied.");
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }
}
