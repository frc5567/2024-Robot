package frc.robot;

import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.hardware.TalonFX;
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

        this.configPID();
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

		/* FPID for Distance */
		m_rightConfig.Slot0.kV = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kV;
		m_rightConfig.Slot0.kP = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kP;
		m_rightConfig.Slot0.kI = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kI;
		m_rightConfig.Slot0.kD = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kD;

        //m_rightConfig.motionCurveStrength = 4;

		/* FPID for Heading */
        //TODO: figure out if kV is the correct feedforward gain.
		m_rightConfig.Slot1.kV = RobotMap.DrivetrainConstants.TURNING_GAINS.kV;
		m_rightConfig.Slot1.kP = RobotMap.DrivetrainConstants.TURNING_GAINS.kP;
		m_rightConfig.Slot1.kI = RobotMap.DrivetrainConstants.TURNING_GAINS.kI;
		m_rightConfig.Slot1.kD = RobotMap.DrivetrainConstants.TURNING_GAINS.kD;
		
		/* Config the neutral deadband. */
		//m_leftConfig.neutralDeadband = RobotMap.DrivetrainConstants.NEUTRAL_DEADBAND;
		//m_rightConfig.neutralDeadband = RobotMap.DrivetrainConstants.NEUTRAL_DEADBAND;

		/* Motion Magic Configs */
        var motionMagicConfigs = m_rightConfig.MotionMagic;
		//m_rightConfig.motionAcceleration = 9000; //(distance units per 100 ms) per second
		//m_rightConfig.motionCruiseVelocity = 12000; //distance units per 100 ms
        motionMagicConfigs.MotionMagicCruiseVelocity = 12000; 
        motionMagicConfigs.MotionMagicAcceleration = 9000;

		/* APPLY the config settings */
		//m_leftLeader.configAllSettings(m_leftConfig);
		//m_rightLeader.configAllSettings(m_rightConfig);
        m_leftLeader.getConfigurator().apply(m_leftConfig);
        m_rightLeader.getConfigurator().apply(m_rightConfig);

        /* Determine which slot affects which PID */
        //m_rightLeader.selectProfileSlot(0, RobotMap.DrivetrainConstants.PID_PRIMARY);
        //m_rightLeader.selectProfileSlot(1, RobotMap.DrivetrainConstants.PID_TURN);

    }
}