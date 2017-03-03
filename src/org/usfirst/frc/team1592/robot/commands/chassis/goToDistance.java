package org.usfirst.frc.team1592.robot.commands.chassis;

import org.usfirst.frc.team1592.robot.Robot;
import org.usfirst.frc.team1592.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class goToDistance extends Command{

	int distance;
	public goToDistance(int distance)
	{
		requires(Robot.chassis);
		this.distance=distance;
	}
    
	// Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("GoToDistance", "Started");
    	Robot.logger.writeEventFRC("Go To Distance Started");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.goToDistance();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (RobotMap.lidar.getDistance()<=this.distance)
    	{
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.logger.writeEventFRC("Go To Distance Started");
    	//Robot.chassis.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("GoUntilDistance", "Ended");
    }
}
