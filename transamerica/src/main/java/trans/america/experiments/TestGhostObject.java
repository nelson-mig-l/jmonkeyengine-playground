package trans.america.experiments;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author tim8dev [at] gmail [dot com]
 */
public class TestGhostObject extends SimpleApplication {

    private BulletAppState bulletAppState;
    private GhostControl ghostControl;

    public static void main(String[] args) {
        Application app = new TestGhostObject();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        bulletAppState.setDebugEnabled(true);

        // Mesh to be shared across several boxes.
        Box boxGeom = new Box(1f, 1f, 1f);
        // CollisionShape to be shared across several boxes.
        CollisionShape shape = new BoxCollisionShape(new Vector3f(1, 1, 1));

        Node physicsBox = PhysicsTestHelper.createPhysicsTestNode(assetManager, shape, 1);
        physicsBox.setName("box0");
        physicsBox.getControl(RigidBodyControl.class).setPhysicsLocation(new Vector3f(.6f, 4, .5f));
        rootNode.attachChild(physicsBox);
        getPhysicsSpace().add(physicsBox);

        Node physicsBox1 = PhysicsTestHelper.createPhysicsTestNode(assetManager, shape, 1);
        physicsBox1.setName("box1");
        final Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Red);
        physicsBox1.setMaterial(material);
        physicsBox1.getControl(RigidBodyControl.class).setPhysicsLocation(new Vector3f(0, 40, 0));
        rootNode.attachChild(physicsBox1);
        getPhysicsSpace().add(physicsBox1);

        Node physicsBox2 = PhysicsTestHelper.createPhysicsTestNode(assetManager, new BoxCollisionShape(new Vector3f(1, 1, 1)), 1);
        physicsBox2.setName("box0");
        physicsBox2.getControl(RigidBodyControl.class).setPhysicsLocation(new Vector3f(.5f, 80, -.8f));
        rootNode.attachChild(physicsBox2);
        getPhysicsSpace().add(physicsBox2);

        // the floor, does not move (mass=0)
        Node node = PhysicsTestHelper.createPhysicsTestNode(assetManager, new BoxCollisionShape(new Vector3f(100, 1, 100)), 0);
        node.setName("floor");
        node.getControl(RigidBodyControl.class).setPhysicsLocation(new Vector3f(0f, -6, 0f));
        rootNode.attachChild(node);
        getPhysicsSpace().add(node);

        initGhostObject();
    }

    private PhysicsSpace getPhysicsSpace(){
        return bulletAppState.getPhysicsSpace();
    }

    private void initGhostObject() {
        Vector3f halfExtents = new Vector3f(3, 4.2f, 1);
        ghostControl = new GhostControl(new BoxCollisionShape(halfExtents));
        Node node=new Node("Ghost Object");
        node.addControl(ghostControl);
        rootNode.attachChild(node);
        getPhysicsSpace().add(ghostControl);
    }

    @Override
    public void simpleUpdate(float tpf) {
        fpsText.setText("Overlapping objects: " + ghostControl.getOverlappingObjects().toString());
    }
}