package agh.ics.oop;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
    public static void main(String[] args){
        // lab 1
        /*
        System.out.println("system wystartowal");
        MoveDirection[] directions = translate(args);
        run(directions);
        System.out.println("system zakonczyl dzialanie");
        */
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        MapDirection dir = MapDirection.NORTH;
        System.out.println(dir.toString());
        System.out.println(dir.next());
        System.out.println(dir.previous());
        System.out.println(dir.toUnitVector());
    }

    public static void run(MoveDirection[] directions) {
        for (MoveDirection dir : directions) {
            switch (dir) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case LEFT -> System.out.println("Zwierzak skreca w lewo");
                case RIGHT -> System.out.println("Zwierzak skreca w prawo");
                case BACKWARD -> System.out.println("Zwierzak idzie do tylu");
            }
        }
    }
}
