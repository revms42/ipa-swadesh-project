package org.ajar.swadesh.model.ipa;

public enum RColor implements Location<RColor> {
    RHOTIC,
    NASALIZED,
    NOT_RHOTIC;

    @Override
    public double distanceTo(RColor location) {
        return Math.abs(this.ordinal() - location.ordinal());
    }
}
