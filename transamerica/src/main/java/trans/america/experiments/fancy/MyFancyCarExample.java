package trans.america.experiments.fancy;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import trans.america.experiments.PhysicsTestHelper;
import trans.america.experiments.fancy.car.*;

public class MyFancyCarExample extends SimpleApplication {

    private BulletAppState bulletAppState;
    private Node carNode;
    private MyCamera myCamera;

    public static void main(String[] args) {
        MyFancyCarExample app = new MyFancyCarExample();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        bulletAppState.setDebugEnabled(true);
        bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
        stateManager.attach(bulletAppState);


        cam.setFrustumFar(150f);
        flyCam.setMoveSpeed(10);
        myCamera = new MyCamera(cam);
        inputManager.addMapping("Cam", new KeyTrigger(KeyInput.KEY_C));
        inputManager.addListener(myCamera, "Cam");


        final World world = new World(rootNode, getPhysicsSpace());
        PhysicsTestHelper.createPhysicsTestWorld(rootNode, assetManager, getPhysicsSpace());


        final CarDefinition carDefinition = new CarDefinitionFactory(assetManager).defaultFancyCar();
        carNode = carDefinition.getModel();
        final PhysicalCar physicalCar = new PhysicalCar(carDefinition);
        DebugUtils.attachCoordinateAxes(physicalCar, assetManager);
        final PlayerCar player = new PlayerCar(physicalCar, inputManager);
        world.addVehicle(physicalCar);
        getPhysicsSpace().addCollisionListener(physicalCar);

        //buildGhostCar();

        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.5f, -1f, -0.3f).normalizeLocal());
        rootNode.addLight(dl);
    }

    private PhysicsSpace getPhysicsSpace() {
        return bulletAppState.getPhysicsSpace();
    }


//
//    private void buildGhostCar() {
//        final Spatial model = assetManager.loadModel("car/car.obj");
//        model.setLocalTranslation(3, -5, 3);
//        rootNode.attachChild(model);
//    }

    @Override
    public void simpleUpdate(float tpf) {
        //cam.lookAt(carNode.getWorldTranslation(), Vector3f.UNIT_Y);
//        cam.setLocation(carNode.localToWorld(new Vector3f(0, 5 /* units above car*/, 10 /* units behind car*/), null));
//        cam.lookAt(this.carNode.getWorldTranslation(), Vector3f.UNIT_Y);
        myCamera.updateCamera(carNode);
    }
}

