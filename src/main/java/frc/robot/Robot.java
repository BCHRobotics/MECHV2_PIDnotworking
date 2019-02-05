/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.commands.*;
import frc.robot.subsystems.*;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static Drivetrain m_drivetrain = null;
  public static OI m_oi = null;
  public static Grabber m_grabber = null;
  public static Lift m_lift = null;

  //Gyro
  public ADXRS450_Gyro m_gyro = null;
  public AHRS ahrs = null;

  public double y = 0;           //variable for forward/backward movement
	public double x = 0;           //variable for side to side movement
	public double turn = 0;        //variable for turning movement
	public double deadzone = 0.08;	//variable for amount of deadzone
  
  public double pidTime = 0;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    SmartDashboard.putNumber("ERROR", 4546);
    SmartDashboard.putNumber("PIDTIME", 69);
    
    SmartDashboard.putNumber("EncoderFR", 6969);
    SmartDashboard.putNumber("EncoderBR", 6969);
    SmartDashboard.putNumber("EncoderFL", 6969);
    SmartDashboard.putNumber("EncoderBL", 6969);
    SmartDashboard.putNumber("EncoderAvg", 6969);

    SmartDashboard.putNumber("EncoderFRlast", 2222);

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    SmartDashboard.putNumber("NAVX", 4546);

    m_drivetrain = new Drivetrain();
    m_oi = new OI();
    //m_grabber = new Grabber();
    //m_lift = new Lift();   
    m_gyro = new ADXRS450_Gyro();

    ahrs = new AHRS(SerialPort.Port.kUSB);

    m_gyro.reset();
    m_gyro.calibrate();

    m_drivetrain.resetEncoders();

    /*
    m_drivetrain.encoderBLlast = 0;
    m_drivetrain.encoderBRlast = 0;
    m_drivetrain.encoderFLlast = 0;
    m_drivetrain.encoderFRlast = 0;
    */
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    SmartDashboard.putNumber("Nav Gyro", ahrs.getAngle());
    SmartDashboard.putNumber("EncoderFR", m_drivetrain.getEncoderFR());
    SmartDashboard.putNumber("EncoderBR", m_drivetrain.getEncoderBR());
    SmartDashboard.putNumber("EncoderFL", m_drivetrain.getEncoderFL());
    SmartDashboard.putNumber("EncoderBL", m_drivetrain.getEncoderBL());
    SmartDashboard.putNumber("EncoderAvg", m_drivetrain.getEncoderAvg());

    SmartDashboard.putNumber("EncoderFRlast", m_drivetrain.encoderFRlast);

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    m_drivetrain.resetEncoders();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  
    
    double P = RobotMap.ENCODER_P;
		double I = RobotMap.ENCODER_I;
		double D = RobotMap.ENCODER_D;

		double integral = 0;
    double previous_error = 0;
    double error = 0;
    double setpoint = RobotMap.SETPOINT;

		error = setpoint - m_drivetrain.getEncoderAvg();
		integral = integral + (error*0.02);
		double derivative = (error - previous_error) / 0.02;
		double rcw = P*error + I*integral + D*derivative;

    Robot.m_drivetrain.mecanumDrive(0, -rcw, 0, ahrs.getAngle(), ((1/rcw)*0.3));
    
    pidTime = pidTime + 0.020;
    SmartDashboard.putNumber("ERROR", error);
    SmartDashboard.putNumber("PIDTIME", pidTime);
  
/*
  while(m_drivetrain.getEncoderAvg() < 48){
    Robot.m_drivetrain.mecanumDrive(-1, 0, 0, ahrs.getAngle(), 0.3);
  }*/

  }

  //Reset gyro when the robot is disabled
  @Override
  public void disabledInit(){
    m_gyro.reset();
    ahrs.reset();
    
    m_drivetrain.resetEncoders();
  }

  
  //Reset gyro when the robot is entering teleop
  @Override
  public void teleopInit() {
    super.teleopInit();
    m_gyro.reset();
    ahrs.reset();

    m_drivetrain.resetEncoders();
    
  }


  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

   
   
    //Init the moveSpeed variable
    double moveSpeed = 0.7;

    //Check for Snail and Turbo buttons to speed or slow the robot
    if(Robot.m_oi.ButtonSnail.get() == true){

      moveSpeed = 0.5;
    
    } else if(Robot.m_oi.ButtonTurbo.get() == true){

      moveSpeed = 1;
    
    }


  if(Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVEY) > deadzone || Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVEY) < -deadzone) {
     y = Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVEY);
  } else {
    y = 0;
  }

  if(Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVEX) > deadzone || Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVEX) < -deadzone) {
    x = Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVEX);
  } else {
    x = 0;
  }

  if(Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_ROTATE) > deadzone || Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_ROTATE) < -deadzone){
    turn = Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_ROTATE);
  } else {
    turn = 0;
  }
    
  // m_drivetrain.mecanumDrive(y, x, turn, ahrs.getAngle());

  m_drivetrain.mecanumDrive(y, x, turn, ahrs.getAngle(), moveSpeed);


    //m_drivetrain.mecanumDrive(0.5, 0, 0, 0);

    /*
    //Check if grabber buttons are pressed then open or close grabber
    if(Robot.m_oi.ButtonGrabOpen.get() == true){
        Robot.m_grabber.grabberOpen();
    } else if(Robot.m_oi.ButtonGrabClose.get() == true){
      Robot.m_grabber.grabberClose();
    } 

    //Manipulate the Lift
    if(Robot.m_oi.ButtonLiftEnable.get() == true){
      Robot.m_lift.MoveLift(Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_LIFT));
    } else {
      Robot.m_lift.MoveLift(-0.15);
    }*/
    
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
