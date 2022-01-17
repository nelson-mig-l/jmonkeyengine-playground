package trans.america.experiments.fancy.car;

import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;

public class PhysicalCar extends Node implements PhysicsCollisionListener {

    private final VehicleControl control;

    PhysicalCar(FancyCarDefinition carDefinition) {
        super(carDefinition.getName());

        final CollisionShape carHull = CollisionShapeFactory.createDynamicMeshShape(carDefinition.getChassis());

        //Create a vehicle control
        control = new VehicleControl(carHull, carDefinition.getMass());
        this.addControl(control);

        final SuspensionDefinition suspensionDefinition = carDefinition.getSuspensionConfiguration();
        //Setting default values for wheels
        control.setSuspensionCompression(suspensionDefinition.getCompression());
        control.setSuspensionDamping(suspensionDefinition.getDamping());
        control.setSuspensionStiffness(suspensionDefinition.getStiffness());
        control.setMaxSuspensionForce(suspensionDefinition.getMaxForce());

        for (WheelDefinition.Position position : WheelDefinition.Position.values()) {
            final WheelDefinition wheelDefinition = carDefinition.getWheel(position);
            control.addWheel(wheelDefinition.parent, wheelDefinition.connectionPoint,
                    carDefinition.getWheelDirection(), carDefinition.getWheelAxle(), 0.2f, wheelDefinition.radius, wheelDefinition.isFrontWheel);
            wheelDefinition.frictionSlip.ifPresent(value -> {
                System.out.println(position + " has friction " + value);
                control.getWheel(position.ordinal()).setFrictionSlip(value);
            });
        }

        final Node model = carDefinition.getModel();
        model.setShadowMode(RenderQueue.ShadowMode.Cast);
        this.attachChild(model);
    }

    @Override
    public void collision(PhysicsCollisionEvent event) {
        if (event.getNodeB().getName() != null && !event.getNodeB().getName().equals("Box")) {
            System.out.println("COLLISION!!!");
            System.out.println(event.getNodeA().getName() + " -> " + event.getNodeB().getName());
        }
    }

    public VehicleControl getControls() {
        return control;
    }

}
