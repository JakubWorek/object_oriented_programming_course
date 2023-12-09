package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener{
    private int updateCount = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println("Update #" + (++updateCount) + ": " + message);
        System.out.println(worldMap);
    }
}
