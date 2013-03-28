/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699;

import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author Louis
 */
public class IntegrationControl2 {
    private Team3699Robot robo;
    public ToggleButton shooterToggle = new ToggleButton();
    public Jaguar elevator_out_roller = new Jaguar(Constants.elevator_outtake_PWM);
    double readyState=2.5d;
    int state = 0;
    //0=off
    //1=move elevator up
    //2=spin up outake
    //3=jiggle
    
     public IntegrationControl2(Team3699Robot robo){
         this.robo=robo;
    }
     
    public void update(){
        this.shooterToggle.update(Util.checkButton(this.robo, Constants.shoot_button));
        if (this.shooterToggle.get() && this.state==0){
            this.state=1;
            this.robo.Elevator_motor.setSafetyEnabled(false);
            this.robo.Elevator_motor.set(-0.3d);
        }else if (this.state==1){
            if (this.robo.elevator.ana_chana.getAverageVoltage()>this.readyState){
                this.state=2;
                this.robo.Elevator_motor.setSafetyEnabled(true);
                this.robo.Elevator_motor.set(0d);
            }
        }else if (this.state==2){
           this.elevator_out_roller.setSafetyEnabled(false);
           this.elevator_out_roller.set(0.8d);
           this.state=3;
        }else if (this.state==3){
            this.robo.Elevator_motor.setSafetyEnabled(false);
            this.robo.Elevator_motor.set(-0.3d);
            this.robo.elevator.numDiscs--;
        }
        
        if (this.state == 3 && this.robo.elevator.tickCounter.get()){
            this.state=0;
            this.robo.elevator.state=0;
        }
    }
}
