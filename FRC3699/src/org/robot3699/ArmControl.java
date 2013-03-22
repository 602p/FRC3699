/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699;

import edu.wpi.first.wpilibj.Jaguar;
import org.robot3699.eventloop.ButtonDataSource;
import org.robot3699.eventloop.EventLoop;
import org.robot3699.eventloop.ThreadedCallbackHandler;

/**
 *
 * @author Louis
 */
public class ArmControl {
    EventLoop eloop;
    Team3699Robot robo;
    ToggleButton toggle = new ToggleButton();
    public ArmControl(int button, Team3699Robot robo, int jaguarNumber){
        EventLoop loop = new EventLoop(new ButtonDataSource(button, robo), new ThreadedCallbackHandler(new ArmControlCallbackHandler()));
        this.robo=robo;
        this.eloop=loop;
    }

    private static class ArmControlCallbackHandler implements Runnable{
        Jaguar jag = new Jaguar(2);//<------ CUSTOMIZE
        boolean internalState = false;
        
        public void run(){
            if (this.internalState){
                //this.
            }else{
                //Move this.jag the other way
            }
        }
        
        
        
    }
    
    public void update(){
        this.toggle.update(Util.checkButton(robo, Constants.armToggleButton));
        if (this.toggle.get()){
            this.eloop.DoEventLoopIteration();
        }
    }
}
