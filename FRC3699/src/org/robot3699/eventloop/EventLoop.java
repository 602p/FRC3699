/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699.eventloop;

/**
 *
 * @author Louis
 */
public class EventLoop {
    IDataSource dsource;
    ICallbackHandler chandle;
    boolean isMoving=false;
    
    public EventLoop(IDataSource dsource, ICallbackHandler chandle){
        this.dsource=dsource;
        this.chandle=chandle;
    }
    
    private void DoEventLoopIteration(){
        if (this.dsource.getValue()){
            this.isMoving=!this.isMoving;
        }
        this.chandle.doCallBack(this.isMoving);
    }
}
