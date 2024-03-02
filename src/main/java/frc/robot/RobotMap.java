package frc.robot;

public class RobotMap {
    
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
         * The timeout in milliseconds of the CTRE methods.
         */
        public static final int TIMEOUT_MS = 30;

        /**
         * Drive Straight PID constants
         */
        public final static Gains DISTANCE_GAINS = new Gains( 50.0, 0.0,  0.0, 0.1);
        
        /**
         * Turn to Angle PID constants
         */
        public final static Gains TURNING_GAINS = new Gains( 0.01, 0.0,  4.0, 0.0);

        public final static int PID_PRIMARY = 0;
        public final static int PID_TURN = 1;

        public final static int GEAR_RATIO = 15;

        /**
         * This is a property of the Pigeon IMU, and should not be changed.
         */
        public final static int PIGEON_UNITS_PER_ROTATION = 8192;

        public final static int SENSOR_UNITS_PER_ROTATION = 2048;

        /**
         * Encoder deadband used in driveStraight.
         */
        public static final int DRIVE_STRAIGHT_DEADBAND = 5000;

        public static final double OPEN_RAMPS = 0.2;

        public static final double UPDATE_FREQUENCY = 100;

        public static final double DRIVE_ANGLE_DEADBAND = 0.8;
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
         * Absolute value of the deadband reange for stick input.
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
        public static final double LEFT_AMP_SPEED = 0.4;

        /**
         * Speed set to the right launcher motor when launching to the amp.
         */
        public static final double RIGHT_AMP_SPEED = 0.4;

        /**
         * Speed set to the left launcher motor when launching to the speaker. TODO: make sure this value is up to date with testing changes.
         */
        public static final double LEFT_SPEAKER_SPEED = 0.8;

        /**
         * Speed set to the right launcher motor when launching to the speaker.TODO: make sure this value is up to date with testing changes.
         */
        public static final double RIGHT_SPEAKER_SPEED = 0.95;

        
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
}
