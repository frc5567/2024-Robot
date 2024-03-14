// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  // private static final String kDefaultAuto = "Default";
  // private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private Drivetrain m_drivetrain;
  private PilotController m_pilotController;
  private Intake m_intake;
  private Launcher m_launcher;
  private Indexer m_indexer;
  private GamePad m_gamePad;
  private Climber m_climber;
  private Auton m_auton;
  private UsbCamera m_camera;
  private boolean m_currentlyLaunching;
  private boolean m_haveNote = false;

  private int m_launchCounter = 0;

  private Sendable m_sensorValue;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption(RobotMap.AutonConstants.FRONT_ONE_NOTE_EXIT, RobotMap.AutonConstants.FRONT_ONE_NOTE_EXIT);
    m_chooser.addOption(RobotMap.AutonConstants.TURN_LEFT_ONE_NOTE_EXIT, RobotMap.AutonConstants.TURN_LEFT_ONE_NOTE_EXIT);
    m_chooser.addOption(RobotMap.AutonConstants.TURN_RIGHT_ONE_NOTE_EXIT, RobotMap.AutonConstants.TURN_RIGHT_ONE_NOTE_EXIT);
    m_chooser.addOption(RobotMap.AutonConstants.TURN_RIGHT_ONE_NOTE_PAUSE_EXIT, RobotMap.AutonConstants.TURN_RIGHT_ONE_NOTE_PAUSE_EXIT);
    m_chooser.addOption(RobotMap.AutonConstants.TURN_LEFT_ONE_NOTE_PAUSE_EXIT, RobotMap.AutonConstants.TURN_LEFT_ONE_NOTE_PAUSE_EXIT);
    //m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    m_autoSelected = m_chooser.getSelected();

    Pigeon2 m_pigeon = new Pigeon2(RobotMap.DrivetrainConstants.PIGEON_CAN_ID);

    m_drivetrain = new Drivetrain(m_pigeon);
    m_pilotController = new PilotController();
    m_intake = new Intake();
    m_launcher = new Launcher();
    m_indexer = new Indexer();
    m_gamePad = new GamePad(1);
    m_climber = new Climber();
    m_auton = new Auton();

    m_currentlyLaunching = false;

    m_drivetrain.initDrivetrain();

    try {
      m_camera = CameraServer.startAutomaticCapture();

      m_camera.setResolution(160,120);
      m_camera.setFPS(30);

    } catch (Exception e){
      System.out.println("Camera failed to instantiate");
    }

    m_climber.unlockClimb();



    

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    m_autoSelected = m_chooser.getSelected();
    m_haveNote = m_indexer.readIndexSensor();
    SmartDashboard.putBoolean("HaveNote", m_haveNote);
    m_drivetrain.periodic();
  }

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
    m_auton.init();
    m_auton.selectPath(m_autoSelected);
    m_drivetrain.zeroSensors();
    m_drivetrain.brakeMode();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    AutonInput currentInput;
    currentInput = m_auton.periodic(m_launcher, m_indexer, m_drivetrain);
    
    if (currentInput.m_autonCompleted) {
      m_drivetrain.arcadeDrive(0.0, 0.0);
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    m_drivetrain.brakeMode();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double curSpeed = 0.0;
    double curTurn = 0.0;

    //Setting our controller buttons to false initially.
    boolean intakeOn = false;
    boolean ampLauncherOn = false;
    boolean speakerLauncherOn = false;
    boolean spinningUp = false;
    boolean expelOn = false;
    boolean leftClimberExtending = false;
    boolean rightClimberExtending = false;
    boolean leftClimberRetracting = false;
    boolean rightClimberRetracting = false;
    boolean lockClimbButton = false;
    boolean unlockClimbButton = false;

    PilotController.DesiredDirection desiredDirection = PilotController.DesiredDirection.NoChange;

    //Sets our variables for each action to our buttons/controls
    curSpeed = m_pilotController.getDriverSpeed();
    curTurn = m_pilotController.getDriverTurn();

    desiredDirection = m_pilotController.getPilotChangeControls();
    intakeOn = m_gamePad.getIntake();
    ampLauncherOn = m_gamePad.getAmpLaunch();
    speakerLauncherOn = m_gamePad.getSpeakerLaunch();
    spinningUp = m_gamePad.getSpinUp();

    expelOn = m_gamePad.getExpel();

    SmartDashboard.updateValues();

    m_drivetrain.setDesiredDirection(desiredDirection);

    m_drivetrain.arcadeDrive(curSpeed, curTurn);

    leftClimberExtending = m_gamePad.getLeftExtend();
    rightClimberExtending = m_gamePad.getRightExtend();
    leftClimberRetracting = m_gamePad.getLeftRetract();
    rightClimberRetracting = m_gamePad.getRightRetract();
    unlockClimbButton = m_gamePad.getUnlockRatchet();
    lockClimbButton = m_gamePad.getLockRatchet();

    //left climber controls
    if (leftClimberExtending) {
      m_climber.leftExtend();
    }
    else if (leftClimberRetracting) {
      m_climber.leftRetract();
    }
    else {
      m_climber.leftStop();
    }

    //Right climber controls
    if (rightClimberExtending) {
      m_climber.rightExtend();
    }
    else if (rightClimberRetracting) {
      m_climber.rightRetract();
    }
    else {
      m_climber.rightStop();
    }

    //Climber lock controls
    if (unlockClimbButton) {
      m_climber.unlockClimb();
    }
    else if (lockClimbButton) {
      m_climber.lockClimb();
    }

    if (m_currentlyLaunching) {
      // If we want to launch to the amp, set the launcher to amp speed and feed a note from the indexer.
      if (ampLauncherOn) {
        m_launcher.ampLaunch();
        m_indexer.feedNote();
      }
      // If we want to spin the launcher up to speed, set the launcher to speaker speed.
      // Then when we want to launch, feed a note from the indexer.
      else if (spinningUp) {
        m_launcher.speakerLaunch();

        if (speakerLauncherOn) {
          m_indexer.feedNote();
        }
        else {
          m_indexer.stop();
        }
      }
      // If we don't want to launch, set the launcher and indexer speeds to 0 and set currentlyLaunching to false.
      else {
        m_launcher.stop();
        m_currentlyLaunching = false;
        m_indexer.stop();
      }
    }
    else {
      if (m_haveNote) {
        // If currentlyLaunching is false and we have a note we want to launch to the amp,
        // set the launcher to amp speed, feed a note from the the indexer, set the intake speed to 0, and set currentlyLaunching to true.
        if (ampLauncherOn) {
          m_launcher.ampLaunch();
          m_indexer.feedNote();
          m_intake.stop();
          m_currentlyLaunching = true;
        }
        // If we want to spin the launcher up to speed, set the launcher to speaker speed.
        // Then when we want to launch, feed a note from the indexer.
        else if (spinningUp) {
          m_launcher.speakerLaunch();

          if (speakerLauncherOn) {
            m_indexer.feedNote();
          }
          else {
            m_indexer.stop();
          }
        }
        // If currentlyLaunching is false and we want to expel, set launcher, indexer, and intake to reversed speed.
        else if (expelOn) {
          m_launcher.expel();
          m_indexer.expelNote();
          m_intake.expel();
        }
        // If currentlyLaunching if false and we don't want to launch or expel, set launcher, indexer, and intake speeds to 0.
        else {
          m_launcher.stop();
          m_currentlyLaunching = false;
          m_indexer.stop();
          m_intake.stop();
        }
      }
      else {
        // If we don't have a note and we want to intake, set intake to intake speed, load a note to the indexer, and set launcher speed to 0.
        if (intakeOn) {
          m_intake.intake();
          m_launcher.stop();
          m_indexer.loadNote();
        }
        // If we don't have a note and we want to expel, set launcher, indexer, and intake to reversed speed.
        else if (expelOn) {
          m_launcher.expel();
          m_indexer.expelNote();
          m_intake.expel();
        }
        // If we don't have a note and we don't want to intake or expel, set launcher, indexer, and intake speeds to 0.
        else {
          m_intake.stop();
          m_indexer.stop();
          m_launcher.stop();
        }
      }
      m_launchCounter = 0;
    }
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    m_drivetrain.coastMode();
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
    double curSpeed = 0.0;
    double curTurn = 0.0;
    m_drivetrain.arcadeDrive(curSpeed, curTurn);
    m_launcher.setSpeed(0.0, 0.0);
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
  }
}
