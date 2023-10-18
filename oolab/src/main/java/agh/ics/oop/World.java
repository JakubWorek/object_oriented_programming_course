package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import static agh.ics.oop.OptionParser.translate;

public class World {
    public static void main(String[] args){
        System.out.println("system wystartowal");
        MoveDirection[] directions = translate(args);
        run(directions);
        System.out.println("system zakonczyl dzialanie");
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
