/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.robot3699;


//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj



public class Team3699Robot extends SimpleRobot {
    
    
    
    public Joystick joystick_left = new Joystick(Constants.joystick_left_USB);
    public Joystick joystick_right = new Joystick(Constants.joystick_right_USB);
    public Joystick joystick_elevator = new Joystick(3);
    public DriverStationLCD userMessages = DriverStationLCD.getInstance();
    public DriverStation driverstation = DriverStation.getInstance();
    public NetworkTable server;
    
    public ShooterControl shooter = new ShooterControl();
    public Jaguar shooterMotor = new Jaguar(Constants.shooterPWMChannel);
    
    public ArmControl2 arm = new ArmControl2(this);
    public Jaguar armMotor = new Jaguar(Constants.armMotorPWM);
    
    public ElevatorShooterIntegrationControl integ = new ElevatorShooterIntegrationControl(this);
    
    //public AnalogChannel Optical_Sensor = new AnalogChannel(Constants.OpticalSensorChannel);
    
    public Jaguar test_CIM_1 = new Jaguar(7);
    public Jaguar test_CIM_2 = new Jaguar(8);
    
    /*
    public DigitalInput Disc_Check = new DigitalInput(Constants.Disc_Check_Channel);
    public DigitalInput Disc_Top_Check = new DigitalInput(Constants.Disc_Top_Check_Channel);
    public DigitalInput Upside_Down_Check = new DigitalInput(Constants.Upside_Down_Check_Channel);
    */
    
    public RobotDrive robotdrive = new RobotDrive(Constants.robotdrive_left_PWM,Constants.robotdrive_right_PWM);
    public Jaguar Intake_motor =  new Jaguar(Constants.intake_PWM);
    public Jaguar Elevator_motor = new Jaguar(Constants.elevator_PWM);
    //public Jaguar Elevator_intake = new Jaguar(Constants.elevator_intake_PWM);
    //public Jaguar Elevator_outtake = new Jaguar(Constants.elevator_outtake_PWM);
    
    public ElevatorControl elevator = new ElevatorControl(this);
    public IntakeControl intake = new IntakeControl();
   
    
    public boolean Intake = false;
    public boolean toggle = false;
    
    int dev_=0;
    
    public boolean[] errors = new boolean[10];
    
    //^ Yeah, yeah. Way overinitizized
    //ERRNO 0: Init error
    //ERRNO 1: SmartDashboard error
    //ERRNO 2: Teleop error
    
    public boolean allowUnlimitedErrors = false;
    
    //Set to true to allow unlimeted errors
    
    public void error(String message, int errno){
        if (!(this.errors[errno]) || this.allowUnlimitedErrors){
            log(message);
            errors[errno]=true;
        }
    }
    
    public void resetError(int errno){
        errors[errno]=false;
    }
    
    public void log(String message){
        System.out.println(message);
    }
    
    public void robotInit(){
        log("RobotInit called. Initializing NetworkTabes...");
        try {
        NetworkTable.setIPAddress("10.36.99.2");
        
            NetworkTable.initialize();
            NetworkTable.setServerMode();
        }catch (Exception be){
            error("Error in robotInit: \n "+be.getMessage(), 0);
            error("Reocourrence Test! You shouldnt see this!", 0);
        }
        log("Init SmartDashboard...");
        this.server = NetworkTable.getTable("SmartDashboard");
        
        /*LiveWindow.addActuator("Elevator", "Elevator Intake", Elevator_intake);
        LiveWindow.addActuator("Elevator", "Elevator Gearbox", Elevator_motor);
        LiveWindow.addActuator("Elevator", "Elevator Outtake", Elevator_outtake);
        LiveWindow.addActuator("Intake", "Intake", Elevator_motor);*/
        
        log("Disabling RobotDrive Saftey... :D");
        robotdrive.setSafetyEnabled(false);
        
   
        
    }
    
    public void operatorControl() {
        log("Teleop Called Once!");
        resetError(1);
        resetError(2);
        this.elevator.resetState();
        try{
        showUserMessages("TeleOp");
        
        while (isOperatorControl()&&isEnabled()){
            robotdrive.setSafetyEnabled(true); //In case the program were to stop, this stops the motors from continuing to run.
            if (! Util.checkButton(this, Constants.robotdrive_break_button)){
                robotdrive.tankDrive(doRobotdriveScaling(joystick_left.getY()), 
                        doRobotdriveScaling(joystick_right.getY()));
                //robotdrive.arcadeDrive(doRobotdriveScaling(joystick_left.getY())
                //        , doRobotdriveScaling(joystick_left.getX()));
            }
            
            if (driverstation.getDigitalIn(3)){
                this.test_CIM_1.set(driverstation.getAnalogIn(2));
            }else{
                this.test_CIM_1.set(0-driverstation.getAnalogIn(2));
            }
            
            if (driverstation.getDigitalIn(4)){
                this.test_CIM_2.set(driverstation.getAnalogIn(3));
            }else{
                this.test_CIM_2.set(0-driverstation.getAnalogIn(3));
            }
            
            
            updateSmartDashboard();
            
            this.shooter.updateStates(this);
            this.shooterMotor.set(this.shooter.calculateShooterSpeed());
            
            this.arm.update();
            this.armMotor.set(this.arm.getArmSpeed());
            
            this.elevator.update();
            this.Elevator_motor.set(this.elevator.getElevatorSpeed());
            
            this.intake.update();
            this.Intake_motor.set(this.intake.calculateIntakeSpeed());
            
            this.integ.update();
            
            
            Timer.delay(0.005); //and make sure we dont overload the cRIO
        }  
        }catch (Exception be){
            error("ERROR IN TELEOP! STACKTRACE: \n "+be.getMessage(), 2);
        }
    }
    
    public void disabled(){
        log("Disabled.");
        showUserMessages("Disabled");
    }
    
    public void autonomous(){
        log("Autonomus! (XD I am bad at spelling)");
        showUserMessages("Autonomous");
        robotdrive.tankDrive(doRobotdriveScaling(-0.45),doRobotdriveScaling(-0.45));
        Timer.delay(2.0);
        robotdrive.tankDrive(doRobotdriveScaling(0.0),doRobotdriveScaling(0.0)); // Use to determine ft/s at this power.
    }
    
    public double doRobotdriveScaling(double value){
        if (driverstation.getDigitalIn(Constants.driverstation_scale_toggle_channel)){
            if (driverstation.getDigitalIn(Constants.driverstation_scale_toggle_channel2)){
                driverstation.setDigitalOut(2, true);
                driverstation.setDigitalOut(1, false);
                driverstation.setDigitalOut(3, false);
                if (driverstation.getAnalogIn(Constants.driverstation_scale_channel)<1){
                    return value*driverstation.getAnalogIn(Constants.driverstation_scale_channel);
                }
                driverstation.setDigitalOut(3, true);
                return 0;
            }
            driverstation.setDigitalOut(3, false);
            driverstation.setDigitalOut(1, true);
            driverstation.setDigitalOut(2, false);
            return value*driverstation.getAnalogIn(Constants.driverstation_scale_channel)*0.2;
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
        this.userMessages.println(DriverStationLCD.Line.kUser5, 1, "===VENDETTA LIST:===");
        this.userMessages.println(DriverStationLCD.Line.kUser6, 1, "(Names Forthcoming) ");
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
            //SmartDashboard.putDouble("Optical Sensor", Optical_Sensor.getVoltage());
            SmartDashboard.putDouble("Battery Voltage", DriverStation.getInstance().getBatteryVoltage());
            SmartDashboard.putInt("dev_", dev_);
            
            SmartDashboard.putDouble("Elevator Sensor Value (.getAverageVoltage())", this.elevator.ana_chana.getAverageVoltage());
            
            String state = "ROBOT STATE INFORMATION:";
            if (this.elevator.state==0){
                state=state+"\nElevator Stopped.";
            }if (this.elevator.state==1){
                state=state+"\nElevator Moving.";
            }if (this.elevator.state==2){
                state=state+"\nElevator E-Stopped.";
            }
            
            state = state+"\nShooter Power Level (Raw): "+this.shooter.calculateShooterSpeed();
            state = state+"\nShooter Power Level (Setting): "+this.shooter.shooterSpeedState;
            
            if (this.integ.globalState==1){
                state = state+"\nMoving Shooter Up To Shoot.";
            }if (this.integ.globalState==2){
                state = state+"\nShooting!";
            }if (this.integ.globalState2LOCK){
                state = state+"\nREALLY SHOOTING!.";
            }
            
            state = state+"\nNumber Of Discs: "+this.elevator.numDiscs;
            
            state = state + "\n Louis + Bobni Are Amazing (Hiding This Message Is ILLEGAL! IT WILL STOP THE ROBOT!)";
            
            
            
            SmartDashboard.putString("State", state);
            
            {
            try {
                SmartDashboard.putInt("Distance To Target", Integer.parseInt(this.server.getString("Distance")));
                //SmartDashboard.putString("NetworkTable Keys", this.server.);
            } catch (Exception be){
                error("ERROR UPDATING SmartDashboard dtt STACK TRACE: "+be.getMessage(), 1);
            }
            }
    }
}
