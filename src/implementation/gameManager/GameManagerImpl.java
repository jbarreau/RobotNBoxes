package implementation.gameManager;

import implementation.environement.Environement;
import implementation.robot.Robot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;


public class GameManagerImpl implements GameManager {
    private boolean paused = false;
    private double speed = 1;

    public GameManagerImpl() {
        Thread tick = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        while (!paused) {
                            Thread.sleep((long) (1000 / speed));
                            for (Robot r : Environement.getInstance().getRobots()) {
                                r.play();
                            }
                        }
                    Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        tick.start();
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void stop() {
        paused = true;
        new File("log").mkdir();
        for (Robot r : Environement.getInstance().getRobots()) {
        	try {
				File file = new File("log/"+r.hashCode()+".log");
				file.createNewFile();
				FileWriter writer = new FileWriter(file);
				writer.write(r.getLog());
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

    @Override
    public void play() {
        paused = false;
    }

    @Override
    public void speed(double s) {
        speed = s;
    }


    @Override
    public void changeCorridor() {
        Environement.getInstance().changeCorridor();
    }

    @Override
    public Serializable saveState() {
        Serializable state = Environement.getInstance().SerializeSystem();
        return state;
    }

    @Override
    public void loadState(Serializable state) {
        Environement.getInstance().unserializeSystem(state);
    }

}
