package org.usfirst.frc.team1592.robot.commands.chassis;

import org.usfirst.frc.team1592.robot.Robot;
import org.usfirst.frc.team1592.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Circle extends Command{
	public Circle()
	{
		requires(Robot.chassis);
	}
	// Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.logger.writeEventFRC("Circle: Initialize");
    	System.out.println("started");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.logger.writeEventFRC("Circle: Execute");
    	Robot.chassis.tank(1/2.0,-1/2.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Robot.logger.writeEventFRC("Circle: IsFinished");
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.drive(0, 0);
    	RobotMap.logger.writeEventFRC("Circle: End", true);
    	System.out.println("ended");
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.logger.writeEventFRC("Circle: Interrupted", true);
    	System.out.println("ended");
    }

}
