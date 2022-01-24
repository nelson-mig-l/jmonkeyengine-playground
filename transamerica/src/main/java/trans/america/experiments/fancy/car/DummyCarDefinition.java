package trans.america.experiments.fancy.car;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Cylinder;

import java.util.HashMap;
import java.util.Map;

public class DummyCarDefinition implements CarDefinition {

    private static final Vector3f WHEEL_DIRECTION = new Vector3f(0, -1, 0);
    private static final Vector3f WHEEL_AXLE = new Vector3f(-1, 0, 0);

    private final Map<WheelDefinition.Position, WheelDefinition> wheels = new HashMap<>();
    private final Node model = new Node();

    public DummyCarDefinition(AssetManager assetManager) {
        final Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.getAdditionalRenderState().setWireframe(true);
        material.setColor("Color", ColorRGBA.Red);

        final float radius = 0.5f;
        final float restLength = 0.3f;
        final float yOff = 0.5f;
        final float xOff = 1f;
        final float zOff = 2f;
        final Vector3f[] connectionPoint = {
                new Vector3f(-xOff, yOff, zOff),
                new Vector3f(xOff, yOff, zOff),
                new Vector3f(-xOff, yOff, -zOff),
                new Vector3f(xOff, yOff, -zOff)
        };
        final boolean[] isFrontWheel = {false, false, true, true};

        final Cylinder wheelMesh = new Cylinder(16, 16, radius, radius * 0.6f, true);
        for (WheelDefinition.Position position : WheelDefinition.Position.values()) {
            final int index = position.ordinal();
            final Node parent = new Node(position.name() + "_node");
            final Geometry geo = new Geometry(position.name(), wheelMesh);
            parent.attachChild(geo);
            geo.rotate(0, FastMath.HALF_PI, 0);
            geo.setMaterial(material);
            wheels.put(position, new WheelDefinition(parent, connectionPoint[index], radius, restLength, isFrontWheel[index]));
            model.attachChild(parent);
        }

    }

    @Override
    public WheelDefinition getWheel(WheelDefinition.Position position) {
        return wheels.get(position);
    }

    @Override
    public CollisionShape getChassis() {
        final CompoundCollisionShape chassis = new CompoundCollisionShape();
        final BoxCollisionShape box = new BoxCollisionShape(new Vector3f(1.2f, 0.5f, 2.4f));
        chassis.addChildShape(box, new Vector3f(0, 1, 0));
        return chassis;
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
}
