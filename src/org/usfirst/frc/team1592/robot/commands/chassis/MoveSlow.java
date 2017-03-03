package org.usfirst.frc.team1592.robot.commands.chassis;


import org.usfirst.frc.team1592.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveSlow extends Command{
	public MoveSlow()
	{
		requires(Robot.chassis);
	}
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.logger.writeEventFRC("Move Slow: Initialize");
    	System.out.println("started");
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.tank(.4,.4);
    	//Robot.logger.writeEventFRC("Move Slow: Execute");
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Robot.logger.writeEventFRC("Move Slow: IsFinished");
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.drive(0, 0);
    	Robot.logger.writeEventFRC("Move Slow: End", true);
    	System.out.println("ended");
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.logger.writeEventFRC("Move Slow: Interrupted", true);
    }
}
