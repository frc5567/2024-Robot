package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class Launcher {
    TalonFX m_launcherLeft;
    TalonFX m_launcherRight;

    final DutyCycleOut m_motorOutput = new DutyCycleOut(0.0);

    Launcher() {
        m_launcherLeft = new TalonFX(RobotMap.LauncherConstants.LEFT_LAUNCHER_CAN_ID);
        m_launcherRight = new TalonFX(RobotMap.LauncherConstants.RIGHT_LAUNCHER_CAN_ID);

        TalonFXConfiguration leftConfiguration = new TalonFXConfiguration();
        TalonFXConfiguration rightConfiguration = new TalonFXConfiguration();

        // Sets brake mode to both motors.
        leftConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        rightConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        // Sets inversion to both motors
        leftConfiguration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        rightConfiguration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        // Applies the corresponding configurations to each launcher motor.
        m_launcherLeft.getConfigurator().apply(leftConfiguration);
        m_launcherRight.getConfigurator().apply(rightConfiguration);

        // Enables safety mode which requires input to the motors every cycle or it will set them to 0.
        m_launcherLeft.setSafetyEnabled(true);
        m_launcherRight.setSafetyEnabled(true);
    }

    /**
     * Method that tells the motors how fast to spin using percentages.
     * @param leftSpeed percent power to spin the left motor.
     * @param rightSpeed percent power to spin the right motor. 
     */
    public void setSpeed(double leftSpeed, double rightSpeed) {

        m_launcherLeft.setControl(m_motorOutput.withOutput(leftSpeed));
        m_launcherRight.setControl(m_motorOutput.withOutput(rightSpeed));
    }

    /**
     * Method that sets the launcher speeds to the amp speeds
     */
    public void ampLaunch() {
        this.setSpeed(RobotMap.LauncherConstants.LEFT_AMP_SPEED, RobotMap.LauncherConstants.RIGHT_AMP_SPEED);
    }

    /**
     * Method that sets the launcher speeds to the speaker speeds
     */
    public void speakerLaunch() {
        this.setSpeed(RobotMap.LauncherConstants.LEFT_SPEAKER_SPEED, RobotMap.LauncherConstants.RIGHT_SPEAKER_SPEED);
    }

    /**
     * Method to stop both launch motors
     */
    public void stop() {
        this.setSpeed(0.0, 0.0);
    }
}
