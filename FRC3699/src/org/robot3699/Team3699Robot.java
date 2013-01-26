/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.robot3699;


import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;



public class Team3699Robot extends SimpleRobot {
    RobotDrive robotdrive = new RobotDrive(Constants.robotdrive_left_PWM,Constants.robotdrive_right_PWM);
    Joystick joystick_left = new Joystick(Constants.joystick_left_USB);
    Joystick joystick_right = new Joystick(Constants.joystick_right_USB);
    DriverStationLCD userMessages = DriverStationLCD.getInstance();
    public void operatorControl() {
        this.userMessages.println(DriverStationLCD.Line.kUser2, 1, "FRC Robotics Team              ");
        this.userMessages.println(DriverStationLCD.Line.kUser3, 1, "3699 Robot Teleop              ");
        this.userMessages.println(DriverStationLCD.Line.kUser4, 1, Constants.version);
        this.userMessages.updateLCD();
        while (isOperatorControl()&&isEnabled()){
            if (! (joystick_left.getRawButton(Constants.robotdrive_break_button)||joystick_right.getRawButton(Constants.robotdrive_break_button))){
                robotdrive.arcadeDrive(joystick_left);//robotdrive.tankDrive(joystick_left.getY()*Constants.robotdrive_scale_left, joystick_right.getY()*Constants.robotdrive_scale_right);
            }
            Timer.delay(0.005); //and make sure we dont overload the cRIO
        }  
    }
    
    public void disabled(){
        this.userMessages.println(DriverStationLCD.Line.kUser2, 1, "FRC Robotics Team              ");
        this.userMessages.println(DriverStationLCD.Line.kUser3, 1, "3699 Robot Disabled            ");
        this.userMessages.println(DriverStationLCD.Line.kUser4, 1, Constants.version);
        this.userMessages.updateLCD();
    }
    
    public void autonomous(){
        this.userMessages.println(DriverStationLCD.Line.kUser2, 1, "FRC Robotics Team              ");
        this.userMessages.println(DriverStationLCD.Line.kUser3, 1, "3699 Robot Autonomous          ");
        this.userMessages.println(DriverStationLCD.Line.kUser4, 1, Constants.version);
        this.userMessages.updateLCD();
    }
}
