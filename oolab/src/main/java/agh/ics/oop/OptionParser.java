package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.List;
import java.util.ArrayList;

public class OptionParser {
    public static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> commands = new ArrayList<>();

        for (String arg : args){
            switch (arg) {
                case "f" -> commands.add(MoveDirection.FORWARD);
                case "b" -> commands.add(MoveDirection.BACKWARD);
                case "r" -> commands.add(MoveDirection.RIGHT);
                case "l" -> commands.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(arg + " is not legal move specification");
            }
        }

        return commands;
    }
}
