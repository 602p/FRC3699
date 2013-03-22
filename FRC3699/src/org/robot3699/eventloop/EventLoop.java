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
        int value = dsource.getValue();
        int threshold = dsource.getThreshold();
        int return0;
        if (this.isMoving && value < threshold)
            return0=1;
        else if (!this.isMoving && value > threshold)
            return0=1;
        else if (this.isMoving && value > threshold)
            return0=0;
    }
}
