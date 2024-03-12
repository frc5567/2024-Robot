package frc.robot;

public class Auton {
    int m_step;
    boolean m_autonStart = false;

    private String m_path = "";

    // Sets the default path to Front One Note Exit.
    private String m_currentPath = RobotMap.AutonConstants.FRONT_ONE_NOTE_EXIT;

    private int m_feedLoopCount = 0;
    private int m_launchLoopCount = 0;

    /**
     * Constructor for auton class
     */
    public Auton() {
        m_step = 0;
    }

    /**
     * Sets counts to zero, resets steps, and autonStart to true.
     */
    public void init() {
        m_step = 0;
        m_autonStart = true;
        m_feedLoopCount = 0;
        m_launchLoopCount = 0;
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
        else if (m_currentPath == RobotMap.AutonConstants.TURN_LEFT_ONE_NOTE_EXIT) {
            System.out.println("Setting Auton path to Turn Left One Note Exit.");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.TURN_RIGHT_ONE_NOTE_EXIT) {
            System.out.println("Setting Auton path to Turn Right One Note Exit.");
            m_path = m_currentPath;
        }
        else {
            System.out.println("No path selected. Please restart auton and choose a path.");
        }

    }

    /**
     * Encapsulates all auton paths.
     * @param launcher passed in to utilize launch methods.
     * @param indexer passed in to utilize indexer methods.
     * @param drivetrain passed in to utilize driveStraight and turnToAngle.
     * @return true if we have completed auton.
     */
    public AutonInput periodic(Launcher launcher, Indexer indexer, Drivetrain drivetrain) {
        AutonInput newInput = new AutonInput();

        if (m_autonStart) {
            System.out.println("AUTON STARTED");
            System.out.println("Auton path [" + m_path + "]");
            m_autonStart = false;
        }

        switch(m_path) {
            case RobotMap.AutonConstants.FRONT_ONE_NOTE_EXIT:
            {
                switch(m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    
                    case 2:
                    {
                        if (++m_launchLoopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_launchLoopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (++m_feedLoopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.feedNote();
                                System.out.println("Step feeding: " + m_step + " loopcount:" + m_feedLoopCount + " - " + m_launchLoopCount);
                            }
                            else {
                                indexer.stop();
                                System.out.println("Step not feeding: " + m_step + " loopcount:" + m_feedLoopCount + " - " + m_launchLoopCount);
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Step: " + m_step);

                        //newInput.m_driveTarget = RobotMap.AutonConstants.FRONT_SPEAKER_EXIT_DIST;
                        if (drivetrain.driveStraight(RobotMap.AutonConstants.FRONT_SPEAKER_EXIT_DIST - RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST)) {
                            m_step += 1;
                        }
                        launcher.stop();
                        indexer.stop();
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Step: " + m_step);
                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case RobotMap.AutonConstants.TURN_LEFT_ONE_NOTE_EXIT:
            {
                switch (m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAngle(RobotMap.AutonConstants.TURN_LEFT_TO_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 3:
                    {
                        if (++m_launchLoopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_launchLoopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (++m_feedLoopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.feedNote();
                            }
                            else {
                                indexer.stop();
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Step complete: " + m_step);

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.BACK_UP_AFTER_LAUNCH_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAngle(RobotMap.AutonConstants.SUBWOOFER_ANGLE_FROM_WALL - RobotMap.AutonConstants.TURN_LEFT_TO_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Step: " + m_step);
               
                        if (drivetrain.driveStraight(RobotMap.AutonConstants.SIDE_SPEAKER_EXIT_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 7:
                    {
                        System.out.println("Step: " + m_step);

                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case RobotMap.AutonConstants.TURN_RIGHT_ONE_NOTE_EXIT:
            {
                switch (m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAngle(-RobotMap.AutonConstants.TURN_RIGHT_TO_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 3:
                    {
                        if (++m_launchLoopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_launchLoopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (++m_feedLoopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.feedNote();
                            }
                            else {
                                indexer.stop();
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;    
                    }
                    case 4:
                    {
                        System.out.println("Step complete: " + m_step);

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.BACK_UP_AFTER_LAUNCH_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAngle(-(RobotMap.AutonConstants.SUBWOOFER_ANGLE_FROM_WALL - RobotMap.AutonConstants.TURN_RIGHT_TO_LAUNCH_ANGLE))) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.SIDE_SPEAKER_EXIT_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;    
                    }
                    case 7:
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
