package trans.america.experiments.fancy.car;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

import java.util.Optional;

public class WheelDefinition {

    public enum Position {
        FRONT_RIGHT, FRONT_LEFT, BACK_RIGHT, BACK_LEFT
    }

    final Spatial parent;
    final Vector3f connectionPoint;
    final float radius;
    final boolean isFrontWheel;
    final Optional<Float> frictionSlip;

    public WheelDefinition(Spatial parent, Vector3f connectionPoint, float radius, boolean isFrontWheel) {
        this(parent, connectionPoint, radius, isFrontWheel, null);
    }

    public WheelDefinition(Spatial parent, Vector3f connectionPoint, float radius, boolean isFrontWheel, Float frictionSlip) {
        this.parent = parent;
        this.connectionPoint = connectionPoint;
        this.radius = radius;
        this.isFrontWheel = isFrontWheel;
        this.frictionSlip = Optional.ofNullable(frictionSlip);
    }
}