package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class GamePad extends Joystick{
    
    /**
     * Constructor, used for calling the super constructor (constructor used in the parent class GenericHID).
     * @param port
     */
    public GamePad(final int port) {
        super(port);
    }

    /**
     * Enum that defines buttons on the GamePad and what they do when active
     */
    public enum GamePadControls {
        
        //TODO: update port numbers
        Left_Extend(10),
        Left_Retract(11),
        Right_Extend(1),
        Right_Retract(2),
        Lock_Ratchet(7),
        Unlock_Ratchet(4),
        Expel(8),
        Intake(12),
        Amp_Launch(5),
        Speaker_Launch(3),
        Drive_Forward(6),
        Turn_To_Target(9);

        public final int portNum;

        GamePadControls(int newPortNum) {
            this.portNum = newPortNum;
        }
    }

    /**
     * Checks if the Left Climber Extend button is pressed.
     * @return true if the Left Extend button was pressed, false if not.
     */
    public boolean getLeftExtend() {
        return super.getRawButton(GamePadControls.Left_Extend.portNum);
    }

    /**
     * Checks if the Left Climber Retract button is pressed.
     * @return true if the Left Retract button was pressed, false if not.
     */
    public boolean getLeftRetract() {
        return super.getRawButton(GamePadControls.Left_Retract.portNum);
    }

    /**
     * Checks if the Right Climber Extend button is pressed.
     * @return true if the Right Extend button was pressed, false if not.
     */
    public boolean getRightExtend() {
        return super.getRawButton(GamePadControls.Right_Extend.portNum);
    }

    /**
     * Checks if the Right Climber Retract button is pressed.
     * @return true if the Right Retract button was pressed, false if not.
     */
    public boolean getRightRetract() {
        return super.getRawButton(GamePadControls.Right_Retract.portNum);
    }

    /**
     * Checks if the Engage Ratchet button is pressed.
     * @return true if the Ratchet Engage button was pressed, false if not.
     */
    public boolean getLockRatchet() {
        return super.getRawButton(GamePadControls.Lock_Ratchet.portNum);
    }

    /**
     * Checks if the Disengage Ratchet button is pressed.
     * @return true if the Ratchet Disengage button was pressed, false if not.
     */
    public boolean getUnlockRatchet() {
        return super.getRawButton(GamePadControls.Unlock_Ratchet.portNum);
    }

    /**
     * Checks if the Expel button is pressed.
     * @return true if the Expel button is pressed, false if not.
     */
    public boolean getExpel() {
        return super.getRawButton(GamePadControls.Expel.portNum);
    }

    /**
     * Checks if the Intake button is pressed.
     * @return true if the Intake button is pressed, false if not.
     */
    public boolean getIntake() {
        return super.getRawButton(GamePadControls.Intake.portNum);
    }

    /**
     * Checks if the Amp Launch button is pressed.
     * @return true if the Amp Launch button is pressed, false if not.
     */
    public boolean getAmpLaunch() {
        return super.getRawButton(GamePadControls.Amp_Launch.portNum);
    }

    /**
     * Checks if the Speaker Launch button is pressed.
     * @return true if the Speaker Launch button is pressed, false if not.
     */
    public boolean getSpeakerLaunch() {
        return super.getRawButton(GamePadControls.Speaker_Launch.portNum);
    }

    /**
     * Checks if the manual Drive Straight button is pressed.
     * @return true if the manual Drive Straight button is pressed, false if not.
     */
    public boolean getManualDriveStraight() {
        return super.getRawButton(GamePadControls.Drive_Forward.portNum);
    }

    /**
     * Checks if the manual Turn To Target button is pressed.
     * @return true if the manual Turn To Target button is pressed, false if not.
     */
    public boolean getManualTurnToTarget() {
        return super.getRawButton(GamePadControls.Turn_To_Target.portNum);
    }

}
