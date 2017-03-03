package org.usfirst.frc.team1592.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Arduino extends SensorBase{
    
    I2C m_i2c;
         
    public Arduino() {  
    	m_i2c = new I2C(I2C.Port.kMXP,0x65);
    	initArduino();     	          	  
    }
    
    public void initArduino(){		
        // nothing to do
 	}
	
	public int ReadServos() {
		 
		byte[] buffer;

//		buffer = new byte[4];
		buffer = new byte[2];
		int pan;
		int out=500;
	 
		m_i2c.readOnly(buffer, 2); 	
		
		pan =(int)(buffer[0] << 8 | Byte.toUnsignedInt(buffer[1]));
		SmartDashboard.putNumber("Pan: ", pan);
//		tilt=(int)(buffer[2] << 8 | Byte.toUnsignedInt(buffer[3]));

		out=pan;
//		out[1]=tilt;
		SmartDashboard.putNumber("Out: ", out);
		return out;
  		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

