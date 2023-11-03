package agh.ics.oop;

import agh.ics.oop.model.*;
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
        RectangularMap map = new RectangularMap(5,5);

        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();

        assertEquals(1, animals.size());

        for (Animal animal : animals) {
            assertTrue(animal.getDirection() == MapDirection.SOUTH);
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
        RectangularMap map = new RectangularMap(5,5);

        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();

        assertEquals(1, animals.size());

        Animal animal = animals.get(0);
        assertTrue(animal.getDirection() == MapDirection.EAST);
        assertTrue(animal.getCoordinates().equals(new Vector2d(0, 1)));
    }

    @Test
    public void testSimulationRunWithTwoAnimalsCrossing(){
        List<MoveDirection> directions = new ArrayList<>();
        // f b r l f f r r f f f f f f f f
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.BACKWARD);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.LEFT);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);

        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));

        RectangularMap map = new RectangularMap(5,5);

        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertEquals(2, animals.size());

        for (Animal animal : animals) {
            assertTrue(animal.getCoordinates().follows(new Vector2d(0, 0)));
            assertTrue(animal.getCoordinates().precedes(new Vector2d(4, 4)));
        }

        Animal animal = animals.get(0);
        assertTrue(animal.getDirection() == MapDirection.SOUTH);
        assertTrue(animal.getCoordinates().equals(new Vector2d(2, 0)));
    }
}