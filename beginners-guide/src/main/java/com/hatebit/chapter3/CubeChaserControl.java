package com.hatebit.chapter3;

import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;

public class CubeChaserControl extends AbstractControl {

    private static final Ray RAY = new Ray();

    private final Camera camera;
    private final Node rootNode;

    public CubeChaserControl(Camera camera, Node rootNode) {
        this.camera = camera;
        this.rootNode = rootNode;
    }

    @Override
    protected void controlUpdate(final float tpf) {
        final CollisionResults results = new CollisionResults();
        RAY.setOrigin(camera.getLocation());
        RAY.setDirection(camera.getDirection());
        rootNode.collideWith(RAY, results);
        if (results.size() > 0) {
            final Geometry target = results.getClosestCollision().getGeometry();
            if (target.equals(spatial)) {
                final float distance = camera.getLocation().distance(spatial.getLocalTranslation());
                if (distance < 10) {
                    spatial.move(camera.getDirection());
                }
            }
        }
        spatial.rotate(tpf, tpf, tpf);
    }

    @Override
    protected void controlRender(final RenderManager renderManager, final ViewPort viewPort) {

    }

}
