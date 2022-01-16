package trans.america;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

public class Gui {

    private final Node guiNode;

    private BitmapText positionText;


    public Gui(final AssetManager assetManager, final Node guiNode) {
        this.guiNode = guiNode;

        final BitmapFont guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        //guiFont = assetManager.loadFont("fonts/AcmeFont.fnt");
        positionText = new BitmapText(guiFont);
        positionText.setSize(guiFont.getCharSet().getRenderedSize());
        positionText.setColor(ColorRGBA.Yellow);
        positionText.move(
                200, // X
                positionText.getLineHeight(), // Y
                0); // Z (depth layer)
        guiNode.attachChild(positionText);
    }

    public void update(final Node player) {
        positionText.setText("Position: "
                + Math.rint(player.getLocalTranslation().x) + ", "
                + Math.rint(player.getLocalTranslation().z));
    }



}
