package frc.team7308.robot.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.team7308.robot.auto.commands.Sleep;
import frc.team7308.robot.auto.commands.DriveForTime;
import frc.team7308.robot.auto.commands.MoveLiftToHeight;

public class LeftAutoLeft extends CommandGroup {
    public LeftAutoLeft() {
		System.out.println("test");
    	addSequential(new Sleep(2));
    	addSequential(new DriveForTime(2.7, -0.5, 0.5));
    	addSequential(new DriveForTime(1, 0.0, 0.5));
		addSequential(new DriveForTime(0.5, -0.5, 0.5));
    }
}