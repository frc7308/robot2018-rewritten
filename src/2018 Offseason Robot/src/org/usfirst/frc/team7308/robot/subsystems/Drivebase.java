package org.usfirst.frc.team7308.robot.subsystems;

import static java.util.Objects.requireNonNull;
import java.lang.Math;

import java.util.TimerTask;

import org.usfirst.frc.team7308.robot.Robot;
import org.usfirst.frc.team7308.robot.commands.TurnToAngle;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.PIDController;

// Basic arcade drive drivebase with PID
public class Drivebase extends Subsystem {
	
	public double deadzoneOffset;
	
	private static final double kP_Rotate = 0.1;
	private static final double wheelDistance = 0.0;
	
	private double movementSpeed;
	private double rotationSpeed;
	
	private SpeedControllerGroup m_left;
	private SpeedControllerGroup m_right;
	
	private java.util.Timer controlLoop;
	private static final double period = 0.05;
	
    private double P = 0.1;
    private double I = 0.001;
    private double D = 0.0;
    private double integral, previous_error, setpoint = 0;
    private int acceptable_error = 1;
    private boolean PID_enabled = false;
	
	// 4 Motor drivetrain
	public Drivebase(int frontLeftMotorPort, int rearLeftMotorPort, int frontRightMotorPort, int rearRightMotorPort) {
	   Spark m_frontLeft = new Spark(frontLeftMotorPort);
	   Spark m_rearLeft = new Spark(rearLeftMotorPort);
	   m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

	   Spark m_frontRight = new Spark(frontRightMotorPort);
	   Spark m_rearRight = new Spark(rearRightMotorPort);
	   m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);

	   controlLoop = new java.util.Timer();
	   controlLoop.schedule(new DriveTask(this), 0L, (long) (period * 1000));
	}

	public void initDefaultCommand() {

	}
	
	// Move the drivebase wheels in Arcade Drive
	public void drive(double movement, double rotation) {
		double movementSpeed = movement;
		double rotationSpeed = rotation;
		if (movement > 0.0) movementSpeed += deadzoneOffset;
		if (rotation > 0.0) rotationSpeed += deadzoneOffset;
	}
	
	public void turnToAngle(double angle) {
		PID_enabled = true;
		setpoint = angle;
		
		m_left.set(1);
	}
	
	public void setMovementSpeed(double movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public void setDeadzoneOffset(double deadzoneOffset) {
		this.deadzoneOffset = deadzoneOffset;
	}
	
	public double getDeadzoneOffset() {
		return deadzoneOffset;
	}
	
	// PID
	
    public void PID() {
        double error = setpoint - Robot.globalGyro.getAngle();
        if (Math.abs(error) < this.acceptable_error) {
        	this.rotationSpeed = 0.0;
        	PID_enabled = false;
        } else {
	        this.integral += (error * period);
	        double derivative = (error - this.previous_error) / period;
	        this.rotationSpeed = P * error + I * this.integral + D * derivative;
        }
    }
    
    public void setSetpoint(int setpoint) {
        this.setpoint = setpoint;
    }
	
	// Control Loop
	
	public void update() {
		m_left.set(movementSpeed + rotationSpeed);
		m_right.set(movementSpeed - rotationSpeed);
	}
	
	private class DriveTask extends TimerTask {
	    private Drivebase drivebase;

	    DriveTask(Drivebase drivebase) {
	      this.drivebase = drivebase;
	    }

	    @Override
	    public void run() {
	      drivebase.update();
	      if (PID_enabled) drivebase.PID();
	    }
	}
	
	public void stopControlLoop() {
		controlLoop.cancel();
	}
	
	public void startControlLoop() {
		controlLoop.schedule(new DriveTask(this), 0L, (long) (period * 1000));
	}
	
}
