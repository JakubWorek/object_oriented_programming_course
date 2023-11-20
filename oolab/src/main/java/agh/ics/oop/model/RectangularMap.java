package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap{
    public RectangularMap(int width, int height){
        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(width-1, height-1);
    }
}
