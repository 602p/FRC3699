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
public class Shoot3Discs {
    boolean shooting = false;
    private class TimerTaskStopPulldown implements Runnable{
        private final Team3699Robot robo;
        public TimerTaskStopPulldown(Team3699Robot robo){
            this.robo=robo;
        }

        public void run() {
            this.robo.log("...1");
            Timer.delay(11);
            this.robo.log("...2");
            this.robo.pullDownJaguar.set(0d);
        }
}
}
