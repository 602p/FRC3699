/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//TOTALLY INCOMPLETE

package org.robot3699;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Louis
 */
public class ElevatorControl {
    public DigitalInput tickCounter = new DigitalInput(Constants.elevator_tick_sensor);
    public DigitalInput intakeCounter = new DigitalInput(Constants.intake_tick_sensor);
    Team3699Robot robo;
    public double elevatorSpeed= Constants.Elevator_speed;
    public int state = 0;
    public double cutOff=2.5d;
    public double backUp=2d;
    public AnalogChannel ana_chana = new AnalogChannel(5);
    public int numDiscs = 0;
    public ToggleButton tickCounterToggle = new ToggleButton();
    
    
    //0=Stopped (wait for frisbee)
    //1=Moving (up)
    //2=E-Stop <-- No Reset!
    
    public ElevatorControl(Team3699Robot robo){
        this.robo=robo;
        this.robo.Elevator_motor.setSafetyEnabled(false);
    }
    
    public double getElevatorSpeed(){
        if (this.state == 1){
            return this.elevatorSpeed;
        }else{
            return 0d;
        }
    }
    
    public void update(){
        //Check for cutOff
        this.tickCounterToggle.update(this.tickCounter.get());
        if (!(this.state == 2) && this.ana_chana.getAverageVoltage()>this.cutOff){
            this.robo.log("WARNING! Visual sensor max exceeded! Elevator Component E-Stopped.");
            this.state=2;
        }else if (this.state==0){
            if (!this.intakeCounter.get()){
                this.state=1;
                this.numDiscs++;
            }
        }else if (this.state==1){
            if (this.tickCounterToggle.get()){
                this.state=0;
            }
        }
    }
    
    public void resetState(){
        this.state=0;
        this.robo.log("RESETTING ELEVATOR VARIBLE");
    }
}
