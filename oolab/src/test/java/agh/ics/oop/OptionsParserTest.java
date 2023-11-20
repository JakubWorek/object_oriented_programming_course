package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class OptionsParserTest {
    @Test
    void translate_shouldTranslateForward() {
        String[] args = {"f"};
        List<MoveDirection> expected = List.of(MoveDirection.FORWARD);
        assertEquals(expected, OptionParser.parse(args));
    }

    @Test
    void translate_shouldTranslateBackward() {
        String[] args = {"b"};
        List<MoveDirection> expected = List.of(MoveDirection.BACKWARD);
        assertEquals(expected, OptionParser.parse(args));
    }

    @Test
    void translate_shouldTranslateRight() {
        String[] args = {"r"};
        List<MoveDirection> expected = List.of(MoveDirection.RIGHT);
        assertEquals(expected, OptionParser.parse(args));
    }

    @Test
    void translate_shouldTranslateLeft() {
        String[] args = {"l"};
        List<MoveDirection> expected = List.of(MoveDirection.LEFT);
        assertEquals(expected, OptionParser.parse(args));
    }

    @Test
    void translate_shouldTranslateFailedForUnknownDirection() {
        String[] args = {"x"};
        List<MoveDirection> expected = List.of();
        assertEquals(expected, OptionParser.parse(args));
    }

    @Test
    void translate_shouldTranslateMultipleDirections() {
        String[] args = {"f", "b", "r", "l"};
        List<MoveDirection> expected = List.of(
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT
        );
        assertEquals(expected, OptionParser.parse(args));
    }
}