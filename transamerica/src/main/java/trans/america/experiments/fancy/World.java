package trans.america.experiments.fancy;

import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.scene.Node;
import trans.america.experiments.fancy.car.PhysicalCar;

public class World {

    private final Node rootNode;
    private final PhysicsSpace physicsSpace;


    public World(Node rootNode, PhysicsSpace physicsSpace) {
        this.rootNode = rootNode;
        this.physicsSpace = physicsSpace;
    }

    public void addVehicle(PhysicalCar fancyCar) {
        rootNode.attachChild(fancyCar);
        final VehicleControl controls = fancyCar.getControl();
        physicsSpace.add(controls);
    }
}
