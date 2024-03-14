package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake {
    TalonSRX m_intake;

    /**
     * Contructor method that instantiates the intake motor and sets coast mode.
     */
    Intake() {
        m_intake = new TalonSRX(RobotMap.IntakeConstants.INTAKE_CAN_ID);
        m_intake.setNeutralMode(NeutralMode.Coast);
    }

    /**
     * Method that sets the speed of the intake motor. 
     * @param intakeSpeed Percent power between -1 and 1 to spin the intake motor. 
     */
    public void setSpeed(double intakeSpeed) {
        m_intake.set(TalonSRXControlMode.PercentOutput, intakeSpeed);
    }

    /**
     * Method used to set the intake motor to 0.
     */
    public void stop() {
        this.setSpeed(0.0);
    }

    /**
     * Method used to set the intake motor to the intaking speed (0.5).
     */
    public void intake() {
        this.setSpeed(RobotMap.IntakeConstants.SPEED);
    }

    /**
     * Method to reverse the intake motor. Uses the negative intake speed (-0.5).
     */
    public void expel() {
        this.setSpeed(-RobotMap.IntakeConstants.SPEED);
    }
    
}
