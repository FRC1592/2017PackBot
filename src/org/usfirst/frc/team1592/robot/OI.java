package org.usfirst.frc.team1592.robot;

import edu.wpi.first.wpilibj.buttons.Button;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

import com.qtech.first.qLibs.joysticks.XBOXJoystick;

import org.usfirst.frc.team1592.robot.commands.chassis.CenterWithArduino;
import org.usfirst.frc.team1592.robot.commands.chassis.CenterWithRoborio;
import org.usfirst.frc.team1592.robot.commands.chassis.Circle;
import org.usfirst.frc.team1592.robot.commands.chassis.DriveForSeconds;
import org.usfirst.frc.team1592.robot.commands.chassis.DriveLidarPixy;
//import org.usfirst.frc.team1592.robot.commands.chassis.Circle;
import org.usfirst.frc.team1592.robot.commands.chassis.HoverWithinDistance;
import org.usfirst.frc.team1592.robot.commands.chassis.MoveSlow;
import org.usfirst.frc.team1592.robot.commands.chassis.PrintPixyLidar;
//import org.usfirst.frc.team1592.robot.commands.chassis.LeftPivot;
//import org.usfirst.frc.team1592.robot.commands.chassis.MoveSlow;
import org.usfirst.frc.team1592.robot.commands.chassis.goToDistance;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	// Logitech Joystick
	//public static Joystick driver=new Joystick(0);
	
	// XBox Joystick
	public XBOXJoystick driver=new XBOXJoystick(0);
	
	 //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
	//public Button circle = new JoystickButton(driver, 1);
	//public Button goSlow = new JoystickButton(driver, 2);
	//public Button pivotLeft = new JoystickButton(driver, 7);
	
	//Button X: Drives slowly backwards until a space is found.
	//Button B: Drives slowly forwards until a space is found.
	//Button A: Drives for a given number of counts.
	//Button Y: Keeps a set distance away from an object..
	
	//public Button robRightPress = new JoystickButton(driver, 1);
	public Button printer=new JoystickButton(driver, 1);
	public Button circleGo=new JoystickButton(driver,2);
	public Button slow=new JoystickButton(driver, 3);
	public Button drivePixyLidar=new JoystickButton(driver, 4);
	//public Button arduinoCenter = new JoystickButton(driver, 5);
	//public Button driveDistance = new JoystickButton(driver, 6);
	//public Button ardCenterPress = new JoystickButton(driver, 7);
	//public Button robCenterPress = new JoystickButton(driver, 8);
	
	//public Button seeCross= new JoystickButton(driverbox,11);
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public OI()
	{
		//goSlow.whileHeld(new MoveSlow());
		//circle.whileHeld(new Circle());
		//pivotLeft.whileHeld(new LeftPivot());
		//countSeconds.whenPressed(new DriveForSeconds(100));
		//lidarDrive.whileHeld(new HoverWithinDistance(30)); 
		//leftStop.whenPressed(new CenterLeftStop(30)); Useless as Lidar faces forwards
		//rightStop.whenPressed(new CenterRightStop(30)); Useless as Lidar faces forwards
		//robRightPress.whenPressed(new CenterWithRoborio());
		//arduinoCenter.whileHeld(new CenterWithArduino());
		//driveDistance.whenPressed(new goToDistance(30));
		//ardCenterPress.whenPressed(new CenterWithArduino());
		//robCenterPress.whileHeld(new CenterWithRoborio());
		circleGo.whileHeld(new Circle());
		slow.toggleWhenPressed(new MoveSlow());
		printer.whileHeld(new PrintPixyLidar());
		drivePixyLidar.whileHeld(new DriveLidarPixy());
	}
}

