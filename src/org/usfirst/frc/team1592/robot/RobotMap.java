package org.usfirst.frc.team1592.robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

	public static CANTalon motorLeft=new CANTalon(Constants.LEFT);
	public static CANTalon motorRight=new CANTalon(Constants.RIGHT);
	public static RobotDrive driveBase=new RobotDrive(motorLeft, motorRight);
	public static LIDAR lidar = new LIDAR();
	public static Arduino arduino=new Arduino();
	public static HardwareTimer timer=new HardwareTimer();

	public static final File logPath = new File("/u/logs/");
	
	//Event Writer
	public static final LocalDateTime day = LocalDateTime.now();
	public static FileOutputStream eventOutFile;
	public static BufferedWriterFRC logger;
	
	//Data Loggers
	public static FileOutputStream dataOutFile;
	public static BufferedWriterFRC dataLogger;
	public static RobotTelemetry loggerData; 
	

}
