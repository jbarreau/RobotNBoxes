package main;

import implementation.environement.Environement;
import implementation.gameManager.GameManager;
import implementation.gameManager.GameManagerImpl;
import implementation.gui.GUI;
import implementation.gui.GUIImpl;
import implementation.robot.Robot;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String args[]) {
        GameManager gm = new GameManagerImpl();
        GUI gui = new GUIImpl(gm);

        List<Robot> robs = new ArrayList<Robot>();
        for (int i = 0; i < 15; i++) {
            //robs.add(Environement.getInstance().createRobot());
            Environement.getInstance().createRobot();
        }


    }
}
