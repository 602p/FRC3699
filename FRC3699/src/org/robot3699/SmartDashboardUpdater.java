/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 *
 * @author Louis
 */
public class SmartDashboardUpdater {
    public static void updateSmartDashboard(Team3699Robot robo){
        SmartDashboard.putBoolean("Intake On", robo.intake.state==1);
        SmartDashboard.putBoolean("Elevator Moving", robo.elevator.state==1);
//        SmartDashboard.putBoolean("Arm Moving", robo.arm.state==1||robo.arm.state==2);
        SmartDashboard.putNumber("Num. Discs", robo.elevator.numDiscs);
        try{
            SmartDashboard.putNumber("FPS (This Is A Camera Lag Indicatior", robo.server.getNumber("FPS"));
            SmartDashboard.putBoolean("Target Locked", robo.server.getBoolean("locked"));
            SmartDashboard.putNumber("Distance To Target", robo.server.getNumber("NewDistance"));
            
            
        }catch (TableKeyNotDefinedException e){
            
        }catch (Exception be){
            robo.log("Other error!");
        }
        
    
        SmartDashboard.putBoolean("Connected To RoboRealm", robo.server.isConnected());
        
        
        //===DEBUG===\\
        
        SmartDashboard.putNumber("Analog Sensor Value", robo.elevator.ana_chana.getAverageVoltage());
        SmartDashboard.putBoolean("Tick", robo.elevator.tickCounter.get());
        SmartDashboard.putBoolean("Intake_snsr", !robo.elevator.intakeCounter.get());
    }
}
