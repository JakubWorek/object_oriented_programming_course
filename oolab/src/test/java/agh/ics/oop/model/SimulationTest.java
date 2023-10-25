package agh.ics.oop.model;

import agh.ics.oop.MapDirection;
import agh.ics.oop.Vector2d;
import agh.ics.oop.Simulation;
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
}