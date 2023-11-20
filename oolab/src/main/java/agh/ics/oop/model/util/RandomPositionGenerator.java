package agh.ics.oop.model.util;

import agh.ics.oop.model.Vector2d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final int maxWidth;
    private final int maxHeight;
    private final int count;
    private final List<Vector2d> allPositions;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int count) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.count = count;
        this.allPositions = generateAllPositions();
        // Tasowanie jest jednorazowe co zapewnia determinizm
        Collections.shuffle(allPositions);
    }

    private List<Vector2d> generateAllPositions() {
        List<Vector2d> positions = new ArrayList<>();
        for (int x = 0; x < maxWidth; x++) {
            for (int y = 0; y < maxHeight; y++) {
                positions.add(new Vector2d(x, y));
            }
        }
        return positions;
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new Iterator<Vector2d>() {
            private int generatedCount = 0;

            @Override
            public boolean hasNext() {
                return generatedCount < count;
            }

            @Override
            public Vector2d next() {
                if (!hasNext()) {
                    throw new UnsupportedOperationException("No more positions to generate");
                }

                Vector2d position = allPositions.get(generatedCount);
                generatedCount++;
                return position;
            }
        };
    }
}
