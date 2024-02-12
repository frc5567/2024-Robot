package frc.robot;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;

public class PilotController {
    private XboxController m_controller;

    private SlewRateLimiter m_accelFilter;

    private SlewRateLimiter m_accelLTFilter;
    private SlewRateLimiter m_accelRTFilter;

    /**
     * The possible values for DesiredDirection input.
     */
    public enum DesiredDirection {
        Initial,
        Reversed,
        NoChange
    };

    /**
     * Contructor for the pilot controller. Instantiates the xbox controller and slew rate limiters.
     */
    public PilotController() {
        m_controller = new XboxController(RobotMap.PilotControllerConstants.XBOX_CONTROLLER_USB_PORT);
        m_accelFilter = new SlewRateLimiter(RobotMap.PilotControllerConstants.ACCEL_SLEW_RATE);
        m_accelLTFilter = new SlewRateLimiter(RobotMap.PilotControllerConstants.ACCEL_SLEW_RATE);
        m_accelRTFilter = new SlewRateLimiter(RobotMap.PilotControllerConstants.ACCEL_SLEW_RATE);
    }

    /**
     * Sets the robot speed to the difference of the left and right triggers.
     * Applies a slew rate limiter. This caps the max rate of change.
     * @return the speed adjusted for deadband.
     */
    public double getDriverSpeed() {
        double speed = m_controller.getRightTriggerAxis() - m_controller.getLeftTriggerAxis();
        speed = m_accelFilter.calculate(speed);
        return adjustForDeadband(speed);
    }

    /**
     * Sets the turn speed using the left joystick.
     * Squares the turn input. This allows for more control by making large changes in input less impactful.
     * Also applies a turn scalar (currently 0.7). Also allows for more control.
     * @return the speed adjusted for deadband.
     */
    public double getDriverTurn() {
        double turnInput = -m_controller.getLeftX();
        double squaredTurnInput = turnInput * turnInput;
        squaredTurnInput = Math.copySign(squaredTurnInput, turnInput);

        double scaledTurnInput = (squaredTurnInput * RobotMap.PilotControllerConstants.TURN_SCALAR);

        return adjustForDeadband(scaledTurnInput);
    }

    /**
     * Method used to obtain the pilot input.
     * @return The state of the x button. True if pressed, false if not pressed
     */
    public boolean getIntakeButton() {
        boolean intakeInput = m_controller.getXButton();
        return intakeInput;
    }

    /**
     * Sets the desired direction of the drivetrain. The desired direction defaults to NoChange.
     * When the right bumper is pressed the desired direction is set to the initial direction.
     * When the left bumper is pressed the desired direction is set to the opposite direction.
     * @param desiredDirection set by the pilot based on input.
     */
    public DesiredDirection getPilotChangeControls() {
        DesiredDirection desiredDirection = DesiredDirection.NoChange;

        if(m_controller.getRightBumper()) {
            desiredDirection = DesiredDirection.Initial;
        }
        else if(m_controller.getLeftBumper()) {
            desiredDirection = DesiredDirection.Reversed;
        }

        return desiredDirection;
    }

    /**
     * Deadband method for stick.
     * @param stickInput takes a value from -1 to 1.
     * @return input value adjusted for deadband. It is a double with a value between -1 and 1.
     */
    private double adjustForDeadband(double stickInput) {
        double retVal = 0.0;
        double deadband = RobotMap.PilotControllerConstants.STICK_DEADBAND;

        // Takes the absolute value of stick input for simplification.
        double absStickInput = Math.abs(stickInput);

        // If the stick input is outside the deadband, adjust.
        if(!(absStickInput < deadband)) {
            retVal = (absStickInput / (1.0 - deadband));
            retVal = Math.copySign(retVal, stickInput);
        }

        return retVal;
    }
}
