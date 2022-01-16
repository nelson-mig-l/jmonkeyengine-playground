package trans.america;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.KeyTrigger;

public class Controls {

    public static final String PLAYER_FORWARD = "moveForward";
    public static final String PLAYER_BACKWARD = "moveBackward";
    public static final String PLAYER_RIGHT = "rotateRight";
    public static final String PLAYER_LEFT = "rotateLeft";

    public Controls(InputManager inputManager, InputListener inputListener) {
        inputManager.addMapping(PLAYER_FORWARD,
                new KeyTrigger(KeyInput.KEY_UP),
                new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping(PLAYER_BACKWARD,
                new KeyTrigger(KeyInput.KEY_DOWN),
                new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping(PLAYER_RIGHT,
                new KeyTrigger(KeyInput.KEY_RIGHT),
                new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping(PLAYER_LEFT,
                new KeyTrigger(KeyInput.KEY_LEFT),
                new KeyTrigger(KeyInput.KEY_A));

        inputManager.addListener(inputListener,
                PLAYER_FORWARD,
                PLAYER_BACKWARD,
                PLAYER_RIGHT,
                PLAYER_LEFT);
    }



}
