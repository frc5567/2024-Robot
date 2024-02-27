package frc.robot;

public class CoDriveInput {
    public double m_leftClimber;
    public double m_rightClimber;
    public double m_intake;
    public double m_leftLauncher;
    public double m_rightLauncher;

    /**
     * Default instructor which instantiates input values.
     */
    public CoDriveInput() {
        m_leftClimber = 0.0;
        m_rightClimber = 0.0;
        m_intake = 0.0;
        m_leftLauncher = 0.0;
        m_rightLauncher = 0.0;
    }
}
