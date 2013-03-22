/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699.eventloop;

/**
 *
 * @author Louis
 */
public class ThreadedCallbackHandler implements ICallbackHandler{
    Runnable run;
    public ThreadedCallbackHandler(Runnable run){
        this.run=run;
    }
    
    public void doCallBack(boolean state){
        if (state){
            (new Thread(this.run)).start();
        }
    }
}
