package frc.team7308.robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import frc.team7308.robot.Constants;
import frc.team7308.robot.ControlLoop;

public class LoopMaster {
    public ArrayList<ControlLoop> loopList = new ArrayList<ControlLoop>();;

    private Timer loopTimer;
    private String gameState = "Disabled";

    public void addLoop(ControlLoop loop) {
        loopList.add(loop);
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
        for (ControlLoop loop : loopList) {
            loop.gameState = gameState;
        }
    }

    public void start() {
        class LoopTask extends TimerTask {
            private double prevTime;

            public void run() {
                double currTime = System.nanoTime() / 1000000.0;
                double deltaTime = currTime - prevTime;
                prevTime = currTime;
                for (ControlLoop loop : LoopMaster.this.loopList) {
                    loop.loopPeriodic();
                    loop.deltaTime = deltaTime;
                }
            }
        }

        for (ControlLoop loop : loopList) {
            loop.loopInit();
            loop.deltaTime = 0.0;
        }

        loopTimer = new Timer();
        loopTimer.schedule(new LoopTask(), 0, Constants.kControlLoopInterval);
    }
}