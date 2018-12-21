package org.ajar.swadesh.model.ipa;

public enum Openness implements Location<Openness> {
    CLOSE,
    NEAR_CLOSE,
    CLOSE_MID,
    MID,
    OPEN_MID,
    NEAR_OPEN,
    OPEN;

    @Override
    public double distanceTo(Openness location) {
        return Math.abs(this.ordinal() - location.ordinal());
    }
}
