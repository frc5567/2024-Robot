package frc.robot;

public class CopilotController {

    private GamePad m_controller;

    /**
     * Constructor that sets the port for GamePad
     */
    public CopilotController() {
        m_controller = new GamePad(1);

    }

    /**
     * Takes copilot driver input from the controller/gamepad.
     * @return the CoDriveInput object representing Copilot input to the bot
     */
    public CodriveInput getCodriveInput() {
        CodriveInput coDriveInput = new CodriveInput();

        if (m_controller.getLeftExtend()) {
            coDriveInput.m_leftClimber = RobotMap.CopilotConstants.LEFT_CLIMBER_SPEED;
        }
        else if (m_controller.getLeftRetract()) {
            coDriveInput.m_leftClimber = -RobotMap.CopilotConstants.LEFT_CLIMBER_SPEED;
        }

        if (m_controller.getRightExtend()) {
            coDriveInput.m_rightClimber = RobotMap.CopilotConstants.RIGHT_CLIMBER_SPEED;
        }
        else if (m_controller.getRightRetract()) {
            coDriveInput.m_rightClimber = -RobotMap.CopilotConstants.RIGHT_CLIMBER_SPEED;
        }

        if (m_controller.getIntake()) {
            coDriveInput.m_intake = RobotMap.CopilotConstants.INTAKE_SPEED;
        }
        else if (m_controller.getAmpLaunch()) {
            coDriveInput.m_leftLauncher = RobotMap.CopilotConstants.LEFT_AMP_LAUNCH_SPEED;
            coDriveInput.m_rightLauncher = RobotMap.CopilotConstants.RIGHT_AMP_LAUNCH_SPEED;
        }
        else if (m_controller.getSpeakerLaunch()) {
            coDriveInput.m_leftLauncher = RobotMap.CopilotConstants.LEFT_SPEAKER_LAUNCH_SPEED;
            coDriveInput.m_rightLauncher = RobotMap.CopilotConstants.RIGHT_SPEAKER_LAUNCH_SPEED;
        }
        else if (m_controller.getExpel()) {
            coDriveInput.m_intake = -RobotMap.CopilotConstants.INTAKE_SPEED;
            coDriveInput.m_leftLauncher = -RobotMap.CopilotConstants.LEFT_AMP_LAUNCH_SPEED;
            coDriveInput.m_rightLauncher = -RobotMap.CopilotConstants.RIGHT_AMP_LAUNCH_SPEED;
        }

        return coDriveInput;
    }
}
