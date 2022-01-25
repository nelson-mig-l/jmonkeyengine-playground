package trans.america.experiments.fancy;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.FogFilter;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import trans.america.experiments.PhysicsTestHelper;
import trans.america.experiments.fancy.car.*;

public class MyFancyCarExample extends SimpleApplication {

    private BulletAppState bulletAppState;
    private Node carNode;
    private MyCamera myCamera;


    PlayerCar player;
    PhysicalCar pc;

    public static void main(String[] args) {
        MyFancyCarExample app = new MyFancyCarExample();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        initializePhysics();


        cam.setFrustumFar(150f);
        flyCam.setMoveSpeed(10);
        myCamera = new MyCamera(cam);
        inputManager.addMapping("Cam", new KeyTrigger(KeyInput.KEY_C));
        inputManager.addListener(myCamera, "Cam");


        viewPort.setBackgroundColor(ColorRGBA.Cyan);
        final World world = new World(rootNode, getPhysicsSpace());
        PhysicsTestHelper.createPhysicsTestWorld(rootNode, assetManager, getPhysicsSpace());


        initializeFog();


        final CarDefinition carDefinition = new CarDefinitionFactory(assetManager).defaultCustomCar();
        carNode = carDefinition.getModel();
        final PhysicalCar physicalCar = new PhysicalCar(carDefinition);
        DebugUtils.attachCoordinateAxes(physicalCar, assetManager);
        player = new PlayerCar(physicalCar, inputManager);
        pc = physicalCar;
        world.addVehicle(physicalCar);
        getPhysicsSpace().addCollisionListener(physicalCar); // what about this?

        //buildGhostCar();
//        final Spatial model = assetManager.loadModel("car/low/chassis.fbx");
//        model.setLocalTranslation(3, -5, 3);
//        rootNode.attachChild(model);


        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.5f, -1f, -0.3f).normalizeLocal());
        rootNode.addLight(dl);
    }

    private PhysicsSpace getPhysicsSpace() {
        return bulletAppState.getPhysicsSpace();
    }

    private void initializeFog() {
        final FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        viewPort.addProcessor(fpp);
        final FogFilter fogFilter = new FogFilter();
        fogFilter.setFogDistance(155);
        fogFilter.setFogDensity(2.0f);
        fpp.addFilter(fogFilter);
    }

    private void initializePhysics() {
        bulletAppState = new BulletAppState();
        bulletAppState.setDebugEnabled(true);
        bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
        stateManager.attach(bulletAppState);
    }

    @Override
    public void simpleUpdate(float tpf) {
        myCamera.updateCamera(carNode);
    }
}

