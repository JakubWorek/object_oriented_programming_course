package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionParser {
    public static MoveDirection[] translate(String[] args) {
        int size = args.length;
        MoveDirection[] results = new MoveDirection[size];

        for (int i = 0; i < size; i++) {
            switch (args[i]) {
                case "f":
                    results[i] = MoveDirection.FORWARD;
                    break;
                case "b":
                    results[i] = MoveDirection.BACKWARD;
                    break;
                case "r":
                    results[i] = MoveDirection.RIGHT;
                    break;
                case "l":
                    results[i] = MoveDirection.LEFT;
                    break;
                default:
                    results[i] = MoveDirection.FAILED;
                    break;
            }
        }

        return results;
    }
}
