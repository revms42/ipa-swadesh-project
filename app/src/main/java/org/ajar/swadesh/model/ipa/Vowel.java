package org.ajar.swadesh.model.ipa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.ajar.swadesh.model.ipa.Backness.*;
import static org.ajar.swadesh.model.ipa.Openness.*;
import static org.ajar.swadesh.model.ipa.Rounding.*;
import static org.ajar.swadesh.model.ipa.RColor.*;

public enum Vowel implements PhoneticSymbol<Vowel> {
    i("i", CLOSE, FRONT, UNROUNDED),
    ĩ("ĩ", CLOSE, FRONT, UNROUNDED, NASALIZED),
    y("y", CLOSE, FRONT, ROUNDED),
    ɨ("ɨ", CLOSE, CENTRAL, UNROUNDED),
    ʉ("ʉ", CLOSE, CENTRAL, ROUNDED),
    ɯ("ɯ", CLOSE, BACK, UNROUNDED),
    u("u", CLOSE, BACK, ROUNDED),
    ũ("ũ", CLOSE, BACK, ROUNDED, NASALIZED),

    ɪ("ɪ", NEAR_CLOSE, FRONT, UNROUNDED),
    ʏ("ʏ", NEAR_CLOSE, FRONT, ROUNDED),
    ɨ_("ɨ", NEAR_CLOSE, CENTRAL, UNROUNDED),
    ʉ̞_("̞ʉ̞", NEAR_CLOSE, CENTRAL, ROUNDED),
    ɯ̞("ɯ̞", NEAR_CLOSE, BACK, UNROUNDED),
    ʊ("ʊ", NEAR_CLOSE, BACK, ROUNDED),

    e("e", CLOSE_MID, FRONT, UNROUNDED),
    ẽ("ẽ", CLOSE_MID, FRONT, UNROUNDED, NASALIZED),
    ø("ø", CLOSE_MID, FRONT, ROUNDED),
    ɘ("ɘ", CLOSE_MID, CENTRAL, UNROUNDED),
    ɵ("ɵ", CLOSE_MID, CENTRAL, ROUNDED),
    ɤ("ɤ", CLOSE_MID, BACK, UNROUNDED),
    o("o", CLOSE_MID, BACK, ROUNDED),
    õ("õ", CLOSE_MID, BACK, ROUNDED, NASALIZED),

    e̞("e̞", MID, FRONT, UNROUNDED),
    ø̞("ø̞", MID, FRONT, ROUNDED),
    ə("ə", MID, CENTRAL, UNROUNDED),
    ɚ("ɚ", MID, CENTRAL, UNROUNDED, RHOTIC),
    ɤ̞("ɤ̞", MID, BACK, UNROUNDED),
    o̞("o̞", MID, BACK, ROUNDED),

    ɛ("ɛ", OPEN_MID, FRONT, UNROUNDED),
    œ("œ", OPEN_MID, FRONT, ROUNDED),
    ɜ("ɜ", OPEN_MID, CENTRAL, UNROUNDED),
    ɝ("ɝ", OPEN_MID, CENTRAL, UNROUNDED, RHOTIC),
    ɞ("ɞ", OPEN_MID, CENTRAL, ROUNDED),
    ʌ("ʌ", OPEN_MID, BACK, UNROUNDED),
    ɔ("ɔ", OPEN_MID, BACK, ROUNDED),

    æ("æ", NEAR_OPEN, FRONT, UNROUNDED),
    ɐ("ɐ", NEAR_OPEN, CENTRAL, UNROUNDED),

    a("a", OPEN, FRONT, UNROUNDED),
    ã("ã", OPEN, FRONT, UNROUNDED, NASALIZED),
    ɶ("ɶ", OPEN, FRONT, ROUNDED),
    ä("ä", OPEN, CENTRAL, UNROUNDED),
    ɒ̈("ɒ̈", OPEN, CENTRAL, ROUNDED),
    ɑ("ɑ", OPEN, BACK, UNROUNDED),
    ɑ_n("̃d", OPEN, BACK, UNROUNDED, NASALIZED),
    ɒ("ɒ", OPEN, BACK, ROUNDED);

    private final static List<Vowel> searchList = new ArrayList<>();

    private final String symbol;
    private final Rounding round;
    private final Openness open;
    private final Backness back;
    private final RColor color;

    Vowel(String symbol, Openness open, Backness back, Rounding round) {
        this(symbol, open, back, round, RColor.NOT_RHOTIC);
    }

    Vowel(String symbol, Openness open, Backness back, Rounding round, RColor color) {
        this.symbol = symbol;
        this.round = round;
        this.open = open;
        this.back = back;
        this.color = color;
    }

    @Override
    public int length() {
        return symbol.length();
    }

    @Override
    public String description() {
        return open.toString() + ", " + back.toString() + ", " + round.toString() + " vowel";
    }

    @Override
    public boolean starts(String string) {
        return string.startsWith(symbol);
    }

    @Override
    public double distanceTo(Vowel location) {
        //In theory, there should be more distance between adjacent sounds the more closed you get. That's not implemented here.
        double roundD = round.distanceTo(location.round);
        double openD = open.distanceTo(location.open);
        double backD = back.distanceTo(location.back);
        double colorD = color.distanceTo(location.color);
        return Math.sqrt((roundD * roundD) + (openD * openD) + (backD * backD) + (colorD * colorD));
    }

    public static List<Vowel> getSearchList() {
        if(searchList.size() == 0) {
            searchList.addAll(Arrays.asList(Vowel.values()));
            searchList.sort(new Comparator<Vowel>() {
                @Override
                public int compare(Vowel o1, Vowel o2) {
                    return o2.length() - o1.length();
                }
            });
        }

        return searchList;
    }
}
