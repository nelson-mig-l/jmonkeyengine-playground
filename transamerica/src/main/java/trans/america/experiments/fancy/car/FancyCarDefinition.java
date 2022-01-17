package trans.america.experiments.fancy.car;

import com.jme3.bounding.BoundingBox;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import java.util.HashMap;
import java.util.Map;

public class FancyCarDefinition implements CarDefinition {

    // Create four wheels and add them at their locations.
    // Note that our fancy car actually goes backward.
    private Vector3f WHEEL_DIRECTION = new Vector3f(0, -1, 0);
    private Vector3f WHEEL_AXLE = new Vector3f(-1, 0, 0);





    private final Node model;
    private final Geometry chassis;
    private final Map<WheelDefinition.Position, WheelDefinition> wheels = new HashMap<>();

    public FancyCarDefinition(final Node model) {
        this.model = model;
        this.chassis = findGeom(model, "Car");

        final Geometry wheelFrontRight = findGeom(model, "WheelFrontRight");
        wheelFrontRight.center();
        BoundingBox box = (BoundingBox) wheelFrontRight.getModelBound();

        float wheelRadius = box.getYExtent();
        float backWheelHeight = (wheelRadius * 1.7f) - 1f;
        float frontWheelHeight = (wheelRadius * 1.9f) - 1f;

        Vector3f connectionPoint = box.getCenter().add(0, -frontWheelHeight, 0);
        wheels.put(
                WheelDefinition.Position.FRONT_RIGHT,
                new WheelDefinition(wheelFrontRight.getParent(), connectionPoint, wheelRadius, true)
        );

        final Geometry wheelFrontLeft = findGeom(model, "WheelFrontLeft");
        wheelFrontLeft.center();
        box = (BoundingBox) wheelFrontLeft.getModelBound();
        connectionPoint = box.getCenter().add(0, -frontWheelHeight, 0);
        wheels.put(
                WheelDefinition.Position.FRONT_LEFT,
                new WheelDefinition(wheelFrontLeft.getParent(), connectionPoint, wheelRadius, true)
        );

        final Geometry wheelBackRight = findGeom(model, "WheelBackRight");
        wheelBackRight.center();
        box = (BoundingBox) wheelBackRight.getModelBound();
        connectionPoint = box.getCenter().add(0, -backWheelHeight, 0);
        wheels.put(
                WheelDefinition.Position.BACK_RIGHT,
                new WheelDefinition(wheelBackRight.getParent(), connectionPoint, wheelRadius, false, 4f)
        );

        final Geometry wheelBackLeft = findGeom(model, "WheelBackLeft");
        wheelBackLeft.center();
        box = (BoundingBox) wheelBackLeft.getModelBound();
        connectionPoint = box.getCenter().add(0, -backWheelHeight, 0);
        wheels.put(
                WheelDefinition.Position.BACK_LEFT,
                new WheelDefinition(wheelBackLeft.getParent(), connectionPoint, wheelRadius, false, 4f)
        );
    }

    public WheelDefinition getWheel(final WheelDefinition.Position position) {
        return wheels.get(position);
    }

    public Geometry getChassis() {
        return chassis;
    }

    public Node getModel() {
        return model;
    }

    public Vector3f getWheelDirection() {
        return WHEEL_DIRECTION;
    }

    public Vector3f getWheelAxle() {
        return WHEEL_AXLE;
    }

    public SuspensionDefinition getSuspensionConfiguration() {
        return new FancySuspensionDefinition();
    }

    public float getMass() {
        return 400;
    }

    public String getName() {
        return "Fancy Car";
    }

    private Geometry findGeom(Spatial spatial, String name) {
        if (spatial instanceof Node) {
            Node node = (Node) spatial;
            for (int i = 0; i < node.getQuantity(); i++) {
                Spatial child = node.getChild(i);
                Geometry result = findGeom(child, name);
                if (result != null) {
                    return result;
                }
            }
        } else if (spatial instanceof Geometry) {
            if (spatial.getName().startsWith(name)) {
                return (Geometry) spatial;
            }
        }
        return null;
    }

}
