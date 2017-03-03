package org.usfirst.frc.team1592.robot.commands.chassis;

import org.usfirst.frc.team1592.robot.Robot;
import org.usfirst.frc.team1592.robot.RobotMap;
import org.usfirst.frc.team1592.robot.subsystems.Chassis;
import org.usfirst.frc.team1592.robot.Constants;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CenterWithRoborio extends Command{
	public CenterWithRoborio()
	{
		requires(Robot.chassis);
	}
    
	// Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.roborioCenter();
    	   	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if ((int)RobotMap.arduino.ReadServos()==(int)Constants.pixyCenter)
    	{
    		return true;
    	}
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
