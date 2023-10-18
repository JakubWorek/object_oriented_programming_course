package agh.ics.oop.model;
import agh.ics.oop.OptionParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class OptionsParserTest {
    @Test
    void translate_shouldTranslateForward() {
        String[] args = {"f"};
        MoveDirection[] expected = {MoveDirection.FORWARD};
        assertArrayEquals(expected, OptionParser.translate(args));
    }

    @Test
    void translate_shouldTranslateBackward() {
        String[] args = {"b"};
        MoveDirection[] expected = {MoveDirection.BACKWARD};
        assertArrayEquals(expected, OptionParser.translate(args));
    }

    @Test
    void translate_shouldTranslateRight() {
        String[] args = {"r"};
        MoveDirection[] expected = {MoveDirection.RIGHT};
        assertArrayEquals(expected, OptionParser.translate(args));
    }

    @Test
    void translate_shouldTranslateLeft() {
        String[] args = {"l"};
        MoveDirection[] expected = {MoveDirection.LEFT};
        assertArrayEquals(expected, OptionParser.translate(args));
    }

    @Test
    void translate_shouldTranslateFailedForUnknownDirection() {
        String[] args = {"x"};
        MoveDirection[] expected = {MoveDirection.FAILED};
        assertArrayEquals(expected, OptionParser.translate(args));
    }

    @Test
    void translate_shouldTranslateMultipleDirections() {
        String[] args = {"f", "b", "r", "l"};
        MoveDirection[] expected = {
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT
        };
        assertArrayEquals(expected, OptionParser.translate(args));
    }
}

