// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.hardware.Pigeon2;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private Drivetrain m_drivetrain;
  private PilotController m_pilotController;
  private CopilotController m_copilotController;
  //private Intake m_intake;
  //private Launcher m_launcher;
  //private Indexer m_indexer;

  private boolean m_currentlyLaunching;

  private int m_launchCounter = 0;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    Pigeon2 m_pigeon = new Pigeon2(RobotMap.DrivetrainConstants.PIGEON_CAN_ID);

    m_drivetrain = new Drivetrain(m_pigeon);
    m_pilotController = new PilotController();
    m_copilotController = new CopilotController();
    // m_intake = new Intake();
    // m_launcher = new Launcher();
    // m_indexer = new Indexer();

    m_currentlyLaunching = false;

    m_drivetrain.initDrivetrain();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
    double curSpeed = 0.0;
    double curTurn = 0.0;
    m_drivetrain.arcadeDrive(curSpeed, curTurn);
    //m_launcher.setSpeed(0.0, 0.0);
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double curSpeed = 0.0;
    double curTurn = 0.0;

    boolean intakeOn = false;
    boolean ampLauncherOn = false;
    boolean speakerLauncherOn = false;
    boolean haveNote = false;
    boolean expelOn = false;

    double intakeSpeed = RobotMap.IntakeConstants.SPEED;

    double leftLauncherAmpSpeed = RobotMap.LauncherConstants.LEFT_AMP_SPEED;
    double rightLauncherAmpSpeed = RobotMap.LauncherConstants.RIGHT_AMP_SPEED;

    // Speaker speeds are offset for a more predictable flight pattern.
    double leftLauncherSpeakerSpeed = RobotMap.LauncherConstants.LEFT_SPEAKER_SPEED;
    double rightLauncherSpeakerSpeed = RobotMap.LauncherConstants.RIGHT_SPEAKER_SPEED;

    PilotController.DesiredDirection desiredDirection = PilotController.DesiredDirection.NoChange;

    curSpeed = m_pilotController.getDriverSpeed();
    curTurn = m_pilotController.getDriverTurn();

    desiredDirection = m_pilotController.getPilotChangeControls();
    intakeOn = m_pilotController.getIntakeButton();
    ampLauncherOn = m_pilotController.getAmpLaunchButton();
    speakerLauncherOn = m_pilotController.getSpeakerLaunchButton();
    // haveNote = m_indexer.readIndexSensor();
    // expelOn = m_indexer.readIndexSensor();

    m_drivetrain.setDesiredDirection(desiredDirection);

    m_drivetrain.arcadeDrive(curSpeed, curTurn);

    if (m_currentlyLaunching) {
      // If we want to launch to the amp, set the launcher to amp speed and feed a note from the indexer.
      if (ampLauncherOn) {
        // m_launcher.setSpeed(leftLauncherAmpSpeed, rightLauncherAmpSpeed);
        // m_indexer.feedNote();
      }
      // If we want to launch to the speaker, wait 25 cycles (0.5 seconds), then set the launcher to speaker speed and feed a note from the indexer.
      else if (speakerLauncherOn) {
        if (++m_launchCounter > 25) {
          //m_indexer.feedNote();
        }
        else {
          //m_indexer.stop();
        }
        //m_launcher.setSpeed(leftLauncherSpeakerSpeed, rightLauncherSpeakerSpeed);
      }
      // If we don't want to launch, set the launcher and indexer speeds to 0 and set currentlyLaunching to false.
      else {
        // m_launcher.setSpeed(0.0, 0.0);
        // m_currentlyLaunching = false;
        // m_indexer.stop();
      }
    }
    else {
      if (haveNote) {
        // If currentlyLaunching is false and we have a note we want to launch to the amp,
        // set the launcher to amp speed, feed a note from the the indexer, set the intake speed to 0, and set currentlyLaunching to true.
        if (ampLauncherOn) {
          // m_launcher.setSpeed(leftLauncherAmpSpeed, rightLauncherAmpSpeed);
          // m_indexer.feedNote();
          // m_intake.setSpeed(0.0);
          m_currentlyLaunching = true;
        }
        // If currentlyLaunching is false and we have a note we want to launch to the speaker,
        // set the launcher to speaker speed, feed a note from the the indexer, set the intake speed to 0, and set currentlyLaunching to true.
        else if (speakerLauncherOn) {
          // m_launcher.setSpeed(leftLauncherSpeakerSpeed, rightLauncherSpeakerSpeed);
          // m_indexer.feedNote();
          // m_intake.setSpeed(0.0);
          m_currentlyLaunching = true;
        }
        // If currentlyLaunching is false and we want to expel, set launcher, indexer, and intake to reversed speed.
        else if (expelOn) {
          // m_launcher.setSpeed(-leftLauncherAmpSpeed, -rightLauncherAmpSpeed);
          // m_indexer.expelNote();
          // m_intake.setSpeed(-intakeSpeed);
        }
        // If currentlyLaunching if false and we don't want to launch or expel, set launcher, indexer, and intake speeds to 0.
        else {
          // m_launcher.setSpeed(0.0, 0.0);
          // m_currentlyLaunching = false;
          // m_indexer.stop();
          // m_intake.setSpeed(0.0);
        }
      }
      else {
        // If we don't have a note and we want to intake, set intake to intake speed, load a note to the indexer, and set launcher speed to 0.
        if (intakeOn) {
          // m_intake.setSpeed(intakeSpeed);
          // m_launcher.setSpeed(0.0, 0.0);
          // m_indexer.loadNote();
        }
        // If we don't have a note and we want to expel, set launcher, indexer, and intake to reversed speed.
        else if (expelOn) {
          // m_launcher.setSpeed(-leftLauncherAmpSpeed, -rightLauncherAmpSpeed);
          // m_indexer.expelNote();
          // m_intake.setSpeed(-intakeSpeed);
        }
        // If we don't have a note and we don't want to intake or expel, set launcher, indexer, and intake speeds to 0.
        else {
          // m_intake.setSpeed(0.0);
          // m_indexer.stop();
          // m_launcher.setSpeed(0.0, 0.0);
        }
      }
      m_launchCounter = 0;
    }
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
    double curSpeed = 0.0;
    double curTurn = 0.0;
    m_drivetrain.arcadeDrive(curSpeed, curTurn);
    //m_launcher.setSpeed(0.0, 0.0);
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    boolean driveForward = false;
    
    driveForward = m_copilotController.driveForward();

    //System.out.print("Right Encoder Pos [ " + m_drivetrain.getRightDrivePos() + " ]");

    if (driveForward) {
      //System.out.print("A BUTTON PRESSED");
      m_drivetrain.driveStraight(10.0);
    }
    else {
      m_drivetrain.driveStraight(0.0);
    }
    
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
  }
}
