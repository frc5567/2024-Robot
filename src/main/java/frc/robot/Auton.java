package frc.robot;

public class Auton {
    int m_step;

    private String m_path = "";

    boolean m_autonStart = false;

    private String m_currentPath = RobotMap.AutonConstants.FRONT_ONE_NOTE_EXIT;

    private Launcher m_launcher;
    private Indexer m_indexer;
    public Drivetrain m_drivetrain;

    private int m_feedLoopCount = 0;

    private int m_launchLoopCount = 0;

    /**
     * Constructor for auton class
     */
    public Auton() {
        m_step = 0;

    }

    public void init() {
        m_step = 0;
        m_autonStart = true;
    }

    /**
     * Method for grabbing the selected auton path from shuffleboard
     */
    public void selectPath(String autonPath) {
        m_currentPath = autonPath;
        m_step = 1;

        if (m_currentPath == RobotMap.AutonConstants.FRONT_ONE_NOTE_EXIT) {
            System.out.println("Setting Auton path to Front One Note Exit.");
            m_path = m_currentPath;
        }
        else {
            System.out.println("No path selected. Please restart auton and choose a path.");
        }

    }

    public AutonInput periodic() {
        AutonInput newInput = new AutonInput();

        if (m_autonStart) {
            System.out.println("AUTON STARTED");
            System.out.println("Auton path [" + m_path + "]");
            m_autonStart = false;
        }

        // if (stepCompleted) {
        //     System.out.println(m_step + "Step Completed!");
        //     m_step += 1;
        // }

        switch(m_path) {
            case RobotMap.AutonConstants.FRONT_ONE_NOTE_EXIT:
            {
                switch(m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);
                        m_launcher.speakerLaunch();
                        if (++m_feedLoopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                            m_indexer.feedNote();
                        }
                        else {
                            m_indexer.stop();
                        }
                        
                        m_launchLoopCount ++;
                        if (m_launchLoopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT) {
                            m_launchLoopCount = 0;
                            m_launcher.stop();
                            m_indexer.stop();
                            m_step += 1;
                            break;
                        }
                        
                    }
                    case 2:
                    {
                        System.out.println("Step: " + m_step);

                        //newInput.m_driveTarget = RobotMap.AutonConstants.FRONT_SPEAKER_EXIT_DIST;
                        if (m_drivetrain.driveStraight(RobotMap.AutonConstants.FRONT_SPEAKER_EXIT_DIST)) {
                            m_step += 1;
                        }
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Step: " + m_step);
                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
        }    
        return newInput;
    }
}
