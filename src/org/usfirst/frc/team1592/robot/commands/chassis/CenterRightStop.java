package org.usfirst.frc.team1592.robot.commands.chassis;

import org.usfirst.frc.team1592.robot.Robot;
import org.usfirst.frc.team1592.robot.RobotMap;
import org.usfirst.frc.team1592.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CenterRightStop extends Command{
	double distance;
	public CenterRightStop(double distance)
	{
		requires(Robot.chassis);
		this.distance=distance;
	}
    
	// Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.stopOnBoilerRight(this.distance);    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (RobotMap.lidar.getDistance()>distance)
    	{
    		RobotMap.driveBase.tankDrive(0.0,0.0);
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.chassis.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
