package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain {

    private TalonFX m_leftLeader;
    private TalonFX m_rightLeader;
    private TalonFX m_leftFollower;
    private TalonFX m_rightFollower;

    private DifferentialDrive m_drive;

    /**
     * The variable that keeps track of current drivetrain direction.
     * True is inital direction (forward). False is reversed control.
     */
    private boolean m_isDrivetrainForward;

    /**
     * Constructor for the drivetrain class 
     */
    public Drivetrain() {
        // Sets the corresponding CAN IDs to each of the drivetrain motors.
        m_leftLeader = new TalonFX(RobotMap.DrivetrainConstants.LEFT_LEADER_CAN_ID);
        m_rightLeader = new TalonFX(RobotMap.DrivetrainConstants.RIGHT_LEADER_CAN_ID);
        m_leftFollower = new TalonFX(RobotMap.DrivetrainConstants.LEFT_FOLLOWER_CAN_ID);
        m_rightFollower = new TalonFX(RobotMap.DrivetrainConstants.RIGHT_FOLLOWER_CAN_ID);

        m_drive = new DifferentialDrive(m_leftLeader, m_rightLeader);

        m_isDrivetrainForward = true;
    }

    /**
     * Function to initalize the drivetrain
     */
    public void initDrivetrain() {
 
        TalonFXConfiguration leftConfiguration = new TalonFXConfiguration();
        TalonFXConfiguration rightConfiguration = new TalonFXConfiguration();

        // Sets brake mode to the left and right configurations.
        leftConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        rightConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        // Counter clockwise is not inverted, clockwise is inverted.
        leftConfiguration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        rightConfiguration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        // Applies the corresponding configurations to each drivetrain motor.
        m_leftLeader.getConfigurator().apply(leftConfiguration);
        m_leftFollower.getConfigurator().apply(leftConfiguration);
        m_rightLeader.getConfigurator().apply(rightConfiguration);
        m_rightFollower.getConfigurator().apply(rightConfiguration);

        // Sets the Followers to follow the Leaders.
        m_leftFollower.setControl(new Follower(m_leftLeader.getDeviceID(), false));
        m_rightFollower.setControl(new Follower(m_rightLeader.getDeviceID(), false));

        // Enables safety mode which requires input to the motors every cycle or it will set them to 0.
        m_leftLeader.setSafetyEnabled(true);
        m_rightLeader.setSafetyEnabled(true);

        m_isDrivetrainForward = true;
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
}