package frc.robot;

public class Auton {
    int m_step;
    boolean m_autonStart = false;

    private String m_path = "";

    // Sets the default path to Front One Note Exit.
    private String m_currentPath = RobotMap.AutonConstants.FRONT_ONE_NOTE_EXIT;

    private int m_loopCount = 0;
    private int m_pauseLoopCount = 0;

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
        m_loopCount = 0;
        m_pauseLoopCount = 0;
        
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
        else if (m_currentPath == RobotMap.AutonConstants.TURN_RIGHT_ONE_NOTE_PAUSE_EXIT) {
            System.out.println("Setting Auton path to Turn Right One Note pause Exit.");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.TURN_LEFT_ONE_NOTE_PAUSE_EXIT) {
            System.out.println("Setting Auton path to Turn Left One Note pause Exit.");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.FRONT_TWO_NOTE_EXIT) {
            System.out.println("Setting Auton path to Front Two Note Exit.");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.TURN_LEFT_TWO_NOTE_EXIT) {
            System.out.println("Setting Auton path to Turn Left Two Note Exit.");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.TURN_RIGHT_TWO_NOTE_EXIT) {
            System.out.println("Setting Auton path to Turn Right Two Note Exit.");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.BLUE_EVIL_GENIUS_PUSH) {
            System.out.println("Setting Auton path to Blue Evil Genius Push. >:D");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.RED_EVIL_GENIUS_PUSH) {
            System.out.println("Setting Auton path to Red Evil Genius Push. >:D");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.BLUE_EVIL_GENIUS_SPIT) {
            System.out.println("Setting Auton path to Blue Evil Genius Spit. >:D");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.RED_EVIL_GENIUS_SPIT) {
            System.out.println("Setting Auton path to Red Evil Genius Spit. >:D");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.RIGHT_THREE_NOTE) {
            System.out.println("Setting Auton path to Right Three Note");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.LEFT_THREE_NOTE) {
            System.out.println("Setting Auton path to Left Three Note");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.RIGHT_THREE_PLUS_NOTE) {
            System.out.println("Setting Auton path to Right Three Plus Note");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.LEFT_THREE_PLUS_NOTE) {
            System.out.println("Setting Auton path to Left Three Plus Note");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.RED_MID_FIELD) {
            System.out.println("Setting Auton path to Red Mid Field");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.BLUE_MID_FIELD) {
            System.out.println("Setting Auton path to Blue Mid Field");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.BLUE_MID_FIELD_TWO) {
            System.out.println("Setting Auton path to Blue Mid Field Two");
            m_path = m_currentPath;
        }
        else if (m_currentPath == RobotMap.AutonConstants.RED_MID_FIELD_TWO) {
            System.out.println("Setting Auton path to Red Mid Field Two");
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
    public AutonInput periodic(Launcher launcher, Indexer indexer, Drivetrain drivetrain, Intake intake) {
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

                        // Begins spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;  
                        }
                        break;
                    }
                    case 2:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                                System.out.println("Step feeding: " + m_step + " loopcount:" + m_loopCount + " - " + m_loopCount);
                            }
                            else {
                                indexer.stop();
                                System.out.println("Step not feeding: " + m_step + " loopcount:" + m_loopCount + " - " + m_loopCount);
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
            case RobotMap.AutonConstants.BLUE_MID_FIELD:
            {
                switch (m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        // Begins spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();
                        intake.stop();
                        indexer.stop();

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Step: " + m_step);

                        // Continues spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();

                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.TURN_LEFT_TO_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 3:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
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

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.MID_EXIT_ZONE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.SUBWOOFER_ANGLE_FROM_WALL)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Step: " + m_step);

                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 3) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 7:
                    {
                        System.out.println("Step: " + m_step);
               
                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.MID_FINISH_DIST) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            indexer.stop();
                            intake.stop();
                            m_step += 1;
                        }
                        break;
                    }
                    case 8:
                    {
                        System.out.println("Step: " + m_step);

                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case RobotMap.AutonConstants.RED_MID_FIELD:
            {
                switch (m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        // Begins spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();
                        intake.stop();
                        indexer.stop();

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Step: " + m_step);

                        // Continues spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();

                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.TURN_RIGHT_TO_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 3:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
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

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.MID_EXIT_ZONE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.SUBWOOFER_ANGLE_FROM_WALL)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Step: " + m_step);

                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 3) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 7:
                    {
                        System.out.println("Step: " + m_step);

                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.MID_FINISH_DIST) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        launcher.stop();
                        break;  
                    }
                    case 8:
                    {
                        System.out.println("Step: " + m_step);

                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case RobotMap.AutonConstants.BLUE_MID_FIELD_TWO:
            {
                switch (m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);
                        // Begins spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();
                        intake.stop();
                        indexer.stop();

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Step: " + m_step);

                        // Continues spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();
                        intake.stop();
                        indexer.stop();

                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.TURN_LEFT_TO_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 3:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
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
                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        if (drivetrain.driveStraight(RobotMap.AutonConstants.MID_EXIT_ZONE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);
                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.SUBWOOFER_ANGLE_FROM_WALL)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Step: " + m_step);
                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 3) {
                            drivetrain.zeroDistance();
                            m_loopCount = 0;
                            m_step += 1;
                        }
                        break;
                    }
                    case 7:
                    {
                        System.out.println("Step: " + m_step);
                        
                        launcher.stop();
                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.MID_FINISH_DIST) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            indexer.stop();
                            intake.stop();
                            drivetrain.slowMidField();
                            m_step += 1;
                        }
                        break;
                    }
                    case 8:
                    {
                        System.out.println("Step: " + m_step);
                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        if (drivetrain.driveStraight(-RobotMap.AutonConstants.MID_LAUNCH_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 9:
                    {
                        System.out.println("Step: " + m_step);
                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.TURN_LEFT_TO_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 10:
                    {
                        System.out.println("Step: " + m_step);
                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 3) {
                            drivetrain.zeroDistance();
                            m_loopCount = 0;
                            m_step += 1;
                        }
                        break;
                    }
                    case 11:
                    {
                        System.out.println("Step: " + m_step);

                        ++m_loopCount;
                        launcher.speakerLaunch();
                        intake.stop();
                        indexer.stop();

                        if (drivetrain.driveStraight(-RobotMap.AutonConstants.MID_EXIT_ZONE + 10)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step += 1;
                        }
                        break;
                    }
                    case 12:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                            }
                            else {
                                indexer.stop();
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 13:
                    {
                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        System.out.println("Step: " + m_step);
                        drivetrain.configPID();
                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case RobotMap.AutonConstants.RED_MID_FIELD_TWO:
            {
                switch (m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        // Begins spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();
                        intake.stop();
                        indexer.stop();

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Step: " + m_step);

                        // Continues spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();

                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.TURN_RIGHT_TO_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 3:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
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

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.MID_EXIT_ZONE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.SUBWOOFER_ANGLE_FROM_WALL)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Step: " + m_step);

                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 3) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 7:
                    {
                        System.out.println("Step: " + m_step);

                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.MID_FINISH_DIST) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        launcher.stop();
                        break;  
                    }
                    case 8:
                    {
                        System.out.println("Step: " + m_step);
                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        if (drivetrain.driveStraight(-RobotMap.AutonConstants.MID_LAUNCH_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 9:
                    {
                        System.out.println("Step: " + m_step);
                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.TURN_LEFT_TO_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 10:
                    {
                        System.out.println("Step: " + m_step);
                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 3) {
                            drivetrain.zeroDistance();
                            m_loopCount = 0;
                            m_step += 1;
                        }
                        break;
                    }
                    case 11:
                    {
                        System.out.println("Step: " + m_step);

                        ++m_loopCount;
                        launcher.speakerLaunch();
                        intake.stop();
                        indexer.stop();

                        if (drivetrain.driveStraight(-RobotMap.AutonConstants.MID_EXIT_ZONE + 10)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step += 1;
                        }
                        break;
                    }
                    case 12:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                            }
                            else {
                                indexer.stop();
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 13:
                    {
                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        System.out.println("Step: " + m_step);
                        drivetrain.configPID();
                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case RobotMap.AutonConstants.TURN_LEFT_ONE_NOTE_PAUSE_EXIT:
            {
                switch (m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        // Begins spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Step: " + m_step);

                        // Continues spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();

                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.TURN_LEFT_TO_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step += 1;
                        }
                        break;
                    }
                    case 3:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
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
                        if (++m_pauseLoopCount > RobotMap.PAUSE_COUNT) {
                            m_step += 1;
                            m_pauseLoopCount = 0;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step complete: " + m_step);

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.BACK_UP_AFTER_LAUNCH_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.SUBWOOFER_ANGLE_FROM_WALL)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 7:
                    {
                        System.out.println("Step: " + m_step);

                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 3) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 8:
                    {
                        System.out.println("Step: " + m_step);

                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.SIDE_SPEAKER_EXIT_DIST) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        launcher.stop();
                        break;
                    }
                    case 9:
                    {
                        System.out.println("Step: " + m_step);

                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case RobotMap.AutonConstants.TURN_RIGHT_ONE_NOTE_PAUSE_EXIT:
            {
                switch (m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        // Begins spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Step: " + m_step);

                        // Continues spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;

                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.TURN_RIGHT_TO_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step += 1;
                        }
                        break;
                    }
                    case 3:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
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
                        if (++m_pauseLoopCount > RobotMap.PAUSE_COUNT) {
                            m_step += 1;
                            m_pauseLoopCount = 0;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step complete: " + m_step);

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.BACK_UP_AFTER_LAUNCH_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.SUBWOOFER_ANGLE_FROM_WALL)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 7:
                    {
                        System.out.println("Step: " + m_step);

                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 3) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 8:
                    {
                        System.out.println("Step: " + m_step);

                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.SIDE_SPEAKER_EXIT_DIST) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        launcher.stop();
                        break;
                    }
                    case 9:
                    {
                        System.out.println("Step: " + m_step);

                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case RobotMap.AutonConstants.FRONT_TWO_NOTE_EXIT:
            {
                switch(m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        // Begins spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 2:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                                System.out.println("Step feeding: " + m_step + " loopcount:" + m_loopCount + " - " + m_loopCount);
                            }
                            else {
                                indexer.stop();
                                System.out.println("Step not feeding: " + m_step + " loopcount:" + m_loopCount + " - " + m_loopCount);
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Step: " + m_step);

                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.FRONT_SPEAKER_EXIT_DIST - RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        launcher.stop();
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Step: " + m_step);

                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.driveStraight(-(RobotMap.AutonConstants.FRONT_SPEAKER_EXIT_DIST - RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST))) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);

                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                            }
                            else {
                                indexer.stop();
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 6:
                    {
                        indexer.stop();
                        intake.stop();
                        System.out.println("Step: " + m_step);
                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case RobotMap.AutonConstants.TURN_LEFT_TWO_NOTE_EXIT:
            {
                switch (m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        // Begins spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Step: " + m_step);

                        // Continues spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();

                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.TURN_LEFT_TO_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 3:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
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
                        System.out.println("Step: " + m_step);

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.BACK_UP_AFTER_LAUNCH_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.SUBWOOFER_ANGLE_FROM_WALL)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Step: " + m_step);

                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 3) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 7:
                    {
                        System.out.println("Step: " + m_step);
               
                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.SIDE_SPEAKER_EXIT_DIST) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 8:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.driveStraight(-RobotMap.AutonConstants.SECOND_NOTE_LAUNCH_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 9:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.TURN_LEFT_TO_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 10:
                    {
                        System.out.println("Step: " + m_step);

                        // Continues spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();

                        if (drivetrain.driveStraight(-RobotMap.AutonConstants.BACK_UP_AFTER_LAUNCH_DIST)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 11:
                    {
                        System.out.println("Step: " + m_step);

                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                            }
                            else {
                                indexer.stop();
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 12:
                    {
                        System.out.println("Step: " + m_step);

                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case RobotMap.AutonConstants.TURN_RIGHT_TWO_NOTE_EXIT:
            {
                switch (m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        // Begins spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Step: " + m_step);

                        // Continues spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.TURN_RIGHT_TO_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 3:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
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
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.BACK_UP_AFTER_LAUNCH_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.SUBWOOFER_ANGLE_FROM_WALL)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Step: " + m_step);

                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 3) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 7:
                    {
                        System.out.println("Step: " + m_step);

                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.SIDE_SPEAKER_EXIT_DIST) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;    
                    }
                    case 8:
                    {
                        System.out.println("Step: " + m_step);
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.driveStraight(-RobotMap.AutonConstants.SECOND_NOTE_LAUNCH_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 9:
                    {
                        System.out.println("Step: " + m_step);

                        indexer.stop();
                        intake.stop();

                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.TURN_RIGHT_TO_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 10:
                    {
                        System.out.println("Step: " + m_step);

                        // Continues spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.driveStraight(-RobotMap.AutonConstants.BACK_UP_AFTER_LAUNCH_DIST)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 11:
                    {
                        System.out.println("Step: " + m_step);

                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                            }
                            else {
                                indexer.stop();
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 12:
                    {
                        System.out.println("Step: " + m_step);
                        indexer.stop();
                        intake.stop();

                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case (RobotMap.AutonConstants.RED_EVIL_GENIUS_PUSH):
            {
                switch (m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.DIST_TO_MID)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.RIGHT_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;

                    }
                    case 3:
                    {
                        System.out.println("Step: " + m_step);

                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 3) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Step: " + m_step);
                        
                        //intake.expel();
                        if (drivetrain.driveStraight(RobotMap.AutonConstants.PUSH_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);

                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case (RobotMap.AutonConstants.BLUE_EVIL_GENIUS_PUSH):
            {
                switch (m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.DIST_TO_MID)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.RIGHT_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;

                    }
                    case 3:
                    {
                        System.out.println("Step: " + m_step);

                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 3) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Step: " + m_step);

                        //intake.expel();
                        if (drivetrain.driveStraight(-RobotMap.AutonConstants.PUSH_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);

                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case (RobotMap.AutonConstants.RED_EVIL_GENIUS_SPIT):
            {
                switch (m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        if (drivetrain.driveStraight(RobotMap.AutonConstants.DIST_TO_MID)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.RIGHT_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;

                    }
                    case 3:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 3) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Step: " + m_step);
                        
                        drivetrain.slowEvilGenius();
                        
                        intake.setSpeed(1.0);
                        indexer.setIndexSpeed(1.0);
                        launcher.ampLaunch();
                        //intake.expel();
                        if (drivetrain.driveStraight(RobotMap.AutonConstants.PUSH_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.configPID();
                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case (RobotMap.AutonConstants.BLUE_EVIL_GENIUS_SPIT):
            {
                switch (m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        if (drivetrain.driveStraight(RobotMap.AutonConstants.DIST_TO_MID)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.RIGHT_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;

                    }
                    case 3:
                    {
                        System.out.println("Step: " + m_step);
                        
                        drivetrain.slowEvilGenius();

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 3) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Step: " + m_step);

                        intake.setSpeed(1.0);
                        indexer.setIndexSpeed(1.0);
                        launcher.ampLaunch();
                        //intake.expel();
                        if (drivetrain.driveStraight(RobotMap.AutonConstants.PUSH_DIST)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.configPID();
                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case RobotMap.AutonConstants.RIGHT_THREE_NOTE:
            {
                switch(m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        // Begins spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 2:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                                System.out.println("Step feeding: " + m_step + " loopcount:" + m_loopCount + " - " + m_loopCount);
                            }
                            else {
                                indexer.stop();
                                System.out.println("Step not feeding: " + m_step + " loopcount:" + m_loopCount + " - " + m_loopCount);
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Step: " + m_step);

                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.FRONT_SPEAKER_EXIT_DIST - RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        launcher.stop();
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Step: " + m_step);

                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.driveStraight(-(RobotMap.AutonConstants.FRONT_SPEAKER_EXIT_DIST - RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST))) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);

                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                            }
                            else {
                                indexer.stop();
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Step: " + m_step);

                        indexer.stop();
                        intake.stop();
                        if (drivetrain.driveStraight(RobotMap.AutonConstants.THIRD_NOTE_BACKUP)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;  
                    }
                    case 7:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.THIRD_NOTE_ANGLE - 1.5)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 8:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 2) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 9:
                    {
                        System.out.println("Step: " + m_step);
                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.THIRD_NOTE_DRIVE_FORWARD) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;  
                    }
                    case 10:
                    {
                        System.out.println("Step: " + m_step);

                        indexer.stop();
                        intake.stop();
                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.THIRD_NOTE_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step +=1;
                        }
                        break;
                    }
                    case 11:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 2) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 12:
                    {
                        System.out.println("Step: " + m_step);

                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();
                        if (drivetrain.driveStraight(-RobotMap.AutonConstants.THIRD_NOTE_LAUNCH_DIST)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;  
                    }
                    case 13:
                    {
                        System.out.println("Step: " + m_step);

                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                            }
                            else {
                                indexer.stop();
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 14:
                    {
                        indexer.stop();
                        intake.stop();
                        System.out.println("Step: " + m_step);
                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case RobotMap.AutonConstants.LEFT_THREE_NOTE:
            {
                switch(m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        // Begins spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 2:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                                System.out.println("Step feeding: " + m_step + " loopcount:" + m_loopCount + " - " + m_loopCount);
                            }
                            else {
                                indexer.stop();
                                System.out.println("Step not feeding: " + m_step + " loopcount:" + m_loopCount + " - " + m_loopCount);
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Step: " + m_step);

                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.FRONT_SPEAKER_EXIT_DIST - RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        launcher.stop();
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Step: " + m_step);

                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.driveStraight(-(RobotMap.AutonConstants.FRONT_SPEAKER_EXIT_DIST - RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST))) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);

                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                            }
                            else {
                                indexer.stop();
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Step: " + m_step);

                        indexer.stop();
                        intake.stop();
                        if (drivetrain.driveStraight(RobotMap.AutonConstants.THIRD_NOTE_BACKUP)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;  
                    }
                    case 7:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.THIRD_NOTE_ANGLE + 1.5)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 8:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 2) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 9:
                    {
                        System.out.println("Step: " + m_step);
                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.THIRD_NOTE_DRIVE_FORWARD + 3) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;  
                    }
                    case 10:
                    {
                        System.out.println("Step: " + m_step);

                        indexer.stop();
                        intake.stop();
                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.THIRD_NOTE_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step +=1;
                        }
                        break;
                    }
                    case 11:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 2) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 12:
                    {
                        System.out.println("Step: " + m_step);

                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();
                        if (drivetrain.driveStraight(-RobotMap.AutonConstants.THIRD_NOTE_LAUNCH_DIST - 3)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;  
                    }
                    case 13:
                    {
                        System.out.println("Step: " + m_step);

                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                            }
                            else {
                                indexer.stop();
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 14:
                    {
                        indexer.stop();
                        intake.stop();
                        System.out.println("Step: " + m_step);
                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case RobotMap.AutonConstants.RIGHT_THREE_PLUS_NOTE:
            {
                switch(m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        // Begins spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 2:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                                System.out.println("Step feeding: " + m_step + " loopcount:" + m_loopCount + " - " + m_loopCount);
                            }
                            else {
                                indexer.stop();
                                System.out.println("Step not feeding: " + m_step + " loopcount:" + m_loopCount + " - " + m_loopCount);
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Step: " + m_step);

                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.FRONT_SPEAKER_EXIT_DIST - RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        launcher.stop();
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Step: " + m_step);

                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.driveStraight(-(RobotMap.AutonConstants.FRONT_SPEAKER_EXIT_DIST - RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST))) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);

                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                            }
                            else {
                                indexer.stop();
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Step: " + m_step);

                        indexer.stop();
                        intake.stop();
                        if (drivetrain.driveStraight(RobotMap.AutonConstants.THIRD_NOTE_BACKUP)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;  
                    }
                    case 7:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.THIRD_NOTE_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 8:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 2) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 9:
                    {
                        System.out.println("Step: " + m_step);
                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.THIRD_NOTE_DRIVE_FORWARD) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;  
                    }
                    case 10:
                    {
                        System.out.println("Step: " + m_step);

                        indexer.stop();
                        intake.stop();
                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.THIRD_NOTE_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step +=1;
                        }
                        break;
                    }
                    case 11:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 2) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 12:
                    {
                        System.out.println("Step: " + m_step);

                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();
                        if (drivetrain.driveStraight(-RobotMap.AutonConstants.THIRD_NOTE_LAUNCH_DIST)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;  
                    }
                    case 13:
                    {
                        System.out.println("Step: " + m_step);

                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                            }
                            else {
                                indexer.stop();
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 14:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.FOURTH_NOTE_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 15:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 2) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 16:
                    {
                        System.out.println("Step: " + m_step);
                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.FOURTH_NOTE_DIST) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break; 
                    }
                    case 17:
                    {
                        indexer.stop();
                        intake.stop();
                        System.out.println("Step: " + m_step);
                        newInput.m_autonCompleted = true;
                        break;
                    }
                }
                break;
            }
            case RobotMap.AutonConstants.LEFT_THREE_PLUS_NOTE:
            {
                switch(m_step) {
                    case 1:
                    {
                        System.out.println("Step: " + m_step);

                        // Begins spinning launcher up to speed for speaker launch to decrease launch time and increments loop count.
                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 2:
                    {
                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                                System.out.println("Step feeding: " + m_step + " loopcount:" + m_loopCount + " - " + m_loopCount);
                            }
                            else {
                                indexer.stop();
                                System.out.println("Step not feeding: " + m_step + " loopcount:" + m_loopCount + " - " + m_loopCount);
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Step: " + m_step);

                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.FRONT_SPEAKER_EXIT_DIST - RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        launcher.stop();
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Step: " + m_step);

                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();

                        if (drivetrain.driveStraight(-(RobotMap.AutonConstants.FRONT_SPEAKER_EXIT_DIST - RobotMap.AutonConstants.FRONT_LAUNCH_BACK_UP_DIST))) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Step: " + m_step);

                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                            }
                            else {
                                indexer.stop();
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Step: " + m_step);

                        indexer.stop();
                        intake.stop();
                        if (drivetrain.driveStraight(RobotMap.AutonConstants.THIRD_NOTE_BACKUP)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;  
                    }
                    case 7:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.THIRD_NOTE_ANGLE + 1.5)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 8:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 2) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 9:
                    {
                        System.out.println("Step: " + m_step);
                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.THIRD_NOTE_DRIVE_FORWARD) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;  
                    }
                    case 10:
                    {
                        System.out.println("Step: " + m_step);

                        indexer.stop();
                        intake.stop();
                        if (drivetrain.turnToAnglePID(RobotMap.AutonConstants.THIRD_NOTE_LAUNCH_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step +=1;
                        }
                        break;
                    }
                    case 11:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 2) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 12:
                    {
                        System.out.println("Step: " + m_step);

                        ++m_loopCount;
                        launcher.speakerLaunch();
                        indexer.stop();
                        intake.stop();
                        if (drivetrain.driveStraight(-RobotMap.AutonConstants.THIRD_NOTE_DRIVE_FORWARD)) {
                            drivetrain.zeroDistance();
                            if (m_loopCount >= RobotMap.LAUNCH_SPIN_UP_COUNT){
                                m_loopCount = RobotMap.LAUNCH_SPIN_UP_COUNT;
                            }
                            m_step +=1;
                        }
                        break;  
                    }
                    case 13:
                    {
                        System.out.println("Step: " + m_step);

                        if (++m_loopCount >= (RobotMap.LAUNCH_SPIN_UP_COUNT + RobotMap.ADDITIONAL_LAUNCH_COUNT)) {
                            System.out.println("Step complete: " + m_step);
                            m_loopCount = 0;
                            launcher.stop();
                            indexer.stop();
                            m_step += 1;
                        }
                        else {
                            launcher.speakerLaunch();
                            if (m_loopCount > RobotMap.LAUNCH_SPIN_UP_COUNT){
                                indexer.speakerFeedNote();
                            }
                            else {
                                indexer.stop();
                            }
                        }
                        drivetrain.arcadeDrive(0.0, 0.0);
                        break;
                    }
                    case 14:
                    {
                        System.out.println("Step: " + m_step);

                        if (drivetrain.turnToAnglePID(-RobotMap.AutonConstants.FOURTH_NOTE_ANGLE)) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 15:
                    {
                        System.out.println("Step: " + m_step);

                        intake.stop();
                        indexer.stop();
                        launcher.stop();
                        drivetrain.arcadeDrive(0.0, 0.0);

                        if (++m_loopCount >= 2) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break;
                    }
                    case 16:
                    {
                        System.out.println("Step: " + m_step);
                        if (indexer.readIndexSensor()) {
                            indexer.stop();
                            intake.stop();
                        }
                        else {
                            indexer.loadNote();
                            intake.intake();
                        }

                        if (drivetrain.driveStraight(RobotMap.AutonConstants.FOURTH_NOTE_DIST) && indexer.readIndexSensor()) {
                            drivetrain.zeroDistance();
                            m_step += 1;
                        }
                        break; 
                    }
                    case 17:
                    {
                        indexer.stop();
                        intake.stop();
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
