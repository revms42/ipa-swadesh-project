package org.ajar.swadesh.model.ipa;

public enum Voice implements Location<Voice> {
    VOICED,
    VOICELESS;

    @Override
    public double distanceTo(Voice location) {
        return 0.5d * Math.abs(this.ordinal() - location.ordinal());
    }
}
