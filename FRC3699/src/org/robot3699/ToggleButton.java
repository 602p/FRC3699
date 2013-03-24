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
        if (this.state && this.output){
            this.output=false;
        }if (input && !this.state){
            this.state=true;
            this.output=true;
        }else if (!input && this.state){
            this.state = false;
        }
    }
    
    public boolean get(){
        return this.output;
    }
}