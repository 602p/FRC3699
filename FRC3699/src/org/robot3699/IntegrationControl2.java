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
    public double takeDownTo = 2.25;
    int state = 0;
    //0=off
    //1=move elevator up
    //2=spin up outake
    //3=jiggle
    
    private class TimedJiggleThread implements Runnable{
        private final Team3699Robot robo;
        public TimedJiggleThread(Team3699Robot robo){
            this.robo=robo;
        }
        public void run(){
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ex) {}
            this.robo.Elevator_motor.set(0d);
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException ex) {}
            this.robo.integ.state=4;
        }
    }
    
     public IntegrationControl2(Team3699Robot robo){
         this.robo=robo;
    }
     
    public void update(){
        this.shooterToggle.update(Util.checkButton(this.robo, Constants.shoot_button));
        if (this.shooterToggle.get() && this.state==0){
            this.state=1;
            this.robo.Elevator_motor.setSafetyEnabled(false);
            this.robo.Elevator_motor.set(0.5d);
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
           this.elevator_out_roller.setSafetyEnabled(false);
           this.robo.Elevator_motor.setSafetyEnabled(false);
           this.robo.Elevator_motor.setSafetyEnabled(false);
        }else if (this.state==3){
            
            this.robo.Elevator_motor.set(-0.4);
            
            this.elevator_out_roller.set(0.9d);
            
            if (this.robo.elevator.ana_chana.getAverageVoltage()<this.takeDownTo){
                this.state=4;
            }
        }
        
        if (this.state == 4){
            this.robo.elevator.numDiscs--;
            this.state=0;
            this.robo.elevator.state=0;
          //  this.elevator_out_roller.setSafetyEnabled(true);
            this.robo.Elevator_motor.setSafetyEnabled(true);
          //  this.elevator_out_roller.set(0d);
            this.robo.Elevator_motor.set(0d);
        }
        
        if (this.state!=0){
            this.elevator_out_roller.set(0.9d);
        }
    }
    
    public boolean doElevatorUpdate(){
        return !(this.state==1);
    }
}
