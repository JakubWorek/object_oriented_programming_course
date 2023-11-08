package agh.ics.oop;
import agh.ics.oop.model.*;

import java.util.List;
import static agh.ics.oop.OptionParser.parse;


public class World {
    public static void main(String[] args){
        System.out.println("system wystartowal");

        // RectangularMap
        List<MoveDirection> directions = parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap<Animal, Vector2d> map = new RectangularMap(5,5);
        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        // TextMap
        List<String> texts = List.of("Ala", "ma", "sowoniedźwiedzia");
        WorldMap<String, Integer> textMap = new TextMap();
        for (String text : texts) {
            textMap.place(text);
        }
        System.out.println(textMap);
        textMap.move("ma", MoveDirection.FORWARD);
        System.out.println(textMap);

        System.out.println("system zakonczyl dzialanie");
    }
}
