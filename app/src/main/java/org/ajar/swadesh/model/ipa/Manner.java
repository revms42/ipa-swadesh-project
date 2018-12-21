package org.ajar.swadesh.model.ipa;

public enum Manner implements Location<Manner> {
    NASAL,
    STOP,
    SIBILANT_AFFRICATIVE,
    NON_SIBILANT_AFFRICATIVE,
    SIBILANT_FRICATIVE,
    NON_SIBILANT_FRICATIVE,
    APPROXIMATE,
    TAP_FLAP,
    TRILL,
    LATERAL_AFFRICATIVE,
    LATERAL_FRICATIVE,
    LATERAL_APPROXIMATE,
    LATERAL_TAP_FLAP,
    PLOSIVE, //Not Pulmonic.
    IMPLOSIVE; //Not Pulmonic.

    @Override
    public double distanceTo(Manner location) {
        return Math.abs(this.ordinal() - location.ordinal());
    }
}
