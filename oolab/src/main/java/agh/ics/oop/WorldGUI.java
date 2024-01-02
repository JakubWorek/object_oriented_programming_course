package agh.ics.oop;

import agh.ics.oop.model.*;
import javafx.application.Application;

import java.util.List;

import static agh.ics.oop.OptionParser.parse;

public class WorldGUI {
    public static void main(String[] args){
        System.out.println("system wystartowal");
        try {
            Application.launch(SimulationApp.class, args);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.println("system zakonczyl dzialanie");
    }
}
