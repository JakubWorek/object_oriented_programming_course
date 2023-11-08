package agh.ics.oop;

import agh.ics.oop.Simulation;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationTest {

    @Test
    public void testSimulationRunWithValidDirections() {
        List<MoveDirection> directions = new ArrayList<>();
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);

        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2, 2));
        positions.add(new Vector2d(2, 2));

        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();

        assertEquals(animals.size(), 2);

        for (Animal animal : animals) {
            assertTrue(animal.getDirection() == MapDirection.EAST);
            assertTrue(animal.getCoordinates().follows(new Vector2d(0, 0)));
            assertTrue(animal.getCoordinates().precedes(new Vector2d(4, 4)));
        }
    }

    @Test
    public void testSimulationRunWithInvalidDirections() {
        List<MoveDirection> directions = new ArrayList<>();
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.BACKWARD);

        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(0, 0));

        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();

        assertEquals(animals.size(), 1);

        Animal animal = animals.get(0);
        assertTrue(animal.getDirection() == MapDirection.EAST);
        assertTrue(animal.getCoordinates().equals(new Vector2d(0, 1)));
    }

    @Test
    public void testAnimalMove() {
        Animal animal = new Animal(MapDirection.NORTH, new Vector2d(2, 2));

        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.getDirection() == MapDirection.NORTH);
        assertTrue(animal.getCoordinates().equals(new Vector2d(2, 3)));

        animal.move(MoveDirection.RIGHT);
        assertTrue(animal.getDirection() == MapDirection.EAST);
        assertTrue(animal.getCoordinates().equals(new Vector2d(2, 3)));

        animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.getDirection() == MapDirection.EAST);
        assertTrue(animal.getCoordinates().equals(new Vector2d(1, 3)));
    }

    @Test
    public void testAnimalMoveWithInvalidDirections() {
        Animal animal = new Animal(MapDirection.NORTH, new Vector2d(0, 0));

        animal.move(MoveDirection.LEFT);
        assertTrue(animal.getDirection() == MapDirection.WEST);
        assertTrue(animal.getCoordinates().equals(new Vector2d(0, 0)));

        animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.getDirection() == MapDirection.WEST);
        assertTrue(animal.getCoordinates().equals(new Vector2d(1, 0)));

        animal.move(MoveDirection.RIGHT);
        assertTrue(animal.getDirection() == MapDirection.NORTH);
        assertTrue(animal.getCoordinates().equals(new Vector2d(1, 0)));

        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.getDirection() == MapDirection.NORTH);
        assertTrue(animal.getCoordinates().equals(new Vector2d(1, 1)));
    }

    @Test
    public void testIfAnimalCanMoveOutOfBoundaries(){
        Animal animal = new Animal(MapDirection.WEST, new Vector2d(0, 0));

        // left boundary
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.getCoordinates().equals(new Vector2d(0, 0)));

        // bottom boundary
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.getCoordinates().equals(new Vector2d(0, 0)));

        // right boundary
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.getCoordinates().equals(new Vector2d(4, 0)));

        // top boundary
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.getCoordinates().equals(new Vector2d(4, 4)));
    }
}