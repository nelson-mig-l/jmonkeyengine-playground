package trans.america;

import com.jme3.app.SimpleApplication;
import com.jme3.input.controls.AnalogListener;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;

public class App extends SimpleApplication implements AnalogListener {

    private Node player;

    private Gui gui;

    private MapRender mapRender;

    public static void main(String[] args) {
        AppSettings settings = new AppSettings(true);
        settings.setSamples(2); // anti-aliasing

        final App app = new App();
        app.setSettings(settings);
        app.setShowSettings(false);
        app.setDisplayStatView(false);
        //app.setDisplayFps(false);
        app.start();
    }

    @Override
    public void simpleInitApp() {


        final Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Red);
        player = new Player(material);
        rootNode.attachChild(player);

        new CameraManager(flyCam, cam).setDefault(player);
        final Controls controls = new Controls(inputManager, this);
        gui = new Gui(assetManager, guiNode);

        final TileUtils tileUtils = new TileUtils(assetManager);
        final Map map = new Map(9);
        mapRender = new MapRender(map, tileUtils);
    }

    @Override
    public void simpleUpdate(float tpf) {
        gui.update(player);
        mapRender.render(rootNode,
                (int)Math.rint(player.getLocalTranslation().x),
                (int)Math.rint(player.getLocalTranslation().z)
        );
    }

    @Override
    public void onAnalog(final String name, float value, float tpf) {
        if (name.equals(Controls.PLAYER_FORWARD)) {
            final Vector3f mult = player.getLocalRotation().getRotationColumn(2).mult(5 * tpf);
            player.move(mult);
        }
        if (name.equals(Controls.PLAYER_BACKWARD)) {
            final Vector3f mult = player.getLocalRotation().getRotationColumn(2).mult(-5 * tpf);
            player.move(mult);
        }
        if (name.equals(Controls.PLAYER_RIGHT)) {
            player.rotate(0f, -FastMathUtils.toRadians(45) * tpf, 0f);
        }
        if (name.equals(Controls.PLAYER_LEFT)) {
            player.rotate(0, FastMathUtils.toRadians(45) * tpf, 0);
        }
    }
}
