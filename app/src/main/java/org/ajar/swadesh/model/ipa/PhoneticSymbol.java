package org.ajar.swadesh.model.ipa;

public interface PhoneticSymbol<P extends PhoneticSymbol<P>> extends Location<P> {

    int length();
    String description();
    boolean starts(String string);
}
