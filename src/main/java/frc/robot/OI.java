package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

    //Init Driver and Functional Mech Joysticks
    public Joystick driveStick = new Joystick(RobotMap.OI_DRIVESTICK);
    public Joystick funStick = new Joystick(RobotMap.OI_FUNSTICK);

    //Set the buttons
    Button ButtonGrabOpen = new JoystickButton(funStick, RobotMap.OI_FUNSTICK_GrabOpen);
    Button ButtonGrabClose = new JoystickButton(funStick, RobotMap.OI_FUNSTICK_GrabClose);

    Button ButtonLiftEnable = new JoystickButton(funStick, RobotMap.OI_FUNSTICK_LiftEnable);

    Button ButtonSnail = new JoystickButton(driveStick, RobotMap.OI_DRIVESTICK_Snail);
    Button ButtonTurbo = new JoystickButton(driveStick, RobotMap.OI_DRIVESTICK_Turbo);
    
    
}