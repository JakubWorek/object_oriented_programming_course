package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {
    private final List<Simulation> simulations;
    private final List<Thread> threads = new ArrayList<>();

    public SimulationEngine(List<Simulation> simulations){
        this.simulations = simulations;
    }

    public void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    public void runAsync() {
        for (Simulation simulation : simulations) {
            Thread thread = new Thread(simulation);
            threads.add(thread);
            thread.start();
        }
        awaitSimulationEnd();
    }

    public void awaitSimulationEnd() {
        try{
            for (Thread thread: threads){
                thread.join();
            }
        }
        catch (InterruptedException e){
            System.out.println(e);
        }
    }
}
