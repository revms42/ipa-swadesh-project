package org.ajar.swadesh.model.ipa;

public interface Location<A extends Location<A>> {

    double distanceTo(A location);
}
