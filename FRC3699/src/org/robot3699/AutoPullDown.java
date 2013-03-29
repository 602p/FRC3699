/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Louis
 */
public class AutoPullDown {
    boolean running = false;
    private class TimerTaskStopPulldown implements Runnable{
        private final Team3699Robot robo;
        public TimerTaskStopPulldown(Team3699Robot robo){
            this.robo=robo;
        }

        public void run() {
            Timer.delay(2);
            this.robo.pullDownJaguar.set(0d);
        }
        
    }
    public void pullDown(Team3699Robot robo){
        if (!this.running){
            this.running=true;
            
            TimerTaskStopPulldown s = new TimerTaskStopPulldown(robo);
            Thread t = new Thread(s);
            
            t.start();
        }
    }
    
    public void reset(){
        this.running=false;
    }
}
