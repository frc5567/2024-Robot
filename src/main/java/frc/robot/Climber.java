package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Servo;

/**
 * Encapsulates the left and right TalonSRX motors and sets their speed.
 */
public class Climber {
    TalonSRX m_leftClimber;
    TalonSRX m_rightClimber;
    Servo m_leftServo;
    Servo m_rightServo;

    /**
     * Constructor that instantiates and sets the inversion on the left and right climber motors.
     */
    Climber() {
        m_leftClimber = new TalonSRX(RobotMap.ClimberConstants.LEFT_CLIMBER_CAN_ID);
        m_rightClimber = new TalonSRX(RobotMap.ClimberConstants.RIGHT_CLIMBER_CAN_ID);

        m_leftServo = new Servo(RobotMap.ClimberConstants.LEFT_SERVO);
        m_rightServo = new Servo(RobotMap.ClimberConstants.RIGHT_SERVO);

        m_leftClimber.setInverted(false);
        m_leftClimber.setInverted(false);
    }

    /**
     * Sets the speed of the left motor.
     */
    public void setLeftSpeed(double speed) {
        m_leftClimber.set(TalonSRXControlMode.PercentOutput, speed);
    }

    /**
     * Sets the speed of the right motor.
     */
    public void setRightSpeed(double speed) {
        m_rightClimber.set(TalonSRXControlMode.PercentOutput, speed);
    }

    //TODO: Test servo values and java doc.
    public void extendLeftServo() {
        m_leftServo.set(0.3);
    }

    public void extendRightServo() {
        m_rightServo.set(0.3);
    }

    public void retractLeftServo() {
        m_leftServo.set(0.69);
    }

    public void retractRightServo() {
        m_rightServo.set(0.69);
    }

}
