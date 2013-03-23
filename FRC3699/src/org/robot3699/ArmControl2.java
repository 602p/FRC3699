/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Louis
 */
public class ArmControl2 {
    Team3699Robot robo;
    double armSpeed = 0.3d;
    int state=0;
    ToggleButton toggle = new ToggleButton();
    DigitalInput bottomLimit=new DigitalInput(Constants.armBottom);
    DigitalInput topLimit = new DigitalInput(Constants.armTop);
    
    ArmControl2(Team3699Robot robo){
        this.robo=robo;
    }
    
    public double getArmSpeed(){
        if (this.state == 1){
            return this.armSpeed;
        }else if (this.state == 2){
            return -this.armSpeed;
        }else{
            return 0.0d;
        }
    }
    
    public void update(){
        this.toggle.update(Util.checkButton(robo, Constants.armToggleButton));
        if (this.state==0 && toggle.get()){
            this.state = 1;
        }else if (this.state==3 && !toggle.get()){  //Used not operator in order 
            this.state = 2;                         //to stop a cycling effect.                    
        }else if (this.state==1 && !toggle.get()){  //Double Check please =).
            this.state = 2;
        }else if (this.state==2 && toggle.get()){
            this.state = 1;
        }else if (this.state==1 && topLimit.get()){
            this.state = 3;
        }else if (this.state==2 && bottomLimit.get()){
            this.state = 0;
        }
    }
}
