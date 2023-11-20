package agh.ics.oop.model;
import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.RandomPositionGenerator;
import java.util.*;

public class GrassField extends AbstractWorldMap{
    private final Map<Vector2d, Grass> grasses;
    public GrassField(int grassNumber) {
        this.grasses = new HashMap<>();

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
    public List<WorldElement> getElements() {
        List<WorldElement> elements = super.getElements();
        elements.addAll(grasses.values());
        return elements;
    }

    @Override
    public Boundary getBoundary(){
        Vector2d bottom = new Vector2d(upperRight.x, upperRight.y);
        Vector2d top = new Vector2d(lowerLeft.x, lowerLeft.y);
        for (WorldElement animal: animals.values()) {
            bottom = bottom.lowerLeft(animal.getPosition());
            top = top.upperRight(animal.getPosition());
        }
        for (WorldElement grass: grasses.values()) {
            bottom = bottom.lowerLeft(grass.getPosition());
            top = top.upperRight(grass.getPosition());
        }
        return new Boundary(bottom, top);
    }
}
