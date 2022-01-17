package trans.america.experiments.fancy.car;

import com.jme3.math.FastMath;

public class DefaultSuspensionDefinition implements SuspensionDefinition {

    private final float stiffness; // 200=f1 car
    private final float compression; // (lower than damp!)
    private final float damping;
    private final float maxForce;

    public DefaultSuspensionDefinition(float stiffness, float compressionValue, float dampingValue, float maxForce) {
        this.stiffness = stiffness;
        this.compression = compressionValue * 2.0f * FastMath.sqrt(stiffness);
        this.damping = dampingValue * 2.0f * FastMath.sqrt(stiffness);
        this.maxForce = maxForce;
    }

    @Override
    public float getStiffness() {
        return stiffness;
    }

    @Override
    public float getCompression() {
        return compression;
    }

    @Override
    public float getDamping() {
        return damping;
    }

    @Override
    public float getMaxForce() {
        return maxForce;
    }
}
