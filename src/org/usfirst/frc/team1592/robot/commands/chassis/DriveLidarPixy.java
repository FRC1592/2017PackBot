package org.usfirst.frc.team1592.robot.commands.chassis;

import org.usfirst.frc.team1592.robot.Constants;
import org.usfirst.frc.team1592.robot.Robot;
import org.usfirst.frc.team1592.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveLidarPixy extends Command{
	public DriveLidarPixy()
	{
		requires(Robot.chassis);
	}
	// Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.logger.writeEventFRC("DriveLidarPixy: Initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.LidarPixy();
    	RobotMap.logger.writeEventFRC("DriveLidarPixy: Execute");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	RobotMap.logger.writeEventFRC("DriveLidarPixy: IsFinished");
    	if (RobotMap.lidar.getDistance()<=Constants.targetDistance)
    	{
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.drive(0, 0);
    	RobotMap.logger.writeEventFRC("DriveLidarPixy: End", true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.logger.writeEventFRC("Circle: Interrupted", true);
    	System.out.println("ended");
    }

}
