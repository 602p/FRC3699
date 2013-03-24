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
    public ToggleButton button = new ToggleButton();
    
    {
        this.shooterSpeedLevels[0]=0;
        this.shooterSpeedLevels[1]=1;
        this.shooterSpeedLevels[2]=2;
        this.shooterSpeedLevels[3]=3;
        this.shooterSpeedLevels[4]=4;
    }
    
    public double calculateShooterSpeed(){
        System.out.println("wtf?");
        return this.shooterSpeedLevels[this.shooterSpeedState]*this.shooterSpeedMultiplier;
    }
    
    public void updateStates(Team3699Robot robo){
        System.out.println("ftw!");
        this.button.update(Util.checkButton(robo, Constants.shooterToggleButton));
        if (this.button.get()){
            this.shooterSpeedState++;
            if (this.shooterSpeedState>4){
                this.shooterSpeedState=0;
            }
        }
        this.shooterSpeedMultiplier=robo.driverstation.getAnalogIn(Constants.shooterMultChannel);
    
}
}