package agh.ics.oop.model;
import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    Vector2d v = new Vector2d(1,1);
    Vector2d u = new Vector2d(-1, 2);
    Vector2d w = new Vector2d(2, 2);

    @Test
    void testToString() {
        assertEquals("(1,1)",v.toString());
    }

    @Test
    void precedes() {
        assertFalse(v.precedes(u));
        assertTrue(v.precedes(w));
    }

    @Test
    void follows() {
        assertFalse(v.follows(u));
        assertTrue(w.follows(v));
    }

    @Test
    void upperRight() {
        assertEquals(new Vector2d(1,2), v.upperRight(u));
    }

    @Test
    void lowerLeft() {
        assertEquals(new Vector2d(-1,1), v.lowerLeft(u));
    }

    @Test
    void add() {
        assertEquals(new Vector2d(0,3), v.add(u));
    }

    @Test
    void subtract() {
        assertEquals(new Vector2d(2,-1), v.subtract(u));
    }

    @Test
    void testEquals() {
        assertFalse(v.equals(u));
        assertTrue(v.equals(v));
    }
    
    @Test
    void opposite() {
        assertEquals(new Vector2d(-1,-1), v.opposite());
    }
}
