/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Grabber extends Subsystem {
	
	//Init grabber variable
	DoubleSolenoid grabberSol = null;

	public Grabber() {
		grabberSol = new DoubleSolenoid(RobotMap.GRABBER_SOLENOID_OPEN,
				RobotMap.GRABBER_SOLENOID_CLOSE);
	}

	public void grabberOpen() {
        grabberSol.set(Value.kForward);
        
	}

	public void grabberClose() {
        grabberSol.set(Value.kReverse);
        
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
