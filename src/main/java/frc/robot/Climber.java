package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Servo;

/**
 * Encapsulates the left and right TalonSRX motors and sets their speed. Sets the positions of the left and right servos.
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

        //2 mils on PWM is the max bound, 1 mils is the min bound, no deadband
        m_leftServo.setBoundsMicroseconds(2000, 1500, 1500, 1500, 1000);
        m_rightServo.setBoundsMicroseconds(2000, 1500, 1500, 1500, 1000);
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

    /**
     * Method used to get the positions of the left and right servos.
     */
    public void getServo() {
        double leftAngle = m_leftServo.getPosition();
        double rightAngle = m_rightServo.getPosition();
        System.out.println("left angle [" + leftAngle + "] Right angle [" + rightAngle + "]");
    }

    /**
     * Sets the servo positions to retract. This allows backfeed on the climber. 
     */
    public void unlockClimb() {
        m_leftServo.setPosition(RobotMap.ClimberConstants.LEFT_SERVO_UNLOCK_POS);
        m_rightServo.setPosition(RobotMap.ClimberConstants.RIGHT_SERVO_UNLOCK_POS);
    }

    /**
     * Sets the servo positions to extend. This restricts backfeed on the climber. 
     */
    public void lockClimb() {
        m_leftServo.setPosition(RobotMap.ClimberConstants.LEFT_SERVO_LOCK_POS);
        m_rightServo.setPosition(RobotMap.ClimberConstants.RIGHT_SERVO_LOCK_POS);
    }
}
