package frc.team7308.robot.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.team7308.robot.auto.commands.Sleep;
import frc.team7308.robot.auto.commands.DriveForTime;
import frc.team7308.robot.auto.commands.MoveLiftToHeight;
import frc.team7308.robot.auto.commands.SetSliderPosition;
import frc.team7308.robot.auto.commands.ThrowBox;

public class LeftAutoRight extends CommandGroup {
    public LeftAutoLeft() {
		System.out.println("Left Auto: Right");
    	addSequential(new DriveForTime(2.7, -0.5, 0.5));
		addSequential(new MoveLiftToHeight(-0.1));
    }
}