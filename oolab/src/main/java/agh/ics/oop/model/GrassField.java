package agh.ics.oop.model;
import agh.ics.oop.model.util.RandomPositionGenerator;
import java.util.*;

public class GrassField extends AbstractWorldMap{
    private final Map<Vector2d, Grass> grasses;
    public GrassField(int grassNumber) {
        this.grasses = new HashMap<>();
        /* To rozwiązanie jest niedeterministyczne
        Random random = new Random();
        int i=0;
        while (i < this.grassNumber) {
            int x = random.nextInt((int) Math.sqrt(10 * this.grassNumber));
            int y = random.nextInt((int) Math.sqrt(10 * this.grassNumber));

            boolean flag = false;
            for (Vector2d position: grasses.keySet()) {
                if (Objects.equals(position, new Vector2d(x, y))) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                this.grasses.put(new Vector2d(x,y), new Grass(new Vector2d(x, y)));
                i++;
            }
        }
        */

        // W tym przypadku jest deterministyczne
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator((int) Math.sqrt(10 * grassNumber), (int) Math.sqrt(10 * grassNumber), grassNumber);
        for (Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement object = super.objectAt(position);
        if(object != null) return object;
        return grasses.get(position);
    }

    @Override
    public String toString() {
        Vector2d bottom = new Vector2d(upperRight.x, upperRight.y);
        Vector2d top = new Vector2d(lowerLeft.x, lowerLeft.y);
        List<WorldElement> elements = getElements();
        for (WorldElement element: elements) {
            bottom = bottom.lowerLeft(element.getPosition());
            top = top.upperRight(element.getPosition());
        }
        return visualizer.draw(bottom, top);
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> elements = super.getElements();
        elements.addAll(grasses.values());
        return elements;
    }
}
