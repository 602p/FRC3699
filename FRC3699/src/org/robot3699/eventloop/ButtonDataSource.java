/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699.eventloop;

import org.robot3699.Team3699Robot;
import org.robot3699.Util;

/**
 *
 * @author Louis
 */
public class ButtonDataSource implements IDataSource{
    public boolean state;
    public int buttonNumber;
    Team3699Robot robo;
    public ButtonDataSource(int buttonNumber, Team3699Robot robo){
        this.state=false;
        this.buttonNumber=buttonNumber;
        this.robo=robo;
    }
    
    public boolean getValue(){
        if (Util.checkButton(robo, this.buttonNumber) && !this.state){
            this.state=true;
            return true;
        }else if (!Util.checkButton(robo, this.buttonNumber) && this.state){
            this.state=false;
            return false;
        }
        return false;
    }
}
