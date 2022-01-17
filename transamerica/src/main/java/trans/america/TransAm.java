package trans.america;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.controls.AnalogListener;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;

public class TransAm extends SimpleApplication implements AnalogListener {

    private Player player;
    private Node playerNode;
    //private Vehicle vehicle;

    private Gui gui;

    private MapRender mapRender;
    private PhysicsSpace physicsSpace;


    public static void main(String[] args) {
        AppSettings settings = new AppSettings(true);
        settings.setSamples(2); // anti-aliasing

        final TransAm transAm = new TransAm();
        transAm.setSettings(settings);
        transAm.setShowSettings(false);
        transAm.setDisplayStatView(false);
        //app.setDisplayFps(false);
        transAm.start();
    }

    @Override
    public void simpleInitApp() {
        physicsSpace = initializePhysics();

        createPlayer();

        final Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Pink);
        //vehicle = new Vehicle(material, rootNode, physicsSpace);
        //rootNode.attachChild(vehicle);

        // https://hub.jmonkeyengine.org/t/solved-camera-that-follow-a-object/17484/11
        new CameraManager(flyCam, cam).setDefault(this.playerNode);
        final Controls controls = new Controls(inputManager, this);
        gui = new Gui(assetManager, guiNode);

        final TileFactory tileFactory = new TileFactory(assetManager);
        final Map map = new Map(9);
        mapRender = new MapRender(map, tileFactory);
    }

    @Override
    public void simpleUpdate(float tpf) {
        gui.update(playerNode);
        mapRender.render(rootNode, physicsSpace,
                (int)Math.rint(player.getLocalTranslation().x),
                (int)Math.rint(player.getLocalTranslation().z)
        );
    }

    @Override
    public void onAnalog(final String name, float value, float tpf) {
        if (name.equals(Controls.PLAYER_FORWARD)) {
            final Vector3f mult = player.getLocalRotation().getRotationColumn(2).mult(5 * tpf);
            //player.move(mult);
            player.go();
        }
        if (name.equals(Controls.PLAYER_BACKWARD)) {
            final Vector3f mult = player.getLocalRotation().getRotationColumn(2).mult(-5 * tpf);
            player.move(mult);
        }
        if (name.equals(Controls.PLAYER_RIGHT)) {
            player.rotate(0f, -FastMathUtils.toRadians(90) * tpf, 0f);
        }
        if (name.equals(Controls.PLAYER_LEFT)) {
            player.rotate(0, FastMathUtils.toRadians(90) * tpf, 0);
        }
    }

    private PhysicsSpace initializePhysics() {
        BulletAppState bulletAppState = new BulletAppState();
        bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
        stateManager.attach(bulletAppState);
        return bulletAppState.getPhysicsSpace();
    }

    private void createPlayer() {
        final Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Red);
        player = new Player(material);
        physicsSpace.add(player);
        this.playerNode = new Node();
        this.playerNode.attachChild(player);
        rootNode.attachChild(this.playerNode);
    }
}
