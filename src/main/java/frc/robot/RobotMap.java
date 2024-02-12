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
}
