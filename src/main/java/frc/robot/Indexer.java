package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class Indexer {
    private WPI_TalonSRX m_indexMotor;

    private DigitalInput m_indexSensor;
    
    /**
     * Constructor that instantiates the index motor and sensor objects.
     */
    public Indexer() {
        m_indexMotor = new WPI_TalonSRX(RobotMap.IndexerConstants.INDEXER_CAN_ID);
        m_indexSensor = new DigitalInput(RobotMap.IndexerConstants.SENSOR_PORT);
        m_indexMotor.setNeutralMode(NeutralMode.Coast);
    }

    /**
     * Sets the speed of the index motor.
     * @param speed input speed of the motor in percent from -1 to 1.
     */
    private void setIndexSpeed(double speed) {
        m_indexMotor.set(speed);
    }

    /**
     * Sets the speed of the index motor to 0.
     */
    public void stop() {
        setIndexSpeed(0.0);
    }

    /**
     * Read the indexs sensor's current value (boolean).
     * @return true if we have a note, false if there is no note.
     */
    public boolean readIndexSensor() {
        boolean haveANote = false;
        haveANote = !m_indexSensor.get();

        return haveANote;
    }

    /**
     * Helper method used to load a note from the intake.
     * If there is a note loaded, sets the indexer to 0. If there is no note sensed, sets the index speed to the Load speed (0.5).
     * @return true if there is a note loaded, false if the note is not loaded.
     */
    public boolean loadNote() {
        boolean noteLoaded = readIndexSensor();
        
        if (noteLoaded) {
            setIndexSpeed(0.0);
        }

        else {
            setIndexSpeed(RobotMap.IndexerConstants.LOAD_SPEED);
        }

        return noteLoaded;
    }

    /**
     * Helper method used to feed the note the launcher. Feed speed is 1.0.
     */
    public void feedNote() {
        setIndexSpeed(RobotMap.IndexerConstants.FEED_SPEED);
    }

    /**
     * Helper method used to expel the note.
     * Sets the index motor to the negative value of the load speed (-0.5).
     */
    public void expelNote() {
        setIndexSpeed(-RobotMap.IndexerConstants.LOAD_SPEED);
    }
}
