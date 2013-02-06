/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.robot3699;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.IOException;



public class Team3699Robot extends SimpleRobot {
    RobotDrive robotdrive = new RobotDrive(Constants.robotdrive_left_PWM,Constants.robotdrive_right_PWM);
    Joystick joystick_left = new Joystick(Constants.joystick_left_USB);
    Joystick joystick_right = new Joystick(Constants.joystick_right_USB);
    DriverStationLCD userMessages = DriverStationLCD.getInstance();
    DriverStation driverstation = DriverStation.getInstance();
    NetworkTable server;
    
    int dev_=0;
    
    public void robotInit(){
        NetworkTable.setServerMode();
        NetworkTable.setIPAddress("10.36.99.2");
        try {
            NetworkTable.initialize();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.server = NetworkTable.getTable("SmartDashboard");
    }
    
    public void operatorControl() {
        showUserMessages("TeleOp");
        
        while (isOperatorControl()&&isEnabled()){
            if (! (joystick_left.getRawButton(Constants.robotdrive_break_button)||joystick_right.getRawButton(Constants.robotdrive_break_button))){
                robotdrive.tankDrive(doRobotdriveScaling(joystick_left.getY()), 
                        doRobotdriveScaling(joystick_right.getY()));
                //robotdrive.arcadeDrive(doRobotdriveScaling(joystick_left.getY())
                //        , doRobotdriveScaling(joystick_left.getX()));
            }
            
            updateSmartDashboard();
            
            Timer.delay(0.005); //and make sure we dont overload the cRIO
        }  
    }
    
    public void disabled(){
        showUserMessages("Disabled");
    }
    
    public void autonomous(){
        showUserMessages("Autonomous");
    }
    
    public double doRobotdriveScaling(double value){
        if (driverstation.getDigitalIn(Constants.driverstation_scale_toggle_channel)){
            if (driverstation.getDigitalIn(2)){
                driverstation.setDigitalOut(2, true);
                driverstation.setDigitalOut(1, false);
                driverstation.setDigitalOut(3, false);
                if (driverstation.getAnalogIn(1)<1){
                    return value*driverstation.getAnalogIn(1);
                }
                driverstation.setDigitalOut(3, true);
                return 0;
            }
            driverstation.setDigitalOut(3, false);
            driverstation.setDigitalOut(1, true);
            driverstation.setDigitalOut(2, false);
            return value*driverstation.getAnalogIn(1)*0.2;
        }
        driverstation.setDigitalOut(3, false);
        driverstation.setDigitalOut(1, false);
        driverstation.setDigitalOut(2, false);
        return value*Constants.robotdrive_scale_hi;
    }
    
    public void showUserMessages(String status){
        this.userMessages.println(DriverStationLCD.Line.kUser2, 1, "FRC Robotics Team              ");
        this.userMessages.println(DriverStationLCD.Line.kUser3, 1, "3699 Robot "+status+"              ");
        this.userMessages.println(DriverStationLCD.Line.kUser4, 1, Constants.version);
        this.userMessages.updateLCD();
    }
    
    public void updateSmartDashboard(){
        if (joystick_left.getRawButton(2) && dev_<100){
                dev_++;
                
            }else if (joystick_left.getRawButton(3) && dev_>0){
                dev_--;
                
            }
            
            SmartDashboard.putDouble("X", joystick_left.getX());
            SmartDashboard.putDouble("Y", joystick_left.getY());
            SmartDashboard.putDouble("Battery Voltage", DriverStation.getInstance().getBatteryVoltage());
            SmartDashboard.putInt("dev_", dev_);
            
            {
            try {
                SmartDashboard.putInt("Distance To Target", Integer.parseInt(this.server.getString("Distance")));
                //SmartDashboard.putString("NetworkTable Keys", this.server.);
            } catch (NetworkTableKeyNotDefined ex) {
                ex.printStackTrace();
            }
            }
    }
}
