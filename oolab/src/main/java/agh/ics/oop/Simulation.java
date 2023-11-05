package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;
import java.util.ArrayList;


public class Simulation {
    private List<Animal> animals;

    private List<MoveDirection> directions;
    private int currentMovementIndex;

    public Simulation(List<MoveDirection> directions, List<Vector2d> positions) {
        this.animals = new ArrayList<>();
        for (int i=0; i<positions.size(); i++){
            animals.add(new Animal(MapDirection.NORTH,positions.get(i)));
        }
        this.currentMovementIndex = 0;
        this.directions = directions;
    }

    public List<Animal> getAnimals() {
        return this.animals;
    }

    public void run() {
        while (currentMovementIndex < directions.size()) {
            for (int i=0; i<animals.size(); i++){
                if (currentMovementIndex >= directions.size()) {
                    break;
                }

                MoveDirection movement = directions.get(currentMovementIndex);
                animals.get(i).move(movement);
                System.out.println("Zwierze " + i + ": " + animals.get(i));
                currentMovementIndex++;
            }
        }
    }
}