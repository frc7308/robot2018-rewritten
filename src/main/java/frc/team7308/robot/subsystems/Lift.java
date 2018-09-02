package frc.team7308.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Encoder;

import frc.team7308.robot.ControlLoop;
import frc.team7308.robot.DriverStation;
import frc.team7308.robot.subsystems.Subsystem;

public class Lift extends Subsystem {
    private SpeedControllerGroup m_lift;
    private double m_liftSpeed;
    private Encoder m_encoder;

    private DriverStation driverStation;

    private double kP = 0.05;
    private double kI = 0.05;
    private double kD = 0.0;
    private double prev_err;
    private double integralGain = 0.0;

    private int goal_height;

    private int kMinHeight = 0;
    private int kMaxHeight = 3600;

    private int kAcceptableError = 5; // In encoder pulses

    public final ControlLoop controlLoop = new ControlLoop() {
        @Override
        public void loopPeriodic() {
            double error = goal_height - encoder.get();
            if (Math.abs(error) < kAcceptableError) {
                integralGain = 0;
            } else {
                integralGain += (error * deltaTime * 0.001);
            }
            double derivativeGain = (error - prev_err) / deltaTime;
            m_liftSpeed = kP * error + kI * integralGain + kD * derivativeGain;

            m_lift.set(m_liftSpeed);
            System.out.println(m_liftSpeed);
        }
    };

    public Lift() {
        Spark leftMotor = new Spark(4);
        Spark middleMotor = new Spark(5);
        Spark rightMotor = new Spark(6);
        this.m_lift = new SpeedControllerGroup(leftMotor, middleMotor, rightMotor);

        this.m_encoder = new Encoder(0, 1);

        controlLoop.start();
    }

    // Normalized coordinates
    public void setHeight(double height) {
        this.goal_height = (int)((kMaxHeight - kMinHeight) * height) + kMinHeight;
    }
}