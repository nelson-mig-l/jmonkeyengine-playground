package trans.america;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.shape.Box;

public class TileFactory {

    private static final Mesh CUBE_MESH = new Box(0.5f, 0.1f, 0.5f);

    private final AssetManager assetManager;

    public TileFactory(final AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Deprecated
    public Geometry createCube(final String name, final Vector3f position, final ColorRGBA color) {
        final Geometry geometry = new Geometry(name, CUBE_MESH);
        final Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", color);
        geometry.setMaterial(material);
        geometry.setLocalTranslation(position);
        return geometry;
    }

    public Geometry createOutOfBounds(final String name, final int x, int y) {
        final Vector3f position = new Vector3f(x, +0.5f, y);
        final Geometry geometry = new Geometry(name, new Box(0.5f, 0.5f, 0.5f));
        final Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.White);
        geometry.setMaterial(material);
        geometry.setLocalTranslation(position);
        return geometry;
    }

    public Geometry createGround(final String name, final int x, int y, ColorRGBA color) {
        final Vector3f position = new Vector3f(x, -0.5f, y);
        final Geometry geometry = new Geometry(name, new Box(0.5f, 0.5f, 0.5f));
        final Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", color);
        geometry.setMaterial(material);
        geometry.setLocalTranslation(position);

        final RigidBodyControl physicsControl = new RigidBodyControl(new BoxCollisionShape(new Vector3f(0.5f, 0.5f, 0.5f)), 0);
        geometry.addControl(physicsControl);

        return geometry;

    }

}
