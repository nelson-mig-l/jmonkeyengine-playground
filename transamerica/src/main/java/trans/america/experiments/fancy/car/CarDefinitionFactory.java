package trans.america.experiments.fancy.car;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import trans.america.experiments.fancy.car.FancyCarDefinition;
import trans.america.experiments.fancy.car.PhysicalCar;

public class CarDefinitionFactory {

    private final AssetManager assetManager;

    public CarDefinitionFactory(final AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public CarDefinition defaultFancyCar() {
        final Node model = (Node) assetManager.loadModel("Models/Ferrari/Car.scene");
        return new FancyCarDefinition(model);
    }

    public CarDefinition defaultDummyCar() {
        return new DummyCarDefinition(assetManager);
    }
}
