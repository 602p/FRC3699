/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699;

/**
 *
 * @author Louis
 */
public class ShooterControl {
    public double[] shooterSpeedLevels = new double[5];
    public double shooterSpeedMultiplier = 1.0d;
    public int shooterSpeedState = 0;
    public boolean  toggleButtonState = false;
    
    {
        this.shooterSpeedLevels[0]=0;
        this.shooterSpeedLevels[1]=1;
        this.shooterSpeedLevels[2]=2;
        this.shooterSpeedLevels[3]=3;
        this.shooterSpeedLevels[4]=4;
        this.shooterSpeedLevels[5]=5;
    }
    
    public double calculateShooterSpeed(){
        return this.shooterSpeedLevels[this.shooterSpeedState]*this.shooterSpeedMultiplier;
    }
    
    public void updateStates(Team3699Robot robo){
        if (robo.joystick_left.getRawButton(Constants.shooterToggleButton) && !this.toggleButtonState){
            this.toggleButtonState=true;
            this.shooterSpeedState++;
            if (this.shooterSpeedState>this.shooterSpeedLevels.length){
                this.shooterSpeedState=0;
            }
        }else if (!robo.joystick_left.getRawButton(Constants.shooterToggleButton) && this.toggleButtonState){
            this.toggleButtonState=false;
        }
        
        this.shooterSpeedMultiplier=robo.driverstation.getAnalogIn(Constants.shooterMultChannel);
    }
}
