package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake {
    TalonSRX m_intake;

    /**
     * Contructor method that instantiates the intake motor. 
     */
    Intake() {
        m_intake = new TalonSRX(RobotMap.IntakeConstants.INTAKE_CAN_ID);
    }

    /**
     * Method that sets the speed of the intake motor. 
     * @param intakeSpeed Percent power between -1 and 1 to spin the intake motor. 
     */
    public void setSpeed(double intakeSpeed) {
        m_intake.set(TalonSRXControlMode.PercentOutput, intakeSpeed);
    }

    /**
     * Method to stop the intake motor
     */
    public void stop() {
        this.setSpeed(0.0);
    }

    /**
     * Method to set the intake motor to the intaking speed (0.5)
     */
    public void intake() {
        this.setSpeed(RobotMap.IntakeConstants.SPEED);
    }

    /**
     * Method to reverse the intake motor (-0.5)
     */
    public void expel() {
        this.setSpeed(-RobotMap.IntakeConstants.SPEED);
    }
    
}
