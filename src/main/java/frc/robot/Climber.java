package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;

/**
 * Encapsulates the left and right TalonSRX motors and sets their speed. Sets the positions of the left and right servos.
 */
public class Climber {
    TalonSRX m_leftClimber;
    TalonSRX m_rightClimber;
    Servo m_leftServo;
    Servo m_rightServo;
    DigitalInput m_rightSensor;
    DigitalInput m_leftSensor;

    /**
     * Constructor that instantiates and sets the inversion on the left and right climber motors.
     * Instantiates the left and right servos and sets their bounds. 
     */
    Climber() {
        m_leftClimber = new TalonSRX(RobotMap.ClimberConstants.LEFT_CLIMBER_CAN_ID);
        m_rightClimber = new TalonSRX(RobotMap.ClimberConstants.RIGHT_CLIMBER_CAN_ID);

        m_leftSensor = new DigitalInput(RobotMap.ClimberConstants.LEFT_SENSOR_PORT);
        m_rightSensor = new DigitalInput(RobotMap.ClimberConstants.RIGHT_SENSOR_PORT);

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
     * Read the left sensor's current value (boolean).
     * @return true if the left climber is retracted, false if it is extended.
     */
    public boolean getLeftSensor() {
        return !m_leftSensor.get();
    }

    /**
     * Read the right sensor's current value (boolean).
     * @return true if the right climber is retracted, false if it is extended.
     */
    public boolean getRightSensor() {
        return !m_rightSensor.get();
    }

    /**
     * Method used to get the positions of the left and right servos.
     */
    public void getServo() {
        double leftPosition = m_leftServo.getPosition();
        double rightPosition = m_rightServo.getPosition();
        System.out.println("left angle [" + leftPosition + "] Right angle [" + rightPosition + "]");
    }

    /**
     * Sets the servo positions to retract. This allows backfeed on the climber. Positions are 0 and 0.
     */
    public void unlockClimb() {
        m_leftServo.setPosition(RobotMap.ClimberConstants.LEFT_SERVO_UNLOCK_POS);
        m_rightServo.setPosition(RobotMap.ClimberConstants.RIGHT_SERVO_UNLOCK_POS);
    }

    /**
     * Sets the servo positions to extend. This restricts backfeed on the climber. Positions are 1 and 1.
     */
    public void lockClimb() {
        m_leftServo.setPosition(RobotMap.ClimberConstants.LEFT_SERVO_LOCK_POS);
        m_rightServo.setPosition(RobotMap.ClimberConstants.RIGHT_SERVO_LOCK_POS);
    }

    /**
     * Method to set the left climb motor to the climbing speed (0.5)
     */
    public void leftExtend() {
        this.setLeftSpeed(RobotMap.ClimberConstants.LEFT_SPEED);
    }

    /**
     * Method to set the right climb motor to the climbing speed (0.5)
     */
    public void rightExtend() {
        this.setRightSpeed(RobotMap.ClimberConstants.RIGHT_SPEED);
    }

    /**
     * Method to set the left climb motor to the negative climbing speed (-0.5)
     */
    public void leftRetract() {
        this.setLeftSpeed(-RobotMap.ClimberConstants.LEFT_SPEED);
    }

    /**
     * Method to set the right climb motor to the negative climbing speed (-0.5)
     */
    public void rightRetract() {
        this.setRightSpeed(-RobotMap.ClimberConstants.RIGHT_SPEED);
    }

    /**
     * Method to set the left climb motor to 0
     */
    public void leftStop() {
        this.setLeftSpeed(0.0);
    }

    /**
     * Method to set the right climb motor to 0
     */
    public void rightStop() {
        this.setRightSpeed(0.0);
    }
}
