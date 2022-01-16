package trans.america;

import com.jme3.input.FlyByCamera;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl;

public class CameraManager {

    private final FlyByCamera flyByCam;
    private final Camera cam;

    public CameraManager(FlyByCamera flyByCam, Camera cam) {
        this.flyByCam = flyByCam;
        this.cam = cam;
    }

    public void setDefault(final Node target) {
        // Disable the default flyby cam
        flyByCam.setEnabled(false);
        //create the camera Node
        final CameraNode camNode = new CameraNode("Camera Node", cam);
        //This mode means that camera copies the movements of the target:
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        //Attach the camNode to the target:
        target.attachChild(camNode);
        //Move camNode, e.g. behind and above the target:
        camNode.setLocalTranslation(new Vector3f(0, 5, -5));
        //Rotate the camNode to look at the target:
        camNode.lookAt(target.getLocalTranslation(), Vector3f.UNIT_Y);
    }

}
