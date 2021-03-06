/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Commands.AlwaysOn;
import frc.robot.Commands.AutonomousCommands;
import frc.robot.Commands.TeleopCommands;
import frc.robot.Subsystems.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Drive drive;
  public static DashHelper dash;
  public static RobotStick stick;
  public static Lights lights;
  public static Grabber grabber;
  public static Lift lift;
  static int test;
  CommandGroup tele;
  CommandGroup auto;
  CommandGroup constant;


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */

  @Override
  public void robotInit() {

    //CrashTracker.logRobotInit();
    try {
      lights = new Lights();
      lights.red_light.setRaw(0);
      //grabber = new Grabber();
      //lift = new Lift();
      drive = new Drive();
      drive.resetGyro();
      stick = new RobotStick(5);
      test = 0;
      dash = new DashHelper();
      dash.startDash();
      dash.addGyro(drive.gyro);
      tele = new TeleopCommands();
      auto = new AutonomousCommands();
      constant = new AlwaysOn();
      constant.start();
      Scheduler.getInstance().run();
    }
    catch (Throwable t){
//      CrashTracker.logThrowable(t);
      throw t;
    }
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
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW DashHelper, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    tele.cancel();
    auto.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    auto.cancel();
    lights.red_light.setRaw(0);
    tele.start();
  }

    /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
