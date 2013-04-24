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
public class ArmControl3 {
    public Team3699Robot robo;
    double speed = 10D;
    public DigitalInput limit = new DigitalInput(Constants.armBottom);
    public int state=0;
    //0=ready
    //1=moving
    //2=stopped
    public ArmControl3(Team3699Robot robo){
        this.robo=robo;
    }
    
    public double getArmSpeed(){
        if (this.state==1){
            return -this.speed;
            
        }
        return 0D;
    }
    
    public void update(){
        if (state==0 && Util.checkButton(robo, 5)){
            state=1;
        }else if (state==1 && this.limit.get()){
            this.state=2;
        }
    }
}
