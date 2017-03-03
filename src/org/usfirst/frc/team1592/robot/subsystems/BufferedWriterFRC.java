package org.usfirst.frc.team1592.robot.subsystems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.usfirst.frc.team1592.robot.RobotMap;

public class BufferedWriterFRC extends BufferedWriter {

	private static final File logPath = new File("/u/logs/");
	private static final LocalDateTime dateTime = LocalDateTime.now();
	private static final File outFile=new File(logPath.toString() + "/log_" + 
			dateTime.format(DateTimeFormatter.ofPattern("uu_MM_dd_HH_mm_ss")) + ".csv");
	private boolean isLogging;
	private double time;

	public BufferedWriterFRC() throws FileNotFoundException {

		super(new OutputStreamWriter(new FileOutputStream(outFile)));
		isLogging=true;
		try { 
			write("Time ,");
			write("Name of Event ,");
			write("Value Associated With Event ,");
			newLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void writeValueFRC(String name, String value) {
		if (isLogging){
			try {
				time=(RobotMap.timer.getFPGATimestamp());
				write(Double.toString(time)+",");
				write(name + ", ");
				write(value);
				newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeValueFRC(String name, double value) {
		if (isLogging){
			try {
				time=(RobotMap.timer.getFPGATimestamp());
				write(Double.toString(time)+",");
				write(name + ", ");
				write(Double.toString(value));
				newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeValueFRC(String name, boolean value) {
		if (isLogging){
			try {
				time=(RobotMap.timer.getFPGATimestamp());
				write(Double.toString(time)+",");
				write(name + ", ");
				if (value) 
					write("True");
				else
					write("False");
				newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void writeEventFRC(String name) {
		if (isLogging)
		{
			try {
				time=(RobotMap.timer.getFPGATimestamp());
				write(Double.toString(time)+",");
				write(name + ",");
				System.out.println("Done Writing");
				newLine();
			} 
			catch (IOException e) 
			{
				System.out.println("Failed");
				e.printStackTrace();
				isLogging=false;
			}
		}
	}
	public void writeEventFRC(String name, boolean shouldFlush) {
		if (isLogging)
		{
			try {
				time=(RobotMap.timer.getFPGATimestamp());
				write(Double.toString(time)+",");
				write(name + ",");
				System.out.println("Done Writing");
				newLine();
				if (shouldFlush)
					flush();
			} 
			catch (IOException e) 
			{
				System.out.println("Failed");
				e.printStackTrace();
				isLogging=false;
			}
		}
	}
	public void forceFlush()
	{
		if (isLogging){
			try {
				flush();
			} catch (IOException e) {
				isLogging=false;
				e.printStackTrace();
			}
		}
	}
}