package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.DifferentialSensorsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.Slot1Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DifferentialMotionMagicDutyCycle;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.compound.Diff_DutyCycleOut_Position;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.mechanisms.MechanismState;
import com.ctre.phoenix6.mechanisms.SimpleDifferentialMechanism;
import com.ctre.phoenix6.mechanisms.SimpleDifferentialMechanism.DisabledReason;
import com.ctre.phoenix6.signals.DifferentialSensorSourceValue;
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

    private SimpleDifferentialMechanism m_diffMechanism;

    private DifferentialMotionMagicDutyCycle m_diffMM;

    /**
     * The variable that keeps track of current drivetrain direction.
     * True is inital direction (forward). False is reversed control.
     */
    private boolean m_isDrivetrainForward;

    private TalonFXConfiguration m_leftConfig = new TalonFXConfiguration();
    private TalonFXConfiguration m_rightConfig = new TalonFXConfiguration();

    final MotionMagicVoltage m_motmag = new MotionMagicVoltage(0.0);

    /**
     * Constructor for the drivetrain class 
     */
    public Drivetrain(Pigeon2 pigeon) {
        // Sets the corresponding CAN IDs to each of the drivetrain motors.
        m_leftLeader = new TalonFX(RobotMap.DrivetrainConstants.LEFT_LEADER_CAN_ID);
        m_rightLeader = new TalonFX(RobotMap.DrivetrainConstants.RIGHT_LEADER_CAN_ID);
        m_leftFollower = new TalonFX(RobotMap.DrivetrainConstants.LEFT_FOLLOWER_CAN_ID);
        m_rightFollower = new TalonFX(RobotMap.DrivetrainConstants.RIGHT_FOLLOWER_CAN_ID);

        m_diffMechanism = new SimpleDifferentialMechanism(m_rightLeader, m_leftLeader, true);
        m_diffMM = new DifferentialMotionMagicDutyCycle(5.0, 0.0, false, 0, 1, false, false, false );

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
        //m_pigeon.getConfigurator().apply(pigeonConfiguration);

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

    public void drivetrainPeriodic() {
        m_diffMechanism.periodic();

        MechanismState mechState = m_diffMechanism.getMechanismState();

        if (mechState == MechanismState.Disabled) {
            DisabledReason disabledReason = m_diffMechanism.getDisabledReason();
            System.out.println("Disabled reason [" + disabledReason + "]");
        }
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
        //m_pigeon.setYaw(0.0, RobotMap.DrivetrainConstants.TIMEOUT_MS);
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
     * Drive straight forward (or backward for negative distance) a set number of inches
     * @param distance a double passed for setting what magnitude of distance the robot must travel in inches
     * @return a boolean designating whether the target has been reached
     */
    public boolean driveStraight(double distance) {
        boolean reachedTarget = false;
        // 6" wheels means 18.85" per rotation
        double rotations = distance * 6; // 18.85;
        //double target_sensorUnits = (RobotMap.DrivetrainConstants.SENSOR_UNITS_PER_ROTATION * rotations) * RobotMap.DrivetrainConstants.GEAR_RATIO;
        //double target_turn = 0.0; // don't turn

        //if((m_outputCounter % 100) == 0) {
        //    System.out.println("driveStraight [" + target_sensorUnits + "] Current Position [" + getEncoderPositions().m_rightLeaderPos + "]");
        //    System.out.println("output [" + m_rightLeader.getMotorOutputPercent() + "] velocity [" + m_rightLeader.getSelectedSensorVelocity() + "]");
        //}

        /* Configured for MotionMagic on Quad Encoders' Sum and Auxiliary PID on Pigeon */
        //m_rightLeader.set(ControlMode.MotionMagic, target_sensorUnits, DemandType.AuxPID, target_turn);
        //m_leftLeader.follow(m_rightLeader, FollowerType.AuxOutput1);
        //m_rightFollower.follow(m_rightLeader);
        //m_leftFollower.follow(m_leftLeader);

        //m_rightLeader.setControl(m_motmag.withPosition(rotations).withSlot(0));
        //m_leftLeader.setControl(new Follower(m_rightLeader.getDeviceID(), true));
        StatusCode myStatus = m_diffMechanism.setControl(m_diffMM.withTargetPosition(rotations));
        var mechState = m_diffMechanism.getMechanismState();

        if (mechState == MechanismState.Disabled) {
            var disabledReason = m_diffMechanism.getDisabledReason();
            System.out.println("Disabled reason [" + disabledReason + "]");
        }

        double left = m_leftLeader.get();
        double right = m_rightLeader.get();
        System.out.println("Status[" + myStatus + "][" + left + "][" + right + "] mechanism state [" + mechState + "]"); 
        // if ((Math.abs(m_rightLeader.getPosition().getValueAsDouble() - rotations) < RobotMap.DrivetrainConstants.DRIVE_STRAIGHT_DEADBAND) && m_rightLeader.getPosition().getValueAsDouble() < 100) {
        //     reachedTarget = true;
        // }

        return reachedTarget;
    }

    /**
     * Sets up the PID configuration for drive straight (or turn)
     */
    public void configPID() {

        double v = 0.3;
        double s = 0.2;
        double p = 50;

		/* FPID for Distance */
        Slot0Configs slot0 = m_rightConfig.Slot0;
		slot0.kV = v;
		slot0.kP = p;
		slot0.kI = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kI;
		slot0.kD = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kD;
        slot0.kS = s;

        Slot1Configs slot1 = m_rightConfig.Slot1;
		slot1.kV = v;
		slot1.kP = p;
		slot1.kI = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kI;
		slot1.kD = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kD;
        slot1.kS = s;
        //m_rightConfig.motionCurveStrength = 4;

		/* Motion Magic Configs */
        MotionMagicConfigs motionMagRight = m_rightConfig.MotionMagic;

        motionMagRight.MotionMagicCruiseVelocity = 3.5; 
        motionMagRight.MotionMagicAcceleration = 5.0;
        motionMagRight.MotionMagicJerk = 50.0;

        FeedbackConfigs rightFeedbackConfig = m_rightConfig.Feedback;
        rightFeedbackConfig.SensorToMechanismRatio = 9.82;

        //LEFT
        Slot0Configs slot_0 = m_leftConfig.Slot0;
		slot_0.kV = v;
		slot_0.kP = p;
		slot_0.kI = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kI;
		slot_0.kD = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kD;
        slot_0.kS = s;

        Slot1Configs slot_1 = m_leftConfig.Slot1;
		slot_1.kV = v;
		slot_1.kP = p;
		slot_1.kI = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kI;
		slot_1.kD = RobotMap.DrivetrainConstants.DISTANCE_GAINS.kD;
        slot_1.kS = s;

        MotionMagicConfigs motionMagLeft = m_leftConfig.MotionMagic;

        motionMagLeft.MotionMagicCruiseVelocity = 3.5; 
        motionMagLeft.MotionMagicAcceleration = 5.0;
        motionMagLeft.MotionMagicJerk = 50.0;

        FeedbackConfigs leftFeedbackConfig = m_leftConfig.Feedback;
        leftFeedbackConfig.SensorToMechanismRatio = 9.82;

        //Right side
        DifferentialSensorsConfigs sensRight = m_rightConfig.DifferentialSensors;

        sensRight.withDifferentialSensorSource(DifferentialSensorSourceValue.RemoteTalonFX_Diff);
        //sens.withDifferentialRemoteSensorID(m_leftLeader.getDeviceID());
        sensRight.withDifferentialTalonFXSensorID(m_leftLeader.getDeviceID());

		/* APPLY the config settings */
        m_rightLeader.getConfigurator().apply(m_rightConfig);


        //Left side!
        // DifferentialSensorsConfigs sensLeft = m_leftConfig.DifferentialSensors;

        // sensLeft.withDifferentialSensorSource(DifferentialSensorSourceValue.RemoteTalonFX_Diff);
        // sensLeft.withDifferentialTalonFXSensorID(m_rightLeader.getDeviceID());

        m_leftLeader.getConfigurator().apply(m_leftConfig);

        System.out.println("Left Leader ID [" + m_leftLeader.getDeviceID() + "] Right Leader ID [" + m_rightLeader.getDeviceID() + "]");

        /* Determine which slot affects which PID */
        //m_rightLeader.selectProfileSlot(0, RobotMap.DrivetrainConstants.PID_PRIMARY);
        //m_rightLeader.selectProfileSlot(1, RobotMap.DrivetrainConstants.PID_TURN);

    }
}