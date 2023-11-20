package agh.ics.oop.model;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    @Test
    public void testIfMapWorks(){
        WorldMap map = new RectangularMap(10, 5);
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
        WorldMap map = new RectangularMap(10, 5);
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
        WorldMap map = new RectangularMap(10, 5);
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
    public void testPlace(){
        WorldMap map = new RectangularMap(10, 5);
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
        WorldMap map = new RectangularMap(10, 5);
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
        WorldMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(MapDirection.SOUTH, new Vector2d(2, 2));
        try {
            assertTrue(map.place(animal1));
        } catch (PositionAlreadyOccupiedException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertThrows(PositionAlreadyOccupiedException.class, () -> map.place(animal2));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertFalse(map.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    public void testObjectAt(){
        WorldMap map = new RectangularMap(10, 5);
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
        assertEquals(animal1, map.objectAt(new Vector2d(2, 2)));
        assertEquals(animal2, map.objectAt(new Vector2d(2, 3)));
    }

    @Test
    public void testGetElements(){
        WorldMap map = new RectangularMap(10, 5);
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
        assertEquals(2, map.getElements().size());
    }
}
