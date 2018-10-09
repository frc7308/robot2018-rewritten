package frc.team7308.robot;

// Basic control loop. Contains a delta time tracker, a game state tracker, a loop init method and a loop periodic method.
public class ControlLoop {
    public double deltaTime; // The time since the last loop in milliseconds.
    public String gameState = "Disabled"; // The current state of the game.

    public void loopInit() {}; // Called once on loop startup.
    public void loopPeriodic() {}; // Called repeatedly on the loop interval.
}