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
	
	private boolean isLogging;

	public BufferedWriterFRC() throws FileNotFoundException {

		super(new OutputStreamWriter(RobotMap.eventOutFile));
		
		try { 
			write("Time ,");
			write("Name of Event ,");
			write("Value Associated With Event ,");
			newLine();
			isLogging=true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			isLogging=false;
		}
	}

	public void writeValueFRC(String name, String value) {
		if (isLogging){
			try {
				
				write(Double.toString(RobotMap.timer.getFPGATimestamp())+",");
				write(name + ", ");
				write(value);
				newLine();
			} catch (IOException e) {
				e.printStackTrace();
				isLogging=false;
			}
		}
	}

	public void writeValueFRC(String name, double value) {
		if (isLogging){
			try {
				write(Double.toString(RobotMap.timer.getFPGATimestamp())+",");
				write(name + ", ");
				write(Double.toString(value));
				newLine();
			} catch (IOException e) {
				e.printStackTrace();
				isLogging=false;
			}
		}
	}

	public void writeValueFRC(String name, boolean value) {
		if (isLogging){
			try {
				write(Double.toString(RobotMap.timer.getFPGATimestamp())+",");
				write(name + ", ");
				if (value) 
					write("True");
				else
					write("False");
				newLine();
			} catch (IOException e) {
				e.printStackTrace();
				isLogging=false;
			}
		}
	}
	public void writeEventFRC(String name) {
		if (isLogging)
		{
			try {
				write(Double.toString(RobotMap.timer.getFPGATimestamp())+",");
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
				write(Double.toString(RobotMap.timer.getFPGATimestamp())+",");
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