package frc.team7308.robot.auto.commands;

public class DriveForTime extends Command {
    private double time;
    private double leftSpeed;
    private double rightSpeed;

    public DriveForTime(double time, double leftSpeed, double rightSpeed) {
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        this.time = time;
    	requires(drivetrain);
    }

    protected void initialize() {
        setTimeout(time);
        drivetrain.TankDrive(leftSpeed, rightSpeed);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        drivetrain.TankDrive(0, 0);
    }

    protected void interrupted() {
        drivetrain.TankDrive(0, 0);
    }
}