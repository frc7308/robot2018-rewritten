package frc.team7308.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;

import frc.team7308.robot.ControlLoop;
import frc.team7308.robot.DriverStation;
import frc.team7308.robot.subsystems.Subsystem;

public class Lift extends Subsystem {
    private SpeedControllerGroup m_lift;
    private double m_liftSpeed;
    public static Encoder m_encoder;
    private DigitalInput zeroSensor;

    private DriverStation driverStation;

    private double kP = 0.05;
    private double kI = 0.05;
    private double kD = 0.0;
    private double prev_err;
    private double integralGain = 0.0;

    private boolean zeroed;

    private int goal_height = 1000;

    private int kMinHeight = 0;
    private int kMaxHeight = 3500;

    public int kAcceptableError = 5; // In encoder pulses

    public final ControlLoop controlLoop = new ControlLoop() {
        @Override
        public void loopPeriodic() {
            if (!zeroed && !zeroSensor.get()) {
                zeroed = true;
                m_encoder.reset();
                System.out.println("zeroed");
            }
            if (gameState.equals("Autonomous")) {
                double error = goal_height - m_encoder.get();
                if (Math.abs(error) < kAcceptableError) {
                    integralGain = 0;
                } else {
                    integralGain += (error * deltaTime * 0.001);
                }
                double derivativeGain = (error - prev_err) / deltaTime;
                m_liftSpeed = kP * error + kI * integralGain + kD * derivativeGain;
                m_lift.set(m_liftSpeed);
            } else {
                double throttle = driverStation.getLiftThrottle() * -1;
                if (m_encoder.get() < -600 && zeroed) {
                    throttle = clamp(throttle, 0.0, 1.0);
                }
                if (m_encoder.get() > 3500 && zeroed) {
                    throttle = clamp(throttle, -1.0, 0.0);
                }
                if (m_encoder.get() <= 200 && zeroed && !Claw.m_sliderOut) {
                    throttle = clamp(throttle, 0.0, 1.0);
                }
                m_lift.set(throttle);
            }
        }
    };

    public Lift() {
        Spark leftMotor = new Spark(4);
        Spark middleMotor = new Spark(5);
        Spark rightMotor = new Spark(6);
        this.m_lift = new SpeedControllerGroup(leftMotor, middleMotor, rightMotor);

        this.m_encoder = new Encoder(0, 1);
        this.zeroSensor = new DigitalInput(3);

        zeroed = false;

        this.driverStation = DriverStation.getInstance();
    }

    // Normalized coordinates
    public void setHeight(double height) {
        this.goal_height = (int)((kMaxHeight - kMinHeight) * height) + kMinHeight;
    }

    private static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
}