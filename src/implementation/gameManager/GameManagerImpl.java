package implementation.gameManager;

import implementation.environement.Environement;
import implementation.robot.Robot;

import java.io.Serializable;


public class GameManagerImpl implements GameManager {
    private boolean paused = false;
    private boolean stop = false;
    private double speed = 1;

    public GameManagerImpl() {
        Thread tick = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        while (!stop) {
                            while (!paused) {
                                Thread.sleep((long) (1000 / speed));
                                for (Robot r : Environement.getInstance().getRobots()) {
                                    r.play();
                                }
                            }
                            Thread.sleep(100);
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
        stop = true;

    }

    @Override
    public void play() {
        stop = false;
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
