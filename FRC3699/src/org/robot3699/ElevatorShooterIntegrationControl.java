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
public class ElevatorShooterIntegrationControl {
    public Team3699Robot robo;
    public Jaguar elevator_out_roller = new Jaguar(Constants.elevator_outtake_PWM);
    public int elevatorState=0;
    
    //0=Idle
    //1=Bringing Elevator Up / Idle
    //2=Bringing Elevator Up / Done / Idle
    //3=N/A
    
    public int globalState=0;
    
    //0=Idle
    //1=Elevator Operations --> elevatorState (==> 2)
    //2=Jiggle Operations -->jiggleState?
    //3=Shoot! (for reals)
    
    public ToggleButton shootToggle = new ToggleButton();
    
    public ElevatorShooterIntegrationControl(Team3699Robot robo){
        this.robo=robo;
    }
    
    private class bringElevatorUpToShootWrapper implements Runnable{
        Team3699Robot robo;
        //ElevatorShooterIntegrationControl ctrl;
        double goodPlaceToShoot=2d;
        public bringElevatorUpToShootWrapper(Team3699Robot robo){//, ElevatorShooterIntegrationControl ctrl){
            this.robo=robo;
            //this.ctrl=ctrl;
        }
        public synchronized void run(){
        this.robo.elevator.state=1;
        while (!(robo.elevator.ana_chana.getAverageVoltage()<this.goodPlaceToShoot)){}
        this.robo.elevator.state=0;
    }
    }
    
    private class jiggleElevatorStateWrapper implements Runnable{
        Team3699Robot robo;
        double correctLocation=1.9d;
        public jiggleElevatorStateWrapper(Team3699Robot robo){
            this.robo=robo;
        }
        
        public void run(){
            //while ()
            //T ODO: Make Jiggle
        }
    }
    
    private synchronized void bringElevatorUpToShootNonBlocking(){
        
        this.elevatorState = 1;
        
        bringElevatorUpToShootWrapper wrapper = new bringElevatorUpToShootWrapper(this.robo);
        
        Thread thread = new Thread(wrapper);
        
        thread.start();
        
        this.elevatorState = 2;
        
        this.globalState=2;
        
    }
    
    public void updateIntegratedModules(){
        //pass
    }
    
    public void update(){
        this.shootToggle.update(Util.checkButton(this.robo, Constants.shoot_button));
        if (this.shootToggle.get() && this.globalState==0){
            this.bringElevatorUpToShootNonBlocking();
            this.globalState=1;
        }
        else if (this.globalState==2){
            
        }
        
    }
}
