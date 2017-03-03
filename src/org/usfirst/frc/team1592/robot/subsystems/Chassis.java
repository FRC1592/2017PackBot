
package org.usfirst.frc.team1592.robot.subsystems;

import org.usfirst.frc.team1592.robot.Constants;
import org.usfirst.frc.team1592.robot.RobotMap;
import org.usfirst.frc.team1592.robot.commands.chassis.DriveWithJoysticks;
import org.usfirst.frc.team1592.robot.subsystems.Arduino;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Chassis extends Subsystem {
	CANTalon leftMotor = RobotMap.motorLeft;
	CANTalon rightMotor = RobotMap.motorRight;
	double distanceError;
	double velocity;
	double distance;
	double visionErri;
	double visionCommand;
	private Arduino arduino=RobotMap.arduino;
	public double visionErr;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {

		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveWithJoysticks());
	}
	public void driveWithLidar(double distance)
	{
		double distanceError=(RobotMap.lidar.getDistance()-distance);
		double velocity=distanceError*.02;
		SmartDashboard.putNumber("Distance Error: ",distanceError);
		SmartDashboard.putNumber("Velocity: ",velocity);
		RobotMap.driveBase.tankDrive(velocity, velocity);
	}
	public void drive (double x,double y)
	{
		RobotMap.driveBase.arcadeDrive(-x,y);
	}

	public void tank (double x, double y)
	{
		RobotMap.driveBase.tankDrive(x, y);
	}
	public void stopOnBoilerLeft(double distance)
	{
		this.distance=distance;
		double velocity=-0.4;
		if (RobotMap.lidar.getDistance()<distance)
		{
			RobotMap.driveBase.tankDrive(velocity, velocity);
		}

	}
	public void stopOnBoilerRight(double distance)
	{
		this.distance=distance;
		double velocity=0.4;
		if (RobotMap.lidar.getDistance()<distance)
		{
			RobotMap.driveBase.tankDrive(velocity, velocity);
		}

	}

	public void turnToCenterVision()
	{
		visionErr=(double)arduino.ReadServos();
		//0 to 319
		SmartDashboard.putNumber("Arduino Vision Error", visionErr);
		visionErr-=160; //-160 to 159
		visionErr=visionErr/160.0; //-1 to 1
		visionErri=visionErri+visionErr;
		visionCommand=(visionErr*1.5)+(visionErri*.01);
		SmartDashboard.putNumber("Arduino Vision Integral", visionErri);
		SmartDashboard.putNumber("Arduino Vision Command", visionCommand);
		RobotMap.driveBase.arcadeDrive(-0.4,-visionCommand); //Drive 
		//RobotMap.driveBase.mecanumDrive_Cartesian(0, 0, (visionErr-50.)/50., 0.0);
	}
	public void roborioCenter()
	{
		visionErr=(double)arduino.ReadServos(); //0 to 319
		SmartDashboard.putNumber("Arduino Vision Error", visionErr);
		System.out.println(visionErr);//Target Number 45
		if (visionErr<Constants.pixyCenter)//right
		{
			RobotMap.driveBase.arcadeDrive(-0.1,visionErr/(2*Constants.pixyCenter));	    	
		}
		else if (visionErr>Constants.pixyCenter)
		{
			RobotMap.driveBase.arcadeDrive(-0.1,-visionErr/(2*(Constants.pixyMax-Constants.pixyCenter)));
		}

	}
	public void goToDistance()
	{
		RobotMap.driveBase.tankDrive(.4, .4);
		SmartDashboard.putNumber("Lidar Distance", RobotMap.lidar.getDistance());
	}
	public void LidarPixy()
	{
		double dist=RobotMap.lidar.getDistance();
		double cent=RobotMap.arduino.ReadServos();
		SmartDashboard.putNumber("Pixy Run", cent);
		double speed=((dist-Constants.targetDistance)/76);
		double turn=((cent-160)/160.0);
		SmartDashboard.putNumber("Turn", turn);
		if (speed>=.6)
		{
			speed=.6;
		}
		else if (speed<=-.6)
		{
			speed=-.6;
		}
		
		RobotMap.driveBase.arcadeDrive(-speed,-turn*2);
	}
	

	public boolean isFinished()
	{
		return false;
	}
}

