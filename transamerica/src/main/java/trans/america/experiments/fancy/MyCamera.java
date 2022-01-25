package trans.america.experiments.fancy;

import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

public class MyCamera implements ActionListener {
    private static final Vector3f CAM_RELATIVE_TO_CAR = new Vector3f(0, 3, 10);

    private final Camera cam;

    private boolean inCar = true;

    public MyCamera(Camera cam) {
        this.cam = cam;
    }

    public void switchCamera() {
        inCar = !inCar;
        if (!inCar) {
            cam.setLocation(new Vector3f(0, 10, 0));
        }
    }

    public void updateCamera(Node target) {
        if (inCar) {
            cam.setLocation(target.localToWorld(CAM_RELATIVE_TO_CAR, null));
        }
        cam.lookAt(target.getWorldTranslation(), Vector3f.UNIT_Y);
    }

    @Override
    public void onAction(String s, boolean b, float v) {
        if (s.equals("Cam")) {
            if (b) {
                switchCamera();
            }
        }
    }
}
