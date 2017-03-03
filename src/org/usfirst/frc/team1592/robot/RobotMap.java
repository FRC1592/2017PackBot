package org.usfirst.frc.team1592.robot;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.function.Supplier;
import java.util.function.DoubleSupplier;

import org.usfirst.frc.team1592.robot.subsystems.Arduino;
import org.usfirst.frc.team1592.robot.subsystems.BufferedWriterFRC;
import org.usfirst.frc.team1592.robot.subsystems.LIDAR;
import org.usfirst.frc.team1592.robot.subsystems.RobotTelemetry;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.internal.HardwareTimer;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
    public static CANTalon motorLeft=new CANTalon(Constants.LEFT);
	public static CANTalon motorRight=new CANTalon(Constants.RIGHT);
	public static RobotDrive driveBase=new RobotDrive(motorLeft, motorRight);
	public static LIDAR lidar = new LIDAR();
	public static Arduino arduino=new Arduino();
	public static HardwareTimer timer=new HardwareTimer();
	
	//telemetry trying stuff out
	public static FileOutputStream o;
	private FileOutputStream file;
	public static Supplier<Double> timekeeper=()->{return timer.getFPGATimestamp();};
	public static RobotTelemetry telemetry=new RobotTelemetry(o, (DoubleSupplier) timekeeper);
	
	//trying something different out
	
	
	public void robotInit() {
		try {
			file = new FileOutputStream("test.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			o = new FileOutputStream("test.csv");
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	private RobotTelemetry loggerData = new RobotTelemetry(file, () -> timer.getFPGATimestamp());
}
