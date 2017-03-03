package org.usfirst.frc.team1592.robot.commands.chassis;

import org.usfirst.frc.team1592.robot.Robot;
import org.usfirst.frc.team1592.robot.RobotMap;
import org.usfirst.frc.team1592.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveForSeconds extends Command{
	int count;
	int maxCount;
	public DriveForSeconds(int reps)
	{
		requires(Robot.chassis);
		count=0;
		maxCount=reps;
	}
	// Called just before this Command runs the first time
    protected void initialize() {
    	count=0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	count++;
    	Robot.chassis.tank(.4, .4);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (count>maxCount)
    		return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
