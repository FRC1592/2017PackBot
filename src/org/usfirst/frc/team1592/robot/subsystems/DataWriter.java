package org.usfirst.frc.team1592.robot.subsystems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.omg.PortableInterceptor.ObjectIdHelper;
import org.usfirst.frc.team1592.robot.Robot;
import org.usfirst.frc.team1592.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;

public class DataWriter extends BufferedWriter {

	private static final File logPath = new File("/u/logs/");
	private static final LocalDateTime dateTime = LocalDateTime.now();
	private static final File outFile=new File(logPath.toString() + "/data" + 
			dateTime.format(DateTimeFormatter.ofPattern("uu_MM_dd_HH_mm_ss")) + ".csv");
	private boolean isLogging;
	private double [] data;
	private int dataLen;

	public DataWriter() throws FileNotFoundException {

		super(new OutputStreamWriter(new FileOutputStream(outFile)));
		isLogging=true;
		dataLen=0;
		try { 
			write("Time ,");
			dataLen++;
			write("LIDAR Left ,");
			dataLen++;
			write("LIDAR Right ,");
			dataLen++;
			write("Pixy ,");
			dataLen++;
			write("Gyro ,");
			dataLen++;
			write("Accelerometer ,");
			dataLen++;
			write("Left Joystick,");
			dataLen++;
			write("Right Joystick,");
			dataLen++;
			newLine();
		}
		catch (IOException e)
		{
			isLogging=false;
			e.printStackTrace();
		}
	}
	public void populate()
	{
		try
		{
			write(Double.toString(Timer.getFPGATimestamp())+",");
			write(Double.toString(RobotMap.lidar.getDistance())+",");
			write(Double.toString(RobotMap.lidar.getDistance())+",");
			write(Double.toString(RobotMap.arduino.ReadServos())+",");
			write(Double.toString(1.0)+",");
			write(Double.toString(1.0)+",");
			write(Double.toString(Robot.oi.driver.getY())+",");
			write(Double.toString(Robot.oi.driver.getU())+",");
			newLine();
		}
		catch (IOException e)
		{
			isLogging=false;
			e.printStackTrace();
		}
	}
}