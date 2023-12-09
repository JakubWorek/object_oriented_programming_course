package agh.ics.oop.model;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    public void testIfMapWorks(){
        WorldMap map = new GrassField(10);
        Animal animal = new Animal();
        try {
            assertTrue(map.place(animal));
        } catch (PositionAlreadyOccupiedException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
    }

    @Test
    public void testCanMoveTo1(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        try {
            assertTrue(map.place(animal1));
        } catch (PositionAlreadyOccupiedException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertTrue(map.canMoveTo(new Vector2d(2, 3)));
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    public void testCanMoveTo2(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(MapDirection.SOUTH, new Vector2d(2, 3));
        try {
            assertTrue(map.place(animal1));
        } catch (PositionAlreadyOccupiedException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            assertTrue(map.place(animal2));
        } catch (PositionAlreadyOccupiedException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertFalse(map.canMoveTo(new Vector2d(2, 3)));
    }

    @Test
    public void testPlace() {
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(MapDirection.SOUTH, new Vector2d(2, 2));

        try {
            assertTrue(map.place(animal1));
        } catch (PositionAlreadyOccupiedException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        assertThrows(PositionAlreadyOccupiedException.class, () -> map.place(animal2));
    }

    @Test
    public void testMove(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(MapDirection.SOUTH, new Vector2d(3, 2));
        try {
            assertTrue(map.place(animal1));
        } catch (PositionAlreadyOccupiedException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            assertTrue(map.place(animal2));
        } catch (PositionAlreadyOccupiedException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        map.move(animal1, MoveDirection.FORWARD);
        map.move(animal2, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal1.getPosition());
        assertEquals(new Vector2d(3, 1), animal2.getPosition());
    }

    @Test
    public void testIsOccupied(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(MapDirection.SOUTH, new Vector2d(2, 2));
        try {
            assertTrue(map.place(animal1));
        } catch (PositionAlreadyOccupiedException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertThrows(PositionAlreadyOccupiedException.class, () -> map.place(animal2));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    public void testIsOccupiedPass(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(MapDirection.SOUTH, new Vector2d(2, 3));
        try {
            assertTrue(map.place(animal1));
        } catch (PositionAlreadyOccupiedException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            assertTrue(map.place(animal2));
        } catch (PositionAlreadyOccupiedException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertFalse(map.isOccupied(new Vector2d(2, 4)));
    }

    @Test
    public void testObjectAt(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(MapDirection.SOUTH, new Vector2d(2, 2));
        try {
            assertTrue(map.place(animal1));
        } catch (PositionAlreadyOccupiedException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertThrows(PositionAlreadyOccupiedException.class, () -> map.place(animal2));
        assertEquals(animal1, map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    public void testGetElements(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(MapDirection.SOUTH, new Vector2d(2, 3));
        try {
            assertTrue(map.place(animal1));
        } catch (PositionAlreadyOccupiedException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            assertTrue(map.place(animal2));
        } catch (PositionAlreadyOccupiedException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertEquals(12, map.getElements().size());
    }
}
