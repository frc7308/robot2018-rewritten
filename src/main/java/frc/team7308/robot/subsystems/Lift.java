package frc.team7308.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Encoder;

import frc.team7308.robot.ControlLoop;
import frc.team7308.robot.DriverStation;
import frc.team7308.robot.subsystems.Subsystem;

public class Lift extends Subsystem {
    private SpeedControllerGroup lift;
    private double liftSpeed;
    private Encoder encoder;

    private double kP = 0.5;
    private double kI = 0.5;
    private double kD = 0.0;
    private double prev_err;
    private double integralGain;

    private int goal_height;

    private int kMinHeight = 0;
    private int kMaxHeight = 3600;

    public final ControlLoop controlLoop = new ControlLoop() {
        @Override
        public void loopPeriodic() {
            double error = goal_height - encoder.get();
            integralGain += (error * deltaTime);
            double derivativeGain = (error - prev_err) / deltaTime;
            liftSpeed = kP * error + kI * integralGain + kD * derivativeGain;

            lift.set(liftSpeed);
        }
    };

    public Lift() {
        Spark leftMotor = new Spark(4);
        Spark middleMotor = new Spark(5);
        Spark rightMotor = new Spark(6);
        this.lift = new SpeedControllerGroup(leftMotor, middleMotor, rightMotor);

        this.encoder = new Encoder(0, 1);

        controlLoop.start();
    }

    // Normalized coordinates
    public void setHeight(double height) {
        this.goal_height = (int)((kMaxHeight - kMinHeight) * height) + kMinHeight;
    }
}