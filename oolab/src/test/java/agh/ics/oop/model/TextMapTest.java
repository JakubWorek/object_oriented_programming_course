package agh.ics.oop.model;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TextMapTest {
    @Test
    public void testIfMapWorks(){
        List<String> texts = List.of("Ala", "ma", "sowoniedźwiedzia");
        WorldMap<String, Integer> textMap = new TextMap();
        for (String text : texts) {
            textMap.place(text);
        }
        textMap.move("ma", MoveDirection.FORWARD);
        assertEquals("[\"Ala\", \"sowoniedźwiedzia\", \"ma\"]", textMap.toString());
    }

    @Test
    public void testIfMapWorks2(){
        List<String> texts = List.of("Ala", "ma", "sowoniedźwiedzia");
        WorldMap<String, Integer> textMap = new TextMap();
        for (String text : texts) {
            textMap.place(text);
        }
        textMap.move("ma", MoveDirection.BACKWARD);
        assertEquals("[\"ma\", \"Ala\", \"sowoniedźwiedzia\"]", textMap.toString());
    }

    @Test
    public void testIfMapWorks3(){
        List<String> texts = List.of("Ala", "ma", "sowoniedźwiedzia");
        WorldMap<String, Integer> textMap = new TextMap();
        for (String text : texts) {
            textMap.place(text);
        }
        textMap.move("ma", MoveDirection.BACKWARD);
        textMap.move("ma", MoveDirection.BACKWARD);
        assertEquals("[\"ma\", \"Ala\", \"sowoniedźwiedzia\"]", textMap.toString());
    }

    @Test
    public void testIfMapWorks4(){
        List<String> texts = List.of("Ala", "ma", "sowoniedźwiedzia");
        WorldMap<String, Integer> textMap = new TextMap();
        for (String text : texts) {
            textMap.place(text);
        }
        textMap.move("ma", MoveDirection.FORWARD);
        textMap.move("ma", MoveDirection.FORWARD);
        assertEquals("[\"Ala\", \"sowoniedźwiedzia\", \"ma\"]", textMap.toString());
    }

    @Test
    public void testPlace(){
        WorldMap<String, Integer> textMap = new TextMap();
        textMap.place("Ala");
        textMap.place("ma");
        textMap.place("kota");
        assertEquals("[\"Ala\", \"ma\", \"kota\"]", textMap.toString());
    }

    @Test
    public void testMoveForward(){
        WorldMap<String, Integer> textMap = new TextMap();
        textMap.place("Ala");
        textMap.place("ma");
        textMap.place("kota");
        textMap.move("ma", MoveDirection.FORWARD);
        assertEquals("[\"Ala\", \"kota\", \"ma\"]", textMap.toString());
    }

    @Test
    public void testMoveBackward(){
        WorldMap<String, Integer> textMap = new TextMap();
        textMap.place("Ala");
        textMap.place("ma");
        textMap.place("kota");
        textMap.move("ma", MoveDirection.BACKWARD);
        assertEquals("[\"ma\", \"Ala\", \"kota\"]", textMap.toString());
    }

    @Test
    public void testIsOcupied(){
        WorldMap<String, Integer> textMap = new TextMap();
        textMap.place("Ala");
        textMap.place("ma");
        textMap.place("kota");
        assertTrue(textMap.isOccupied(0));
        assertTrue(textMap.isOccupied(1));
        assertTrue(textMap.isOccupied(2));
        assertFalse(textMap.isOccupied(3));
    }

    @Test
    public void testObjectAt(){
        WorldMap<String, Integer> textMap = new TextMap();
        textMap.place("Ala");
        textMap.place("ma");
        textMap.place("kota");
        assertEquals("Ala", textMap.objectAt(0));
        assertEquals("ma", textMap.objectAt(1));
        assertEquals("kota", textMap.objectAt(2));
        assertNull(textMap.objectAt(3));
    }
}
