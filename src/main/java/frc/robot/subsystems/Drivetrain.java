package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drivetrain extends Subsystem{

    CANSparkMax SPARKLEFTFRONT = new CANSparkMax(RobotMap.SPARKLEFTFRONT, MotorType.kBrushless);
    CANSparkMax SPARKLEFTBACK = new CANSparkMax(RobotMap.SPARKLEFTBACK,MotorType.kBrushless);

    CANSparkMax SPARKRIGHTFRONT = new CANSparkMax(RobotMap.SPARKRIGHTFRONT, MotorType.kBrushless);
    CANSparkMax SPARKRIGHTBACK = new CANSparkMax(RobotMap.SPARKRIGHTBACK, MotorType.kBrushless);


    CANEncoder encoderFR = new CANEncoder(SPARKRIGHTFRONT);
    CANEncoder encoderBR = new CANEncoder(SPARKRIGHTBACK);
    CANEncoder encoderFL = new CANEncoder(SPARKLEFTFRONT);
    CANEncoder encoderBL = new CANEncoder(SPARKLEFTBACK);

    public double encoderFRlast = 0;
    public double encoderBRlast = 0;
    public double encoderFLlast = 0;
    public double encoderBLlast = 0;

    public static double encoderCal = 1.886;

    
    MecanumDrive mDrive = null;

    public Drivetrain(){

        mDrive = new MecanumDrive(SPARKLEFTFRONT, SPARKLEFTBACK, SPARKRIGHTFRONT, SPARKRIGHTBACK);

        
        SPARKRIGHTBACK.setInverted(false);
        SPARKRIGHTFRONT.setInverted(false);

        SPARKLEFTBACK.setInverted(false);
        SPARKLEFTFRONT.setInverted(false);
        

    }

    public void mecanumDrive(double moveY, double moveX, double moveRot, double angle, double moveSpeed){

        mDrive.driveCartesian(moveY*moveSpeed*0.30, -moveX*moveSpeed, moveRot*moveSpeed*0.40, angle);

        /*
        SPARKRIGHTBACK.set(moveRot);
        SPARKRIGHTFRONT.set(-moveRot);

        SPARKLEFTBACK.set(-moveRot);
        SPARKLEFTFRONT.set(moveRot);
        */

        //mDrive.driveCartesian(ySpeed, xSpeed, zRotation, gyroAngle);

    }

    public double getEncoderFR(){

        double encoderVal = -((encoderFR.getPosition() * encoderCal) - encoderFRlast);

        return encoderVal;
    }

    public double getEncoderBR(){

        double encoderVal = -((encoderBR.getPosition() * encoderCal) - encoderBRlast);

        return encoderVal;
    }

    public double getEncoderFL(){

        double encoderVal = (encoderFL.getPosition() * encoderCal) - encoderFLlast;

        return encoderVal;
    }

    public double getEncoderBL(){

        double encoderVal = (encoderBL.getPosition() * encoderCal) - encoderBLlast;

        return encoderVal;
    }

    public double getEncoderAvg(){

        double encoderValAvg = (getEncoderFR() + getEncoderBR() + getEncoderFL() + getEncoderBL()) / 4;

        return encoderValAvg;
    }

    public void resetEncoders(){

    encoderFRlast = encoderFR.getPosition()* encoderCal;
    encoderBRlast = encoderBR.getPosition()* encoderCal;
    encoderFLlast = encoderFL.getPosition()* encoderCal;
    encoderBLlast = encoderBL.getPosition()* encoderCal;
        
    }


    @Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new DriveArcade());
	}

}