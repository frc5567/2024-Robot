package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class CopilotController {
    //private XboxController m_controller;
    private GamePad m_controller;

    public CopilotController() {
        //m_controller = new XboxController(1);
        m_controller = new GamePad(1);
    }

    /**
     * Method used to obtain the copilot input.
     * @return The state of the a button. True if pressed, false if not pressed
     */
    public boolean driveForward() {
        boolean driveForward = m_controller.getAButton();
        return driveForward;
    }
}
