/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Louis
 */
public class SmartDashboardUpdater {
    public void updateSmartDashboard(Team3699Robot robo){
        SmartDashboard.putBoolean("Intake On", robo.intake.state==1);
        SmartDashboard.putBoolean("Elevator Moving", robo.elevator.state==1);
    }
}
