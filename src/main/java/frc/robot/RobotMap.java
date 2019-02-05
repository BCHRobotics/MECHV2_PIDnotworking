package frc.robot;

public class RobotMap {

    //RobotMap holds all the global variables for where things are plugged in


    //MOTORS

    //DriveTrain
    public static int SPARKLEFTFRONT = 11;
    public static int SPARKLEFTBACK = 12;

    public static int SPARKRIGHTFRONT = 15;
    public static int SPARKRIGHTBACK = 16;

    //Lift
    public static int TALONLIFT = 31;

    /********************************* */

    //CONTROLLERS
    public static int OI_DRIVESTICK = 0;
    public static int OI_FUNSTICK = 1;

    //DriveStick AXIS
    public static int OI_DRIVESTICK_MOVEX = 1;
    public static int OI_DRIVESTICK_MOVEY = 0;
    public static int OI_DRIVESTICK_ROTATE = 4;
    public static int OI_FUNSTICK_LIFT = 1; 

    //DirveStick BUTTONS
    public static int OI_FUNSTICK_GrabOpen = 1; 
    public static int OI_FUNSTICK_GrabClose = 2; 
    public static int OI_DRIVESTICK_Snail = 5; 
    public static int OI_DRIVESTICK_Turbo = 6; 
    public static int OI_FUNSTICK_LiftEnable = 5;

    /********************************* */

    //PHENUMATICS
    public static int GRABBER_SOLENOID_OPEN = 0;
    public static int GRABBER_SOLENOID_CLOSE = 1;

     /********************************* */

    //PID VALUES

    public static double SETPOINT = 1;
    //public static double PIDspeed = 0.003;


    //ADXRS450 Gyro PID
    public static double GYRO_P = 1;
    public static double GYRO_I = 1;
    public static double GYRO_D = 1;

    //NavX Gyro PID
    public static double NAV_GYRO_P = 0.1; // 0.031
    public static double NAV_GYRO_I = 0.00281; // 0.00283
    public static double NAV_GYRO_D = 0.0001; // 0
    
    //Encoder PID
    public static double ENCODER_P = 0.001;
    public static double ENCODER_I = 0.001;
    public static double ENCODER_D = 0.001;

    //Vision PID
    public static double VISION_P = 1;
    public static double VISION_I = 1;
    public static double VISION_D = 1;

    //Lift PID
    public static double LIFT_P = 1;
    public static double LIFT_I = 1;
    public static double LIFT_D = 1;

}