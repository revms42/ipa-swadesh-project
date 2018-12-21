package org.ajar.swadesh.model.ipa;

public enum Rounding implements Location<Rounding> {
    ROUNDED,
    UNROUNDED;

    @Override
    public double distanceTo(Rounding location) {
        return 0.5d * Math.abs(this.ordinal() - location.ordinal());
    }
}
