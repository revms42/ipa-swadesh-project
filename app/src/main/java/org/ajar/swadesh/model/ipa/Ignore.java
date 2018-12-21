package org.ajar.swadesh.model.ipa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Ignore implements PhoneticSymbol<Ignore> {

    //Tones
    EXTRA_HIGH_TONE("˥"),
    HIGH_TONE("˦"),
    MID_TONE("˧"),
    LOW_TONE("˨"),
    EXTRA_LOW_TONE("˩"),
    //Non-Pulomic Articulation
    PRIMARY_STRESS("ˈ"),
    SECONDARY_STRESS("̩"),
    COART_BAR("͡"),
    LONG("ː"),
    SPACE(" "),
    BAR("-");

    private final static List<Ignore> searchList = new ArrayList<>();
    private final String symbol;

    Ignore(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public int length() {
        return symbol.length();
    }

    @Override
    public String description() {
        return toString();
    }

    @Override
    public boolean starts(String string) {
        return string.startsWith(symbol);
    }

    @Override
    public double distanceTo(Ignore location) {
        return Math.abs(this.ordinal() - location.ordinal());
    }

    public static List<Ignore> getSearchList() {
        if(searchList.size() == 0) {
            searchList.addAll(Arrays.asList(Ignore.values()));
        }

        return searchList;
    }

    public static String scrub(String s) {
        for(Ignore i : Ignore.values()) {
            s = s.replaceAll(i.symbol, "");
        }

        return s;
    }
}
