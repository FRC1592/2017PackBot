#include <Pixy.h>
#include <Servo.h> 
#include <SPI.h>  
#include <Wire.h>

Pixy pixy;
//Servo pan,tilt;  // create servo object to control a servo 
                // twelve servo objects can be created on most boards
 
int posTilt = 500;    // variable to store the servo position 
int posPan = 500;    // variable to store the servo position 
int errP=0;
int errT=0;
int errPold=0;
int errTold=0;
int errPd=0;
int errTd=0;
int errPi=0;
int errTi=0;

#define X_CENTER        ((PIXY_MAX_X-PIXY_MIN_X)/2)       

#define Y_CENTER        ((PIXY_MAX_Y-PIXY_MIN_Y)/2)
 
void setup() 
{ 
  
  Wire.begin(0x65);
  Wire.onRequest(requestEvent);
  
  //Serial.begin(9600);
//  Serial.print("Starting...\n");
  
  pixy.init();
  //pixy.setServos(500, 500);

} 
 
void loop() 
{ 
    uint16_t blocks;

    // grab blocks!
    blocks = pixy.getBlocks();

    if (blocks>1)
    {
//      for (int i=0;i<blocks;i++)
//      {
//        errP+=pixy.blocks[i];
//      }
//      errP=errP/blocks;
      errP=(pixy.blocks[0].x+pixy.blocks[1].x)/2;
    }
    else if (blocks==1)
    {
      errP=pixy.blocks[0].x;
    }
//      errPd=errP-errPold;
//      errPi=errPi+errP;
//      
//      errT=pixy.blocks[0].y-Y_CENTER;
//      errTd=errT-errTold;
//      errTi=errTi+errT;
//
//      posPan=posPan + errP*0.5 - errPd*0.2;
//      posTilt=posTilt + errT*0.4 - errTd*0.1;
//
//      errPold=errP;
//      errTold=errT;
//
//      posPan=constrain(posPan, 0, 1000);    
//      posTilt=constrain(posTilt, 300, 900);
      
      //pixy.setServos(posPan, posTilt);
      
//    else
//    {
//        errP=0;
//        errPi=0;
//        errPold=0;
//        errT=0;
//        errTi=0;
//        errTold=0;
//    }
    //Serial.println(errP);
                          
} 

void requestEvent() {
  byte buffer[2];
  buffer[0]=highByte(errP);
  buffer[1]=lowByte(errP);
  //buffer[2]=highByte(posTilt);
  //buffer[3]=lowByte(posTilt);
 
  Wire.write(buffer,2);
}
/*
int validBlock(uint16_t blocksIn){
  float AR;
  for(int i=1; i<blocksIn; i++){
    AR=float(pixy.blocks[i].width)/float(pixy.blocks[i].height);
  }
  
}*/

