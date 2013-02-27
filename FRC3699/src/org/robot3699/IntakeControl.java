/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699;

/**
 *
 * @author Louis
 */
public class IntakeControl {
    public static void teleopProcess(Team3699Robot robot){/*
        if (Util.checkButton(robot, Constants.intake_button)){
            robot.Intake_motor.set(-Constants.Intake_speed);
        }    
        else {
            robot.Intake_motor.set(0);
        }    */
        if (robot.joystick_elevator.getRawButton(1)){
            robot.Intake_motor.set(-0.5);
        }else{
            robot.Intake_motor.set(0);
        }
    }
}
