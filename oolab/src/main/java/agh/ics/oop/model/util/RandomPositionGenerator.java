package agh.ics.oop.model.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import agh.ics.oop.model.Vector2d;
import java.util.HashSet;
import java.util.Set;

public class RandomPositionGenerator implements Iterable<Vector2d>, Iterator<Vector2d> {
    private final int maxWidth;
    private final int maxHeight;
    private final int grassCount;
    private final Random random;
    private final Set<Vector2d> generatedPositions;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int grassCount) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.grassCount = grassCount;
        this.random = new Random();
        this.generatedPositions = new HashSet<>();
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return generatedPositions.size() < grassCount;
    }

    @Override
    public Vector2d next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more positions to generate.");
        }

        Vector2d position;
        do {
            int x = random.nextInt(maxWidth);
            int y = random.nextInt(maxHeight);
            position = new Vector2d(x, y);
        } while (generatedPositions.contains(position));

        generatedPositions.add(position);
        return position;
    }
}
