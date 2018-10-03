package frc.team7308.robot.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.team7308.robot.auto.commands.Sleep;
import frc.team7308.robot.auto.commands.DriveForTime;
import frc.team7308.robot.auto.commands.MoveLiftToHeight;
import frc.team7308.robot.auto.commands.SetSliderPosition;
import frc.team7308.robot.auto.commands.ThrowBox;

// Auto Routine: Straight No Zero
// Moves forwards to touch the switch wall. Only run if using a straight throw auto but the switch is not on our side. Used for the middle positions.
// Note: DOES NOT ZERO THE ROBOT!
public class StraightAutoNoZero extends CommandGroup {
    public StraightAutoNoZero() {
		System.out.println("Straight Auto: No Zero");
		addSequential(new SetSliderPosition(true));
		addSequential(new Sleep(1));
    	addSequential(new DriveForTime(2.7, -0.5, 0.5));
		addSequential(new ThrowBox());
    }
}