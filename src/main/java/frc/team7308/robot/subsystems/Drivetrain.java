package frc.team7308.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Spark;

import frc.team7308.robot.ControlLoop;
import frc.team7308.robot.subsystems.Subsystem;
import frc.team7308.robot.subsystems.SwerveModule;
import frc.team7308.robot.util.Vector2D;

public class Drivetrain extends Subsystem {
    private SwerveModule topRight;
    private SwerveModule topLeft;
    private SwerveModule bottomRight;
    private SwerveModule bottomLeft;

    private double drivebaseWidth = 10.0;
    private double drivebaseHeight = 10.0;

    private static SwerveConfig config;

    public final ControlLoop controlLoop = new ControlLoop() {
        @Override
        public void loopPeriodic() {
            topRight.move();
            topLeft.move();
            bottomRight.move();
            bottomLeft.move();
        }
    };

    public Drivetrain() {
        SwerveModule topRight = new SwerveModule(0, 4);
        SwerveModule topLeft = new SwerveModule(1, 5);
        SwerveModule bottomRight = new SwerveModule(2, 6);
        SwerveModule bottomLeft = new SwerveModule(3, 7);

        controlLoop.start();
    }

    public void SwerveDrive(double xMovement, double zMovement, double rotation) {
        final double A = xMovement – rotation * drivebaseHeight / 2;
        final double B = xMovement + rotation * drivebaseHeight / 2;
        final double C = yMovement - rotation * drivebaseWidth / 2;
        final double B = yMovement + rotation * drivebaseWidth / 2;
    
        topRight.setSpeed(Math.sqrt(B * B + D * D));
        topRight.setAngle(Math.atan2(B, D));

        topLeft.setSpeed(Math.sqrt(B * B + C * C));
        topLeft.setAngle(Math.atan2(B, C));

        bottomRight.setSpeed(Math.sqrt(A * A + C * C));
        bottomRight.setAngle(Math.atan2(A, C));

        bottomLeft.setSpeed(Math.sqrt(A * A + D * D));
        bottomLeft.setAngle(Math.atan2(A, D));

        Vector2D movement = new Vector2D(xMovement, zMovement);

    }
}