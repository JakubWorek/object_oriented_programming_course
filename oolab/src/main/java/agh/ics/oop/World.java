package agh.ics.oop;
import agh.ics.oop.model.*;

import java.util.List;
import static agh.ics.oop.OptionParser.parse;


public class World {
    public static void main(String[] args){
        System.out.println("system wystartowal");

        List<MoveDirection> directions = parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap map = new GrassField(10, positions);
        // WorldMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        System.out.println("system zakonczyl dzialanie");
    }
}
