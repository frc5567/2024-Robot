package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.sensors.PigeonIMU_StatusFrame;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain {

    private TalonFX m_leftLeader;
    private TalonFX m_rightLeader;
    private TalonFX m_leftFollower;
    private TalonFX m_rightFollower;

    private Pigeon2 m_pigeon;

    private DifferentialDrive m_drive;

    /**
     * The variable that keeps track of current drivetrain direction.
     * True is inital direction (forward). False is reversed control.
     */
    private boolean m_isDrivetrainForward;

    private TalonFXConfiguration m_leftConfig = new TalonFXConfiguration();
    private TalonFXConfiguration m_rightConfig = new TalonFXConfiguration();

    /**
     * Constructor for the drivetrain class 
     */
    public Drivetrain(Pigeon2 pigeon) {
        // Sets the corresponding CAN IDs to each of the drivetrain motors.
        m_leftLeader = new TalonFX(RobotMap.DrivetrainConstants.LEFT_LEADER_CAN_ID);
        m_rightLeader = new TalonFX(RobotMap.DrivetrainConstants.RIGHT_LEADER_CAN_ID);
        m_leftFollower = new TalonFX(RobotMap.DrivetrainConstants.LEFT_FOLLOWER_CAN_ID);
        m_rightFollower = new TalonFX(RobotMap.DrivetrainConstants.RIGHT_FOLLOWER_CAN_ID);

        m_pigeon = pigeon;

        m_drive = new DifferentialDrive(m_leftLeader, m_rightLeader);

        m_isDrivetrainForward = true;
    }

    /**
     * Function to initalize the drivetrain
     */
    public void initDrivetrain() {

        Pigeon2Configuration pigeonConfiguration = new Pigeon2Configuration();

        // Sets brake mode to the left and right configurations.
        m_leftConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        m_rightConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        // Counter clockwise is not inverted, clockwise is inverted.
        m_leftConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        m_rightConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        // Applies the corresponding configurations to each drivetrain motor and the Pigeon.
        m_leftLeader.getConfigurator().apply(m_leftConfig);
        m_leftFollower.getConfigurator().apply(m_leftConfig);
        m_rightLeader.getConfigurator().apply(m_rightConfig);
        m_rightFollower.getConfigurator().apply(m_rightConfig);
        m_pigeon.getConfigurator().apply(pigeonConfiguration);

        // Sets the Followers to follow the Leaders.
        m_leftFollower.setControl(new Follower(m_leftLeader.getDeviceID(), false));
        m_rightFollower.setControl(new Follower(m_rightLeader.getDeviceID(), false));

        // Enables safety mode which requires input to the motors every cycle or it will set them to 0.
        m_leftLeader.setSafetyEnabled(true);
        m_rightLeader.setSafetyEnabled(true);

        m_isDrivetrainForward = true;

        this.zeroSensors();
    }

    /**
     * Method that makes the drivetrain move foreward/backwards and turn.
     * @param speed Value between -1 and 1 for robot speed.
     * @param turn Value between -1 and 1 for turning.
     */
    public void arcadeDrive(double speed, double turn) {
        if(m_isDrivetrainForward) {
            m_drive.arcadeDrive(speed, turn);
        }
        else {
            m_drive.arcadeDrive(-speed, turn);
        }
    }

    /**
     * Zeros out the encoder positions and the Pigeon.
     */
    public void zeroSensors() {
        m_leftLeader.setPosition(0.0, RobotMap.DrivetrainConstants.TIMEOUT_MS);
        m_rightLeader.setPosition(0.0, RobotMap.DrivetrainConstants.TIMEOUT_MS);
        m_pigeon.setYaw(0.0, RobotMap.DrivetrainConstants.TIMEOUT_MS);
    }

    /**
     * Used to zero integrated sensors position.
     */
    public void zeroDistance() {
        m_leftLeader.setPosition(0.0, RobotMap.DrivetrainConstants.TIMEOUT_MS);
        m_rightLeader.setPosition(.0, RobotMap.DrivetrainConstants.TIMEOUT_MS);
    }

    /**
     * Gets the encoder positions of the left drivetrain motors. Units are rotations.
     */
    public double getLeftDrivePos() {
        double leftPos = m_leftLeader.getPosition().getValueAsDouble();
        return leftPos;
    }

    /**
     * Gets the encoder positions of the right drivetrain motors. Units are rotations.
     */
    public double getRightDrivePos() {
        double rightPos = m_rightLeader.getPosition().getValueAsDouble();
        return rightPos;
    }

    /**
     * Method used to set motor directions while driving.
     * @param desiredDirection true represents the initial direction, false represents the reversed controls.
     */
    public void setDesiredDirection(PilotController.DesiredDirection desiredDirection) {

        if(desiredDirection == PilotController.DesiredDirection.Initial) {
            m_isDrivetrainForward = true;
        }
        else if(desiredDirection == PilotController.DesiredDirection.Reversed) {
            m_isDrivetrainForward = false;
        }
    }

    /**
     * Sets up the PID configuration for drive straight (or turn)
     */
    public void configPID() {

        /* Configure the left Talon's selected sensor as integrated sensor */
		m_leftConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor; //Local Feedback Source

		/* Configure the Remote (Left) Talon's selected sensor as a remote sensor for the right Talon */
		m_rightConfig.Feedback.FeedbackRemoteSensorID = m_leftLeader.getDeviceID(); //Device ID of Remote Source
		m_rightConfig.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RemoteCANcoder; // TalonFX_SelectedSensor; //Remote Source Type
		
		/* Now that the Left sensor can be used by the master Talon,
		 * set up the Left (Aux) and Right (Master) distance into a single
		 * Robot distance as the Master's Selected Sensor 0. */
        //TalonFXInvertType _leftInvert = m_leftLeader.getInverted() ? TalonFXInvertType.Clockwise : TalonFXInvertType.CounterClockwise;
        TalonFXInvertType _rightInvert = m_rightLeader.getInverted() ? TalonFXInvertType.Clockwise : TalonFXInvertType.CounterClockwise; 
		setRobotDistanceConfigs(_rightInvert, m_rightConfig);

		/* FPID for Distance */
		m_rightConfig.Slot0.kV = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kF;
		m_rightConfig.Slot0.kP = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kP;
		m_rightConfig.Slot0.kI = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kI;
		m_rightConfig.Slot0.kD = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kD;
		m_rightConfig.Slot0.integralZone = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kIzone;
		m_rightConfig.Slot0.closedLoopPeakOutput = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kPeakOutput;

        m_rightConfig.motionCurveStrength = 4;

		/** Heading Configs */
		m_rightConfig.remoteFilter1.remoteSensorDeviceID = m_pigeon.getDeviceID();    //Pigeon Device ID
		m_rightConfig.remoteFilter1.remoteSensorSource = RemoteSensorSource.Pigeon_Yaw; //This is for a Pigeon over CAN
		m_rightConfig.auxiliaryPID.selectedFeedbackSensor = FeedbackDevice.RemoteSensor1; //Set as the Aux Sensor
		m_rightConfig.auxiliaryPID.selectedFeedbackCoefficient = 3600.0 / RobotMap.DrivetrainConstants.PIGEON_UNITS_PER_ROTATION; //Convert Yaw to tenths of a degree

		/* false means talon's local output is PID0 + PID1, and other side Talon is PID0 - PID1
		 *   This is typical when the master is the right Talon FX and using Pigeon
		 * 
		 * true means talon's local output is PID0 - PID1, and other side Talon is PID0 + PID1
		 *   This is typical when the master is the left Talon FX and using Pigeon
		 */
		m_rightConfig.auxPIDPolarity = true;

		/* FPID for Heading */
        //TODO: figure out if kV is the correct feedforward gain.
		m_rightConfig.Slot1.kV = RobotMap.DrivetrainConstants.TURNING_GAINS.kF;
		m_rightConfig.Slot1.kP = RobotMap.DrivetrainConstants.TURNING_GAINS.kP;
		m_rightConfig.Slot1.kI = RobotMap.DrivetrainConstants.TURNING_GAINS.kI;
		m_rightConfig.Slot1.kD = RobotMap.DrivetrainConstants.TURNING_GAINS.kD;
		m_rightConfig.Slot1.integralZone = RobotMap.DrivetrainConstants.TURNING_GAINS.kIzone;
		m_rightConfig.Slot1.closedLoopPeakOutput = RobotMap.DrivetrainConstants.TURNING_GAINS.kPeakOutput;


		/* Config the neutral deadband. */
		m_leftConfig.neutralDeadband = RobotMap.DrivetrainConstants.NEUTRAL_DEADBAND;
		m_rightConfig.neutralDeadband = RobotMap.DrivetrainConstants.NEUTRAL_DEADBAND;


		/**
		 * 1ms per loop.  PID loop can be slowed down if need be.
		 * For example,
		 * - if sensor updates are too slow
		 * - sensor deltas are very small per update, so derivative error never gets large enough to be useful.
		 * - sensor movement is very slow causing the derivative error to be near zero.
		 */
		int closedLoopTimeMs = 1;
		m_rightLeader.configClosedLoopPeriod(0, closedLoopTimeMs, RobotMap.DrivetrainConstants.TIMEOUT_MS);
		m_rightLeader.configClosedLoopPeriod(1, closedLoopTimeMs, RobotMap.DrivetrainConstants.TIMEOUT_MS);

		/* Motion Magic Configs */
		m_rightConfig.motionAcceleration = 9000; //(distance units per 100 ms) per second
		m_rightConfig.motionCruiseVelocity = 12000; //distance units per 100 ms

		/* APPLY the config settings */
		m_leftLeader.configAllSettings(m_leftConfig);
		m_rightLeader.configAllSettings(m_rightConfig);

		/* Set status frame periods to ensure we don't have stale data */
		/* These aren't configs (they're not persistant) so we can set these after the configs.  */
		m_rightLeader.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, RobotMap.DrivetrainConstants.TIMEOUT_MS);
		m_rightLeader.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, RobotMap.DrivetrainConstants.TIMEOUT_MS);
		m_rightLeader.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, RobotMap.DrivetrainConstants.TIMEOUT_MS);
		m_rightLeader.setStatusFramePeriod(StatusFrame.Status_10_Targets, 10, RobotMap.DrivetrainConstants.TIMEOUT_MS);
		m_leftLeader.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, RobotMap.DrivetrainConstants.TIMEOUT_MS);
		m_pigeon.setStatusFramePeriod(PigeonIMU_StatusFrame.CondStatus_9_SixDeg_YPR , 5, RobotMap.DrivetrainConstants.TIMEOUT_MS);

        /* Determine which slot affects which PID */
        m_rightLeader.selectProfileSlot(0, RobotMap.DrivetrainConstants.PID_PRIMARY);
        m_rightLeader.selectProfileSlot(1, RobotMap.DrivetrainConstants.PID_TURN);

    }
}