package org.usfirst.frc.team1592.robot.commands.chassis;

import java.io.IOException;
import java.util.function.DoubleSupplier;

import org.usfirst.frc.team1592.robot.Robot;
import org.usfirst.frc.team1592.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class WriteData extends Command{
	public WriteData()
	{
		//requires(RobotMap.loggerData);
		
	}
	// Called just before this Command runs the first time
    protected void initialize() {
    	DoubleSupplier y=()->{return Robot.oi.driver.getY();};
    	DoubleSupplier pi = () -> {return 3.14159;};
    	RobotMap.loggerData.registerDoubleStream("Joystick Y", "Push",y);
    	RobotMap.loggerData.endRegistration();
    	try {
			RobotMap.loggerData.outputHeader();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
			RobotMap.loggerData.outputData();
			System.out.println("Writing");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }

}
