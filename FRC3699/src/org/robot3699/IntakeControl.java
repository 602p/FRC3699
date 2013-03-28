/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699;

/**
 *
 * @author Bobni
 */
public class IntakeControl {
    public double intakespeed = Constants.Intake_speed;
    public double reverseintakespeed = Constants.Reverse_Intake_speed;
    public int state = 0; 
    public Team3699Robot robo;
    ToggleButton toggle = new ToggleButton();
    
    public IntakeControl(Team3699Robot robo){
        this.robo=robo;
    }
    
    public double calculateIntakeSpeed(){
        if (this.robo.elevator.numDiscs >= 4) {
            return this.reverseintakespeed;
        }
        if (this.state==1) {
            return this.intakespeed;
        }else {
            return 0d;
        }
    }
    
    public void update (){
        this.toggle.update(Util.checkButton(this.robo, Constants.intake_button));
        if (this.toggle.get() && this.state==0){
            this.state=1;
        }else if (this.toggle.get() && this.state==1){
            this.state=0;
        }
    }
    
    
}
