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
    
    public void update(boolean input){
        if (input && !this.state){
            this.state=true;
        }else if (!input && this.state){
            this.state = false;
        }
    }
    
    public boolean get(){
        return this.state;
    }
}
