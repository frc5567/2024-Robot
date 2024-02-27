package frc.robot;

import edu.wpi.first.wpilibj.RobotState;

public class CodriveInput {
    public RobotState m_desiredState;
    public double m_leftClimber;
    public double m_rightClimber;

    public CodriveInput() {
        m_leftClimber = 0.0;
        m_rightClimber = 0.0;
    }
}
