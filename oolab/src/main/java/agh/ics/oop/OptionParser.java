package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionParser {
    public static List<MoveDirection> parse(String[] args) {
        return Stream.of(args)
                .map(arg -> {
                    switch (arg) {
                        case "f" -> {
                            return MoveDirection.FORWARD;
                        }
                        case "b" -> {
                            return MoveDirection.BACKWARD;
                        }
                        case "r" -> {
                            return MoveDirection.RIGHT;
                        }
                        case "l" -> {
                            return MoveDirection.LEFT;
                        }
                        default -> throw new IllegalArgumentException(arg + " is not a legal move specification");
                    }
                })
                .collect(Collectors.toList());
    }
}
