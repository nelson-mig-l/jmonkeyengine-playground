package trans.america.experiments.fancy.car;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

public interface CarDefinition {

    WheelDefinition getWheel(WheelDefinition.Position position);

    CollisionShape getChassis();

    Node getModel();

    Vector3f getWheelDirection();

    Vector3f getWheelAxle();

    SuspensionDefinition getSuspensionConfiguration();

    float getMass();

    String getName();
}
