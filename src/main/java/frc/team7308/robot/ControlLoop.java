package frc.team7308.robot;

import java.util.Timer;
import java.util.TimerTask;

import frc.team7308.robot.Constants;

public class ControlLoop {
    private Timer loopTimer;
    public double deltaTime;

    public void start() {
        class LoopTask extends TimerTask {
            private double prevTime;

            public void run() {
                double currTime = System.nanoTime() / 1000000.0;
                ControlLoop.this.deltaTime = currTime - prevTime;
                prevTime = currTime;
                ControlLoop.this.loopPeriodic();
            }
        }

        loopInit();

        loopTimer = new Timer();
        loopTimer.schedule(new LoopTask(), 0, Constants.kControlLoopInterval);
    }

    public void loopInit() {};
    public void loopPeriodic() {};
}