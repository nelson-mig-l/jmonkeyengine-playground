package trans.america.experiments.fancy.car;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.scene.Node;

public class PlayerCar extends ControllableCar {

    public PlayerCar(Node model, InputManager inputManager) {
        super(model);
        inputManager.addMapping(LEFTS, new KeyTrigger(KeyInput.KEY_H));
        inputManager.addMapping(RIGHTS, new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping(UPS, new KeyTrigger(KeyInput.KEY_U));
        inputManager.addMapping(DOWNS, new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping(RESET, new KeyTrigger(KeyInput.KEY_RETURN));
        inputManager.addListener(this, LEFTS);
        inputManager.addListener(this, RIGHTS);
        inputManager.addListener(this, UPS);
        inputManager.addListener(this, DOWNS);
        inputManager.addListener(this, RESET);
    }

}
