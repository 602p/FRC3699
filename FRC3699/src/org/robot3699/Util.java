/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robot3699;

/**
 *
 * @author Louis
 */
public class Util {
    public static boolean checkButton(Team3699Robot robot, int button){
        try{
        return robot.joystick_left.getRawButton(button)||robot.joystick_right.getRawButton(button);
        }catch (NullPointerException npex){
            robot.log("NULLPOINTEREXCEPTION!")
                    ;
            return false;
        }catch (Exception bex){
            robot.log("AUGH!");
            return false;
        }
    }
}
