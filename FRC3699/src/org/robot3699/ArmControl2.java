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
        if (this.state==0 && Util.checkButton(this.robo, Constants.armToggleButton)){
            this.state = 1;
        }else if (this.state==1 && Util.checkButton(this.robo, Constants.armToggleButton)){
            this.state = 2;
        }else if (this.state==2 && Util.checkButton(this.robo, Constants.armToggleButton)){
            this.state = 1;
        }else if (this.state==1 && topLimit.get()){
            this.state = 0;
        }else if (this.state==2 && bottomLimit.get()){
            this.state = 0;
        }
    }
}
