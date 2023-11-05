package agh.ics.oop;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;
import static agh.ics.oop.OptionParser.parse;


public class World {
    public static void main(String[] args){
        System.out.println("system wystartowal");

        /*
        Uważam, że najlepsze jest używanie ArrayList jako implementacji listy w Javie
        w przypadku naszych zadań, ponieważ gwarantuje ona szybki dostęp do elementów,
        indeksowanie ich i możliwość wywołania animals.get(i) dzięki czemu mogą one
        być używane w "bezpieczny" sposób - tzn. kompilator może sprawdzić, czy
        wywoływane metody faktycznie występują w klasie Animal.
        */

        List<MoveDirection> directions = parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        System.out.println("system zakonczyl dzialanie");
    }

    public static void run(List<MoveDirection> directions, Animal animal) {
        for (MoveDirection direction : directions) {
            switch (direction) {
                case FORWARD -> animal.move(MoveDirection.FORWARD);
                case BACKWARD -> animal.move(MoveDirection.BACKWARD);
                case RIGHT -> animal.move(MoveDirection.RIGHT);
                case LEFT -> animal.move(MoveDirection.LEFT);
            }
        }
    }
}
