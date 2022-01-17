package trans.america;

import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Player extends Geometry {

    private static final float SIZE = 0.25f;
    private static final float MASS = 100f;

    private final RigidBodyControl control;

    Player(Material material) {
        super("Player", new Box(SIZE, SIZE, SIZE));
        this.setMaterial(material);
        this.setLocalTranslation(0, SIZE+0.01f, 0);
        this.control = new RigidBodyControl(new BoxCollisionShape(new Vector3f(SIZE, SIZE, SIZE)), MASS);
        this.addControl(control);
    }

    public void go() {
        final Vector3f v = this.getLocalRotation().getRotationColumn(2).mult(800);
        control.applyForce(v, new Vector3f(0, -SIZE, 0));
    }
}
