package trans.america.experiments.fancy.car;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import java.util.HashMap;
import java.util.Map;

public class CustomCarDefinition implements CarDefinition {

    private static final Vector3f WHEEL_DIRECTION = new Vector3f(0, -1, 0);
    private static final Vector3f WHEEL_AXLE = new Vector3f(-1, 0, 0);

    private final Map<WheelDefinition.Position, WheelDefinition> wheels = new HashMap<>();
    private Node model = new Node();
    private Spatial chassis;

    public CustomCarDefinition(AssetManager assetManager) {
        chassis = assetManager.loadModel("car/low/chassis4.fbx");
        chassis.setLocalScale(0.01f);
        model.attachChild(chassis);

        final float restLength = 0.3f;
        final float xOff = 1.22894f / 1.5f;
        final float yOff = +0.305455f*1.5f;
        final float zOff = 0.680042f;
        final Vector3f[] connectionPoint = {
                new Vector3f(-xOff, yOff, zOff*2.6f),
                new Vector3f(xOff, yOff, zOff*2.6f),
                new Vector3f(-xOff, yOff, -zOff),
                new Vector3f(xOff, yOff, -zOff)
        };


        final boolean[] isFrontWheel = {false, false, true, true};


        final Spatial wheel = assetManager.loadModel("car/low/wheel-fr-2.fbx");
        wheel.setLocalScale(0.01f);
        final Geometry wheelGeometry = findGeom(wheel, "whell-submesh");
        final BoundingBox box = (BoundingBox) wheelGeometry.getModelBound();
        final Vector3f center = box.getCenter().mult(-1);
        wheelGeometry.center();
        for (WheelDefinition.Position position : WheelDefinition.Position.values()) {
            final int index = position.ordinal();
            final Node parent = new Node(position.name() + "_node");
            final Spatial clone = wheel.clone();
            if (index % 2 == 0) {
                clone.rotate(0, -FastMath.HALF_PI, 0);
            } else {
                clone.rotate(0, FastMath.HALF_PI, 0);
            }
            parent.attachChild(clone);
            wheels.put(position, new WheelDefinition(
                    parent,
                    connectionPoint[index],
                    /*radius*/ box.getYExtent(),
                    restLength,
                    isFrontWheel[index]));
            model.attachChild(parent);
        }

    }

    @Override
    public WheelDefinition getWheel(WheelDefinition.Position position) {
        return wheels.get(position);
    }

    @Override
    public CollisionShape getChassis() {
        final CollisionShape shape = CollisionShapeFactory.createDynamicMeshShape(chassis);
        return shape;
    }

    @Override
    public Node getModel() {
        return model;
    }

    @Override
    public Vector3f getWheelDirection() {
        return WHEEL_DIRECTION;
    }

    @Override
    public Vector3f getWheelAxle() {
        return WHEEL_AXLE;
    }

    @Override
    public SuspensionDefinition getSuspensionConfiguration() {
        return new CustomSuspensionDefinition(60.0f, 0.3f, 0.4f, 10000.0f);
    }

    @Override
    public float getMass() {
        return 400;
    }

    @Override
    public String getName() {
        return "Dummy Car";
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
