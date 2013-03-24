/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Louis
 */
public class SmartDashboardUIControl {
    public static void putALLTheDatatypes(Team3699Robot robo){ //(Update SmartDashboard)
        SmartDashboard.putString("Credits", "Louis Goessling, Bobni Daas, Ted Goessling, Eric Ware, John Ware, Nick ???, And the rest of Team 3699");
        SmartDashboard.putDouble("Battery Voltage %", (double) (DriverStation.getInstance().getBatteryVoltage()/12.50d)*100);
        SmartDashboard.putInt("Distance To Targer", Integer.parseInt(robo.server.getString("Distance", "-1")));
        try{
        SmartDashboard.putInt("IMAGE_COUNT~!TEST", robo.server.getInt("IMAGE_COUNT"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
