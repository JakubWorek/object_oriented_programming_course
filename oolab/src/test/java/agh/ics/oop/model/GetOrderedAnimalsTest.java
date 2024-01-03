package agh.ics.oop.model;

import agh.ics.oop.model.util.PositionAlreadyOccupiedException;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetOrderedAnimalsTest {
    @Test
    void testGetOrderedAnimals1() throws PositionAlreadyOccupiedException {
        AbstractWorldMap map = new GrassField(10);

        Animal animal1 = new Animal(MapDirection.NORTH, new Vector2d(3, 4));
        Animal animal2 = new Animal(MapDirection.NORTH, new Vector2d(1, 2));
        Animal animal3 = new Animal(MapDirection.NORTH, new Vector2d(2, 2));

        map.place(animal1);
        map.place(animal2);
        map.place(animal3);

        Collection<Animal> orderedAnimals = map.getOrderedAnimals();

        // Sprawdź, czy zwierzęta są posortowane po pozycjach
        assertEquals(animal2, orderedAnimals.iterator().next());
    }

    @Test
    void testGetOrderedAnimals2() throws PositionAlreadyOccupiedException {
        AbstractWorldMap map = new GrassField(10);

        Animal animal1 = new Animal(MapDirection.NORTH, new Vector2d(2, 3));
        Animal animal2 = new Animal(MapDirection.NORTH, new Vector2d(1, 1));
        Animal animal3 = new Animal(MapDirection.NORTH, new Vector2d(2, 2));

        try {
            map.place(animal1);
            map.place(animal2);
            map.place(animal3);
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        }

        // Pobierz posortowane zwierzęta
        Collection<Animal> orderedAnimals = map.getOrderedAnimals();

        // Sprawdź, czy zwierzęta są posortowane zgodnie z oczekiwaniem
        assertEquals(3, orderedAnimals.size());

        // Oczekiwana kolejność: animal2, animal3, animal1
        Animal[] animalsArray = orderedAnimals.toArray(new Animal[0]);
        assertEquals(animal2, animalsArray[0]);
        assertEquals(animal3, animalsArray[1]);
        assertEquals(animal1, animalsArray[2]);
    }
}
