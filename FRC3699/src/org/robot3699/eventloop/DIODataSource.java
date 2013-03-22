/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699.eventloop;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Louis
 */
public class DIODataSource implements IDataSource{
     DigitalInput input;
     
     public DIODataSource(int inputSlot){
         this.input=new DigitalInput(inputSlot);
     }
     
     public boolean getValue(){
         return this.input.get();
     }
    
}