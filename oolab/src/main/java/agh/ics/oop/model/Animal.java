package agh.ics.oop.model;

public class Animal implements WorldElement{
    private MapDirection direction;
    private Vector2d position;

    public Animal(){
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
    }

    public Animal(MapDirection direction, Vector2d positions){
        this.direction = direction;
        this.position = positions;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    public String toString(){
        return this.direction.toString();
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction, WorldMap map){
        Vector2d potentialNewPosition;

        switch (direction){
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case FORWARD:
                potentialNewPosition = this.position.add(this.direction.toUnitVector());
                if (map.canMoveTo(potentialNewPosition)) {
                    if (map.objectAt(potentialNewPosition) instanceof Grass) {
                        ((GrassField) map).RespawnGrass(potentialNewPosition);
                    }
                    this.position = potentialNewPosition;
                }
                break;
            case BACKWARD:
                potentialNewPosition = this.position.subtract(this.direction.toUnitVector());
                if (map.canMoveTo(potentialNewPosition)) {
                    if (map.objectAt(potentialNewPosition) instanceof Grass) {
                        ((GrassField) map).RespawnGrass(potentialNewPosition);
                    }
                    this.position = potentialNewPosition;
                }
                break;
            default:
                break;
        }
    }
}
