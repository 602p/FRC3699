/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699.eventloop;

/**
 *
 * @author Louis
 */
public abstract class ADigitalDataSource implements IDataSource{
    public int getThreshold(){
        return 1;
    }
    
    public abstract int getValue();
}
