package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

public class GrassTest {
    @Test
    void testToString() {
        Grass grass = new Grass(new Vector2d(2, 2));
        assert grass.toString().equals("*");
    }

    @Test
    void testGetPosition() {
        Grass grass = new Grass(new Vector2d(2, 2));
        assert grass.getPosition().equals(new Vector2d(2, 2));
    }
}
