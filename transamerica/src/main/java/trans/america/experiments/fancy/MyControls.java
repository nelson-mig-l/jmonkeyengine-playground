//package trans.america.experiments.fancy;
//
//import com.jme3.input.InputManager;
//import com.jme3.input.KeyInput;
//import com.jme3.input.controls.ActionListener;
//import com.jme3.input.controls.KeyTrigger;
//
//public class MyControls {
//
//    public static final String LEFTS = "Lefts";
//    public static final String RIGHTS = "Rights";
//    public static final String UPS = "Ups";
//    public static final String DOWNS = "Downs";
//    public static final String RESET = "Reset";
//
//    MyControls(InputManager inputManager, ActionListener actionListener) {
//        inputManager.addMapping(LEFTS, new KeyTrigger(KeyInput.KEY_H));
//        inputManager.addMapping(RIGHTS, new KeyTrigger(KeyInput.KEY_K));
//        inputManager.addMapping(UPS, new KeyTrigger(KeyInput.KEY_U));
//        inputManager.addMapping(DOWNS, new KeyTrigger(KeyInput.KEY_J));
//        inputManager.addMapping(RESET, new KeyTrigger(KeyInput.KEY_RETURN));
//        inputManager.addListener(actionListener, LEFTS);
//        inputManager.addListener(actionListener, RIGHTS);
//        inputManager.addListener(actionListener, UPS);
//        inputManager.addListener(actionListener, DOWNS);
//        inputManager.addListener(actionListener, RESET);
//    }
//}
