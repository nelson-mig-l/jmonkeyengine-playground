package trans.america.experiments.fancy.car;

import com.jme3.bullet.control.VehicleControl;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;

public class ControllableCar implements ActionListener {

    public static final String LEFTS = "Lefts";
    public static final String RIGHTS = "Rights";
    public static final String UPS = "Ups";
    public static final String DOWNS = "Downs";
    public static final String RESET = "Reset";

    public static final int ACCELERATION_FORCE = 800;
    public static final float BRAKE_FORCE = 40f;

    private float steeringValue = 0;
    private float accelerationValue = 0;

    private final VehicleControl control;

    public ControllableCar(PhysicalCar car) {
        this.control = car.getControl();
    }

    @Override
    public void onAction(String binding, boolean value, float tpf) {
        if (binding.equals(LEFTS)) {
            if (value) {
                steeringValue += .5f;
            } else {
                steeringValue -= .5f;
            }
            control.steer(steeringValue);
        } else if (binding.equals(RIGHTS)) {
            if (value) {
                steeringValue -= .5f;
            } else {
                steeringValue += .5f;
            }
            control.steer(steeringValue);
        } // Note that our fancy car actually goes backward.
        else if (binding.equals(UPS)) {
            if (value) {
                accelerationValue -= ACCELERATION_FORCE;
            } else {
                accelerationValue += ACCELERATION_FORCE;
            }
            control.accelerate(accelerationValue);
        } else if (binding.equals(DOWNS)) {
            if (value) {
                control.brake(BRAKE_FORCE);
            } else {
                control.brake(0f);
            }
        } else if (binding.equals(RESET)) {
            if (value) {
                System.out.println("Reset");
                control.setPhysicsLocation(Vector3f.ZERO);
                control.setPhysicsRotation(new Matrix3f());
                control.setLinearVelocity(Vector3f.ZERO);
                control.setAngularVelocity(Vector3f.ZERO);
                control.resetSuspension();
            }
        }
    }
}
