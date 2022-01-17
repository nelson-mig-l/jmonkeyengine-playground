package trans.america;

import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;


public class Vehicle extends Node {

    float stiffness = 120.0f;//200=f1 car
    float compValue = 0.2f; //(lower than damp!)
    float dampValue = 0.3f;
    public static final int MASS = 400;
    public static final float SIZE = 0.25f;

    private float accelerationValue = 0;


    private final VehicleControl vehicleControl;

    Vehicle(Material material, Node rootNode, PhysicsSpace physicsSpace) {
        Geometry chassis = new Geometry("Chassis", new Box(SIZE, SIZE, SIZE));
        chassis.setMaterial(material);
        chassis.setLocalTranslation(0, SIZE, 0);
        rootNode.attachChild(chassis);

        CollisionShape carHull = CollisionShapeFactory.createBoxShape(chassis);
        vehicleControl = new VehicleControl(carHull, MASS);
        this.addControl(vehicleControl);

        //Setting default values for wheels
        vehicleControl.setSuspensionCompression(compValue * 2.0f * FastMath.sqrt(stiffness));
        vehicleControl.setSuspensionDamping(dampValue * 2.0f * FastMath.sqrt(stiffness));
        vehicleControl.setSuspensionStiffness(stiffness);
        vehicleControl.setMaxSuspensionForce(10000);

        physicsSpace.add(vehicleControl);
    }

    public void go(final float delta) {
        vehicleControl.accelerate(delta);
    }


}
