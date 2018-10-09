package frc.team7308.robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import frc.team7308.robot.Constants;
import frc.team7308.robot.ControlLoop;

// Condenses all of the control loops into one to save roboRIO computing power.
public class LoopMaster {
    public ArrayList<ControlLoop> loopList = new ArrayList<ControlLoop>(); // A list of all the loops the robot uses.

    private Timer loopTimer; // Java timer to time the loop.
    private String gameState = "Disabled"; // Global gamestate flag.

    // Adds a loop to the list of loops.
    public void addLoop(ControlLoop loop) {
        loopList.add(loop);
    }

    // Set the gamestate for all control loops.
    public void setGameState(String gameState) {
        this.gameState = gameState;
        for (ControlLoop loop : loopList) {
            loop.gameState = gameState;
        }
    }

    // Calling this function starts all of the control loops.
    public void start() {
        // TimerTask that contains all of the control loops.
        class LoopTask extends TimerTask {
            private double prevTime; // Tracks the previous timestamp for DeltaTime calculations.

            public void run() {
                double currTime = System.nanoTime() / 1000000.0; // Get the current timestamp in milliseconds.
                double deltaTime = currTime - prevTime; // Calculate deltatime.
                prevTime = currTime; // Set prevTime.
                // Run all of the loops and set their deltaTime variables.
                for (ControlLoop loop : LoopMaster.this.loopList) {
                    loop.loopPeriodic();
                    loop.deltaTime = deltaTime;
                }
            }
        }

        // Initialize all of the loops.
        for (ControlLoop loop : loopList) {
            loop.loopInit();
            loop.deltaTime = 0.0;
        }

        // Start all of the loops using the TimerTask on the loop interval.
        loopTimer = new Timer();
        loopTimer.schedule(new LoopTask(), 0, Constants.kControlLoopInterval);
    }
}