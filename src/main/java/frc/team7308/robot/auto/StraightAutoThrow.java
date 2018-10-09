package frc.team7308.robot.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.team7308.robot.auto.commands.Sleep;
import frc.team7308.robot.auto.commands.DriveForTime;
import frc.team7308.robot.auto.commands.MoveLiftToHeight;
import frc.team7308.robot.auto.commands.SetSliderPosition;
import frc.team7308.robot.auto.commands.ThrowBox;

// Auto Routine: Straight Throw
// Moves forwards to touch the switch wall and throws a box. Used for the middle positions.
// DOES NOT ZERO THE ROBOT!
public class StraightAutoThrow extends CommandGroup {
    public StraightAutoThrow() {
        System.out.println("Straight Auto: Throw");
        addSequential(new DriveForTime(2.7, -0.5, 0.5));
        addParallel(new SetSliderPosition(true));
		addParallel(new MoveLiftToHeight(0.25));
        addSequential(new ThrowBox());
    }
}