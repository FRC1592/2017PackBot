package org.usfirst.frc.team1592.robot;

import edu.wpi.first.wpilibj.buttons.Button;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

import com.qtech.first.qLibs.joysticks.XBOXJoystick;

import org.usfirst.frc.team1592.robot.commands.chassis.Circle;
import org.usfirst.frc.team1592.robot.commands.chassis.DriveLidarPixy;
import org.usfirst.frc.team1592.robot.commands.chassis.MoveSlow;
import org.usfirst.frc.team1592.robot.commands.chassis.PrintPixyLidar;
import org.usfirst.frc.team1592.robot.commands.chassis.WriteData;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	// Logitech Joystick
	//public static Joystick driver=new Joystick(0);
	
	// XBox Joystick
	public XBOXJoystick driver=new XBOXJoystick(0);
	

	public Button printer=new JoystickButton(driver, 1);
	public Button circleGo=new JoystickButton(driver,2);
	public Button slow=new JoystickButton(driver, 3);
	public Button drivePixyLidar=new JoystickButton(driver, 4);
	
	public Button piTry=new JoystickButton(driver, 5);

	
	public OI()
	{
		circleGo.whileHeld(new Circle());
		slow.toggleWhenPressed(new MoveSlow());
		printer.whileHeld(new PrintPixyLidar());
		drivePixyLidar.whileHeld(new DriveLidarPixy());
		piTry.whenPressed(new WriteData());
	}
}

