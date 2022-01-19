package trans.america.experiments.fancy;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.debug.Arrow;

public class DebugUtils {

    public static final int LENGTH = 10;

    public static void attachCoordinateAxes(Node node, AssetManager assetManager) {
        attachArrow(node, assetManager, Vector3f.UNIT_X.mult(LENGTH), ColorRGBA.Red);
        attachArrow(node, assetManager, Vector3f.UNIT_Y.mult(LENGTH), ColorRGBA.Green);
        attachArrow(node, assetManager, Vector3f.UNIT_Z.mult(LENGTH), ColorRGBA.Blue);
    }

    private static void attachArrow(Node node, AssetManager assetManager, Vector3f vector, ColorRGBA color) {
        final Geometry geometry = new Geometry("coordinate axis", new Arrow(vector));
        final Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.getAdditionalRenderState().setLineWidth(4);
        material.getAdditionalRenderState().setWireframe(true);
        material.setColor("Color", color);
        geometry.setMaterial(material);
        node.attachChild(geometry);
    }
}
