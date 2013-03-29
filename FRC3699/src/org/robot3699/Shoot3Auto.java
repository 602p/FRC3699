/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Bobni
 */
public class Shoot3Auto {
    boolean shoot = false;
    private Team3699Robot robo;  
    
    public Shoot3Auto(Team3699Robot robo) {
        this.robo=robo;
    }
    
    public void update() {
        shoot = true;
        robo.shooterMotor.set(0.7);
        robo.integ2.update(shoot);
        Timer.delay(3);
        shoot = false;
        Timer.delay(1);
        shoot = true;
        robo.integ2.update(shoot);
        Timer.delay(3);
        shoot = false;
        Timer.delay(1);
        shoot = true;
        robo.integ2.update(shoot);
        Timer.delay(3);
        shoot = false;
        Timer.delay(1);
    }
    
}
    
    

