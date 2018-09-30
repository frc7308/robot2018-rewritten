package frc.team7308.robot.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.team7308.robot.auto.commands.Sleep;
import frc.team7308.robot.auto.commands.DriveForTime;
import frc.team7308.robot.auto.commands.MoveLiftToHeight;
import frc.team7308.robot.auto.commands.SetSliderPosition;
import frc.team7308.robot.auto.commands.ThrowBox;

public class StraightAuto extends CommandGroup {
    public StraightAuto() {
			System.out.println("Straight Auto");
			addSequential(new SetSliderPosition(true));
			addSequential(new Sleep(1));
    	addSequential(new DriveForTime(2.7, -0.5, 0.5));
			addParallel(new MoveLiftToHeight(-0.1));
    }
}