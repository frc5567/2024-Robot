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
        
        Left_Up(1),
        Left_Down(2),
        Right_Up(3),
        Right_Down(4),
        Engage_Ratchet(5),
        Disengage_Ratchet(6),
        Expell(7),
        Intake(8),
        Amp_Launch(9),
        Speaker_Launch(10);
        //Extra_Button(11),
        //Extra_Button2(12),
    }
