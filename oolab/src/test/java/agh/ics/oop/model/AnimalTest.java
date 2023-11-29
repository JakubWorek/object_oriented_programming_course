package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

public class AnimalTest {
    @Test
    void testToString() {
        Animal animal = new Animal();
        assert animal.toString().equals("N");
    }

    @Test
    void testGetPosition() {
        Animal animal = new Animal();
        assert animal.getPosition().equals(new Vector2d(2, 2));
    }

    @Test
    void testGetDirection() {
        Animal animal = new Animal();
        assert animal.getDirection().equals(MapDirection.NORTH);
    }

    @Test
    void testIsAt() {
        Animal animal = new Animal();
        assert animal.isAt(new Vector2d(2, 2));
    }
}
