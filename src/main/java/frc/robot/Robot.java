// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private CANSparkMax kController;
  private int controllerId = 0;
  private double speed = 0;
  @Override
  public void robotInit() {
    SmartDashboard.putNumber("Controller ID", controllerId);
    SmartDashboard.putNumber("Speed", speed);
    controllerId = (int)SmartDashboard.getNumber("Controller ID", 0);
    
    kController = new CANSparkMax(controllerId, MotorType.kBrushless);
    kController.set(speed);
  }

  @Override
  public void teleopPeriodic() {
    int newControllerID = (int)SmartDashboard.getNumber("Controller ID", 0);
    double newMotorSpeed = SmartDashboard.getNumber("Speed", 0);

    if (newControllerID != controllerId){
      kController.set(0);
      controllerId = newControllerID;
      kController = new CANSparkMax(newControllerID, MotorType.kBrushless);
      kController.set(newMotorSpeed);
    }

    if(newMotorSpeed != speed){
      speed = newMotorSpeed;
      kController.set(newMotorSpeed);
    }
  }
}
