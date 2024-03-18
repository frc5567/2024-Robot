package frc.robot;

public class RobotMap {

    /**
     * The number of cycles we allow our launcher speed to speed up.
     */
    public static final int LAUNCH_SPIN_UP_COUNT = 25;

    /**
     * The number of cycles we allow our launcher to launch to the speaker.
     */
    public static final int ADDITIONAL_LAUNCH_COUNT = 25;

    /**
     * The number of cycles we wait during auton.
     */
    public static final int PAUSE_COUNT = 350;

    /**
     * The device number of the front camera.
     */
    public static final int FRONT_CAMERA_ID = 0;

    /**
     * The device number of the back camera.
     */
    public static final int BACK_CAMERA_ID = 1;
    
    /**
     * All drivetrain constants.
     */
    public static class DrivetrainConstants {

        /**
         * CAN ID of the left leader motor controller
         */
        public static final int LEFT_LEADER_CAN_ID = 5;

        /**
         * CAN ID of the right leader motor controller
         */
        public static final int RIGHT_LEADER_CAN_ID = 4;

        /**
         * CAN ID of the left follower motor controller
         */
        public static final int LEFT_FOLLOWER_CAN_ID = 7;

        /**
         * CAN ID of the right follower motor controller
         */
        public static final int RIGHT_FOLLOWER_CAN_ID = 6;

        /**
         * CAN ID of the Pigeon
         */
        public static final int PIGEON_CAN_ID = 3;

        /**
         * Drive Straight PID constants
         */
        public final static Gains DISTANCE_GAINS = new Gains( 50.0, 0.0,  0.0, 0.1);
        
        /**
         * Turn to Angle PID constants
         */
        public final static Gains TURNING_GAINS = new Gains( 0.0175, 0.0,  0.00135, 0.0);

        /**
         * Seconds it takes to speed up from 0% to 100% power for the drivetrain.
         */
        public static final double OPEN_RAMPS = 0.2;

        /**
         * The rate at which the device will publish the pigeon and drivetrain signals.
         */
        public static final double UPDATE_FREQUENCY = 100;

        /**
         * Used to differentiate turn speeds in turn to angle.
         */
        public static final double DRIVE_ANGLE_DEADBAND = 0.8;

        /**
         * Percent output for turn to target when angle is greater than 8 degrees.
         */
        public static final double LARGEST_ANGLE_SPEED = 0.12;

        /**
         * Percent output for turn to target when angle is between 4 and 8 degrees.
         */
        public static final double LARGER_MIDDLE_ANGLE_SPEED = 0.08;

        /**
         * Percent output for turn to target when angle is between 2.4 and 4 degrees.
         */
        public static final double SMALLER_MIDDLE_ANGLE_SPEED = 0.06;

        /**
         * Percent output for turn to target when angle is between 0.8 and 2.4 degrees.
         */
        public static final double SMALLEST_ANGLE_SPEED = 0.06;

        /**
         * Percent output for turn to target at which the bot won't turn
         */
        public static final double TURN_COMPLETE_SPEED = 0.03;

        /**
         * Number of cycles to watch for completed turn.
         */
        public static final int TURN_PID_CYCLE_COUNT = 5;
    }

    /**
     * All Pilot Controller constants.
     */
    public static class PilotControllerConstants {

        /**
         * USB port number of the xbox controller.
         */
        public static final int XBOX_CONTROLLER_USB_PORT = 0;

        /**
         * Absolute value of the deadband range for stick input.
         */
        public static final double STICK_DEADBAND = 0.09;

        /**
         * Adjusts output of turns to tone down the final output.
         */
        public static final double TURN_SCALAR = 0.7;

        /**
         * Rate limit for acceleration to prevent brownouts.
         */
        public static final double ACCEL_SLEW_RATE = 1.6;
    }

    /**
     * All intake constants.
     */
    public static class IntakeConstants {

        /**
         * CAN ID of the intake motor.
         */
        public static final int INTAKE_CAN_ID = 19;

        /**
         * Speed set to the intake motor when button is pressed.
         */
        public static final double SPEED = 0.5;
    }

    /**
     * All launcher constants.
     */
    public static class LauncherConstants {

        /**
         * CAN ID of the left launcher motor
         */
        public static final int LEFT_LAUNCHER_CAN_ID = 17;

        /**
         * CAN ID of the right launcher motor
         */
        public static final int RIGHT_LAUNCHER_CAN_ID = 18;

        /**
         * Speed set to the left launcher motor when launching to the amp.
         */
        public static final double LEFT_AMP_SPEED = 0.3;

        /**
         * Speed set to the right launcher motor when launching to the amp.
         */
        public static final double RIGHT_AMP_SPEED = 0.3;

        /**
         * Speed set to the left launcher motor when launching to the speaker.
         */
        public static final double LEFT_SPEAKER_SPEED = 0.9;

        /**
         * Speed set to the right launcher motor when launching to the speaker.
         */
        public static final double RIGHT_SPEAKER_SPEED = 0.9;
    }

    /**
     * All indexer constants.
     */
    public static class IndexerConstants {

        /**
         * CAN ID of the index motor.
         */
        public static final int INDEXER_CAN_ID = 10;

        /**
         * DIO port of the index sensor.
         */
        public static final int SENSOR_PORT = 0;

        /**
         * Speed set to index motor for loading note to intake.
         */
        public static final double LOAD_SPEED = 0.5;

        /**
         * Speed set to the index motor for feeding note into launcher.
         */
        public static final double FEED_SPEED = 1.0;
    }

    /**
     * All Climber constants
     */
    public static class ClimberConstants {

        /**
         * CAN ID of the left climber motor.
         */
        public static final int LEFT_CLIMBER_CAN_ID = 8;

        /**
         * CAN ID of the right climber motor.
         */

        public static final int RIGHT_CLIMBER_CAN_ID = 9;

        /**
         * PWM Channel ID for the left climber servo.
         */
        public static final int LEFT_SERVO = 0;

        /**
        * PWM Channel ID for the right climber servo.
        */
        public static final int RIGHT_SERVO = 1;

        /**
         * The position of the right servo when unlocked (extended).
         */
        public static final double RIGHT_SERVO_UNLOCK_POS = 0.0;

        /**
         * The position of the left servo when unlocked (extended).
         */
        public static final double LEFT_SERVO_UNLOCK_POS = 0.0;

        /**
         * The position of the right servo when locked (retracted).
         */
        public static final double RIGHT_SERVO_LOCK_POS = 1.0;

        /**
         * The position of the left servo when locked (retracted).
         */
        public static final double LEFT_SERVO_LOCK_POS = 1.0;

        /**
         * Speed set to the left climb when extending (positive) and retracting (negative)
         */
        public static final double LEFT_SPEED = 0.5;

        /**
         * Speed set to the right climb when extending (positive) and retracting (negative)
         */
        public static final double RIGHT_SPEED = 0.5;
    }

    /**
     * All Auton constants.
     */
    public static class AutonConstants {

        /**
         * Path used when starting directly in front of the subwoofer, launching one note, and exiting community.
         */
        public static final String FRONT_ONE_NOTE_EXIT = "Front One Note Exit";

        /**
         * Path used when starting on the left side of the subwoofer. Turns left to launch and then exits community.
         */
        public static final String TURN_LEFT_ONE_NOTE_EXIT = "Turn Left One Note Exit";

        /**
         * Path used when starting on the right side of the subwoofer. Turns right to launch and then exits community.
         */
        public static final String TURN_RIGHT_ONE_NOTE_EXIT = "Turn Right One Note Exit";

        /**
         * Path used when starting on the right side of the subwoofer and pausing before exiting. Turns right to launch, pauses, and then exits community.
         */
        public static final String TURN_RIGHT_ONE_NOTE_PAUSE_EXIT = "Turn Right One Note Pause Exit";

        /**
         * Path used when starting on the left side of the subwoofer and pausing before exiting. Turns left to launch, pauses, and then exits community.
         */
        public static final String TURN_LEFT_ONE_NOTE_PAUSE_EXIT = "Turn Left One Note Pause Exit";

        /**
         * Path used when starting on the front of the subwoofer, launching a note, exiting community, and intaking.
         */
        public static final String FRONT_TWO_NOTE_EXIT = "Front Two Note Exit";

        /**
         * Path used when starting on the left side of the subwoofer. Turns left to launch, exits community, and intakes a note.
         */
        public static final String TURN_LEFT_TWO_NOTE_EXIT = "Turn Left Two Note Exit";

        /**
         * Path used when starting on the right side of the subwoofer. Turns right to launch, exits community, and intakes a note.
         */
        public static final String TURN_RIGHT_TWO_NOTE_EXIT = "Turn Right Two Note Exit";

        /**
         * Path used when starting on the long wall on the red side, exiting the community, turning right, and moving middle notes >:D
         */
        public static final String RED_EVIL_GENIUS_PUSH = "Red Evil Genius Push";

        /**
         * Path used when starting on the long wall on the blue side, exiting the community, turning left, and moving middle notes >:D
         */
        public static final String BLUE_EVIL_GENIUS_PUSH = "Blue Evil Genius Push";

        /**
         * Path used when starting on the long wall on the red side, exiting the community, turning right, and moving middle notes >:D
         */
        public static final String RED_EVIL_GENIUS_SPIT = "Red Evil Genius Spit";

        /**
         * Path used when starting on the long wall on the blue side, exiting the community, turning left, and moving middle notes >:D
         */
        public static final String BLUE_EVIL_GENIUS_SPIT = "Blue Evil Genius Spit";

        /**
         * The distance to exit the community when starting directly in front of the subwoofer. 
         */
        public static final double FRONT_SPEAKER_EXIT_DIST = 50.0;

        /**
         * The distance to back up enough to launch to the speaker when starting fully against the front of the subwoofer.
         */
        public static final double FRONT_LAUNCH_BACK_UP_DIST = 6.0;

        /**
         * The distance to exit the community when starting on the side of the subwoofer.
         */
        public static final double SIDE_SPEAKER_EXIT_DIST = 60.0;

        /**
         * The distance to back up enough to launch to the speaker when starting fully against the side of the subwoofer.
         */
        public static final double LAUNCH_BACK_UP_DIST = 6.0;

        /**
         * The angle in degrees to turn left to launch to the speaker when starting flush against the subwoofer.
         */
        public static final double TURN_LEFT_TO_LAUNCH_ANGLE = 10.0;

        /**
         * The angle in degrees to turn right to launch to the speaker when starting flush against the subwoofer.
         */
        public static final double TURN_RIGHT_TO_LAUNCH_ANGLE = 9.0;

        /**
         * The distance to back up after launching to the speaker to give enough clearance for turning the rest of the way.
         */
        public static final double BACK_UP_AFTER_LAUNCH_DIST = 10.0;

        /**
         * The angle of the subwoofer from the wall.
         */
        public static final double SUBWOOFER_ANGLE_FROM_WALL = 60.0;

        /**
         * The distance to travel to the middle of the field when starting against the back wall.
         */
        public static final double DIST_TO_MID = 292.6;

        /**
         * 90 degree turning angle.
         */
        public static final double RIGHT_ANGLE = 90.0;

        /**
         * The distance to travel across the middle of the field to push notes.
         */
        public static final double PUSH_DIST = 223.0;

    }
}
