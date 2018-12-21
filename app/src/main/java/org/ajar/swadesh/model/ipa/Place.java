package org.ajar.swadesh.model.ipa;

public enum Place implements Location<Place> {
    BILABIAL(0.0d),
    LABIO_DENTAL(1.0d),
    LINGUO_LABIAL(1.5d),
    DENTAL(2.0d),
    ALVEOLAR(3.0d),
    POST_ALVEOLAR(4.0d),
    RETROFLEX(5.0d),
    PALATAL(6.0d),
    //LABIALIZED_PALATAL(5.5d),
    VELAR(7.0d),
    //VELARIZED_AVLVEOLAR(3.5d),
    //LABIALIZED_VELAR(6.5d),
    UVULAR(8.0d),
    PHARYNGEAL_EPIGLOTTAL(9.0d),
    GLOTTAL(10.0d);

    private final double position;

    Place(double position) {
        this.position = position;
    }

    @Override
    public double distanceTo(Place location) {
        return Math.abs(this.position - location.position);
    }
}
