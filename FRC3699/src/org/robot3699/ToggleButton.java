/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699;

/**
 *
 * @author Louis
 */
public class ToggleButton {
    public boolean state = false;
    public boolean output = false;
    
    public void update(boolean input){
        if (input && this.state){
            this.state=false;
            if(this.output){
                this.output = false; 
            }
            else {
                this.output = true;
            }
        }else if (!input){
            this.state = true;
        }
    }
    
    public boolean get(){
        return this.output;
    }
}
