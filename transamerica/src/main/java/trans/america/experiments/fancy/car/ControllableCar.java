package trans.america.experiments.fancy.car;

import com.jme3.input.controls.ActionListener;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

public class ControllableCar extends PhysicalCar implements ActionListener {

    public static final String LEFTS = "Lefts";
    public static final String RIGHTS = "Rights";
    public static final String UPS = "Ups";
    public static final String DOWNS = "Downs";
    public static final String RESET = "Reset";

    private float steeringValue = 0;
    private float accelerationValue = 0;

    public ControllableCar(Node model) {
        super(new FancyCarDefinition(model));
    }

    @Override
    public void onAction(String binding, boolean value, float tpf) {
        if (binding.equals(LEFTS)) {
            if (value) {
                steeringValue += .5f;
            } else {
                steeringValue -= .5f;
            }
            getControls().steer(steeringValue);
        } else if (binding.equals(RIGHTS)) {
            if (value) {
                steeringValue -= .5f;
            } else {
                steeringValue += .5f;
            }
            getControls().steer(steeringValue);
        } // Note that our fancy car actually goes backward.
        else if (binding.equals(UPS)) {
            if (value) {
                accelerationValue -= 800;
            } else {
                accelerationValue += 800;
            }
            getControls().accelerate(accelerationValue);
        } else if (binding.equals(DOWNS)) {
            if (value) {
                getControls().brake(40f);
            } else {
                getControls().brake(0f);
            }
        } else if (binding.equals(RESET)) {
            if (value) {
                System.out.println("Reset");
                getControls().setPhysicsLocation(Vector3f.ZERO);
                getControls().setPhysicsRotation(new Matrix3f());
                getControls().setLinearVelocity(Vector3f.ZERO);
                getControls().setAngularVelocity(Vector3f.ZERO);
                getControls().resetSuspension();
            }
        }
    }
}
