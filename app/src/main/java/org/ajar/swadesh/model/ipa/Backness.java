package org.ajar.swadesh.model.ipa;

public enum Backness implements Location<Backness> {
    FRONT,
    CENTRAL,
    BACK;

    @Override
    public double distanceTo(Backness location) {
        return Math.abs(this.ordinal() - location.ordinal());
    }
}
