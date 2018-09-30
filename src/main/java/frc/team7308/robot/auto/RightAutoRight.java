package frc.team7308.robot.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.team7308.robot.auto.commands.Sleep;
import frc.team7308.robot.auto.commands.DriveForTime;
import frc.team7308.robot.auto.commands.MoveLiftToHeight;
import frc.team7308.robot.auto.commands.SetSliderPosition;
import frc.team7308.robot.auto.commands.ThrowBox;

public class RightAutoRight extends CommandGroup {
    public RightAutoRight() {
		System.out.println("Left Auto: Right");
    	addSequential(new Sleep(2));
		addParallel(new SetSliderPosition(true));
    	addSequential(new DriveForTime(2.7, -0.5, 0.5));
    	addSequential(new DriveForTime(1, -0.6, 0.0));
		addSequential(new DriveForTime(0.5, -0.5, 0.5));
		addSequential(new ThrowBox());
		addSequential(new DriveForTime(1.5, 0.5, -0.5));
		addSequential(new MoveLiftToHeight(-0.1));
    }
}