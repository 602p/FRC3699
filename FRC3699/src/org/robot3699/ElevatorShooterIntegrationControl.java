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
    /*
    public Team3699Robot robo;
    public Jaguar elevator_out_roller = new Jaguar(Constants.elevator_outtake_PWM);
    public int elevatorState=0;
    
    //0=Idle
    //1=Bringing Elevator Up / Idle
    //2=Bringing Elevator Up / Done / Idle
    //3=Jiggling!
    //4=Done jigglineg! (RESET TO 0)
    
    public boolean globalState2LOCK = false;
    public int globalState=0;
    
    //0=Idle
    //1=Elevator Operations --> elevatorState (==> 2)
    //2=Jiggle Operations --> AND SHOOT
    
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
            this.robo.elevator.state=2;
            this.robo.integ.elevatorState=2;
            this.robo.integ.globalState=2;
        }
    }
    
    private class jiggleElevatorStateWrapper implements Runnable{
        Team3699Robot robo;
        double correctLocation=1.9d;
        double adjSpeed = 0.3d;
        public jiggleElevatorStateWrapper(Team3699Robot robo){
            this.robo=robo;
        }
        
        public void run(){
            this.robo.elevator.state=2;
            this.robo.Elevator_motor.set(this.adjSpeed);
            while (this.robo.elevator.ana_chana.getAverageVoltage() > this.correctLocation) {}
            this.robo.Elevator_motor.set(0d);
            this.robo.elevator.state=0;
            this.robo.integ.globalState=0;
            this.robo.integ.globalState2LOCK=false;
        }
    }
    
    private synchronized void bringElevatorUpToShootNonBlocking(){
        
        this.elevatorState = 1;
        
        this.globalState=1;
        
        bringElevatorUpToShootWrapper wrapper = new bringElevatorUpToShootWrapper(this.robo);
        
        Thread thread = new Thread(wrapper);
        
        thread.start();
        
    }
    
    private synchronized void jiggleElevatorStateNonBlocking(){
        
        this.elevatorState=3;
        this.globalState=2;
        
        jiggleElevatorStateWrapper wrapper = new jiggleElevatorStateWrapper(this.robo);
        
        Thread thread = new Thread(wrapper);
        
        thread.start();
    }
    
    public void updateIntegratedModules(){
        //pass
    }
    
    public void update(){
        this.shootToggle.update(Util.checkButton(this.robo, Constants.shoot_button));
        if (this.shootToggle.get() && this.globalState==0){
            this.bringElevatorUpToShootNonBlocking();
        }
        else if (this.globalState==2 && !this.globalState2LOCK){
            this.globalState2LOCK=true;
            this.jiggleElevatorStateNonBlocking();
        }
        
    }
    * */
}
