package getaway;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import getaway.map.World;

public class Getaway extends SimpleApplication {

    public static void main(String[] args) {
        final Getaway app = new Getaway();
        app.setShowSettings(false);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        setDisplayFps(true);
        setDisplayStatView(false);

        viewPort.setBackgroundColor(ColorRGBA.White);

        // Setup camera
        cam.setLocation(new Vector3f(0, 1, 0));
        cam.lookAt(new Vector3f(0, 0, 6f), Vector3f.UNIT_Y);
        flyCam.setEnabled(true);

        // Let there be light
        final DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.5f, -0.5f, -0.5f));
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

        //
        rootNode.attachChild(new World(assetManager));
        rootNode.attachChild(new Player(assetManager));
    }
}
