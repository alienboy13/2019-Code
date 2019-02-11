package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

public class Lift extends Subsystem {
    public Talon liftTalon, pivotTalon1, pivotTalon2;

    public Lift(){
        liftTalon = new Talon(Constants.kTALON_PORT_LIFT1);
        pivotTalon1 = new Talon(Constants.kTALON_PORT_LIFT2);
        pivotTalon2 = new Talon(Constants.kTALON_PORT_PIVOT);
    }
    public void initDefaultCommand(){

    }
}
