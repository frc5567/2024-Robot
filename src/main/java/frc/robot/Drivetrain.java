package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class Drivetrain {

    private TalonFX m_leftLeader;
    private TalonFX m_rightLeader;
    private TalonFX m_leftFollower;
    private TalonFX m_rightFollower;

    /**
     * Constructor for the drivetrain class 
     * 
     */
    public Drivetrain() {
        m_leftLeader = new TalonFX(RobotMap.DrivetrainConstants.LEFT_LEADER_CAN_ID);
        m_rightLeader = new TalonFX(RobotMap.DrivetrainConstants.RIGHT_LEADER_CAN_ID);
        m_leftFollower = new TalonFX(RobotMap.DrivetrainConstants.LEFT_FOLLOWER_CAN_ID);
        m_rightFollower = new TalonFX(RobotMap.DrivetrainConstants.RIGHT_FOLLOWER_CAN_ID);

    }

    /**
     * Function to initalize the drivetrain
     */
    public void initDrivetrain() {

        TalonFXConfiguration leftConfiguration = new TalonFXConfiguration();
        TalonFXConfiguration rightConfiguration = new TalonFXConfiguration();

        leftConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        rightConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    }
}
