package org.ajar.swadesh.model.ipa;

public enum Coarticulation implements Location<Coarticulation> {
    NONE,
    LABIALIZED,
    PALATALIZED,
    VELARIZED,
    PHARYNGEALIZED,
    ASPIRATED,
    MURMURED,
    NASALIZED;

    @Override
    public double distanceTo(Coarticulation location) {
        if(this == NONE || location == NONE) return 1.0d;

        return Math.abs(this.ordinal() - location.ordinal());
    }
}
