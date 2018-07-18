package frc.team7308.robot;

import java.util.Timer;
import java.util.TimerTask;

import frc.team7308.robot.Constants;

public class ControlLoop {
    private Timer loopTimer;

    public void start() {
        class LoopTask extends TimerTask {
            public void run() {
                ControlLoop.this.loopPeriodic();
            }
        }

        this.loopInit();

        loopTimer = new Timer();
        loopTimer.schedule(new LoopTask(), 0, Constants.kControlLoopInterval);
    }

    public void loopInit() {};
    public void loopPeriodic() {};
}