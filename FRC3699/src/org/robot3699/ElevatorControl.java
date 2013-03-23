/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//TOTALLY INCOMPLETE

package org.robot3699;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Louis
 */
public class ElevatorControl {
    public DigitalInput tickCounter = new DigitalInput(Constants.elevator_tick_sensor);
    Team3699Robot robo;
    public double elevatorSpeed= 0.25;
    
    public ElevatorControl(Team3699Robot robo){
        this.robo=robo;
    }
    
    public void getElevatorSpeed(){
        
    }
}
