/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699;

/**
 *
 * @author Louis
 */
public class ElevatorControl {
    public static void teleopProcess(Team3699Robot robot){
        /*
        if (Util.checkButton(robot, Constants.elevator_button)){
            robot.Elevator_motor.set(Constants.Elevator_speed);
        }    
        else {
            robot.Elevator_motor.set(0);
        }    */
        robot.Elevator_motor.set(robot.joystick_elevator.getY());
        if (robot.joystick_elevator.getRawButton(4)){
            robot.Elevator_intake.set(robot.driverstation.getAnalogIn(4));
        }    
        else {
            robot.Elevator_intake.set(0);
        }
        if (robot.joystick_elevator.getRawButton(5)){
            robot.Elevator_outtake.set(robot.driverstation.getAnalogIn(4));
        }    
        else {
            robot.Elevator_outtake.set(0);
        }
    }
}
