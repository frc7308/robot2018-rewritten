package frc.team7308.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.team7308.robot.ControlLoop;
import frc.team7308.robot.Input;
import frc.team7308.robot.subsystems.Subsystem;

public class Lift extends Subsystem {
    private SpeedControllerGroup m_lift;
    private double m_liftSpeed;
    public static Encoder m_encoder;
    private DigitalInput zeroSensor;

    private Input driverStation;

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
            // Zero if not already and the zero sensor is triggered.
            // This is why the lift moves down in most autonomous routines.
            if (!zeroed && !zeroSensor.get()) {
                zeroed = true;
                m_encoder.reset();
                System.out.println("zeroed");
            }
            // Use PID-based position mode during Autonomous.
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
            // Use manual stick control during Teleop.
            } else {
                double encoder = m_encoder.get();
                double throttle = driverStation.getLiftThrottle() * -1;
                // Prevent superstructure collisions with the claw by preventing motion in one direction at certain values.
                // PID correction is not neccesary as the encoder is extremely precise and the control loop refresh
                // rate is fast enough for this purpose.
                if (encoder < -600 && zeroed) {
                    throttle = clamp(throttle, 0.0, 1.0);
                }
                if (encoder > 3500 && zeroed) {
                    throttle = clamp(throttle, -1.0, 0.0);
                }
                if (encoder <= 200 && zeroed && !Claw.m_sliderOut) {
                    throttle = clamp(throttle, 0.0, 1.0);
                }
                m_lift.set(throttle);
            }
            // Display stats on the SmartDashboard for quick troubleshooting during a match.
            SmartDashboard.putNumber("Encoder", m_encoder.get());
            SmartDashboard.putBoolean("Zeroed", zeroed);
        }
    };

    public Lift() {
        // Initialize the lift sparks and group them together.
        Spark leftMotor = new Spark(4);
        Spark middleMotor = new Spark(5);
        Spark rightMotor = new Spark(6);
        this.m_lift = new SpeedControllerGroup(leftMotor, middleMotor, rightMotor);

        // Initialize the sensors (encoder and zero sensor).
        this.m_encoder = new Encoder(0, 1);
        this.zeroSensor = new DigitalInput(3);

        this.zeroed = false;

        this.driverStation = Input.getInstance();
    }

    // Sets the height of the lift while in position mode. Uses normalized coordinates (0.0 for 0 state, 1.0 for highest state, approx. -0.166 for lowest state).
    public void setHeight(double height) {
        this.goal_height = (int)((kMaxHeight - kMinHeight) * height) + kMinHeight;
    }

    // Used to clamp the lift speed value to prevent superstructure collisions.
    private static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
}