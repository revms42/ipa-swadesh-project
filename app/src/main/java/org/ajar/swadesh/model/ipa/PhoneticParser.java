package org.ajar.swadesh.model.ipa;

import android.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PhoneticParser {

    private final static List<PhoneticSymbol<?>> searchSymbols;

    static {
        searchSymbols = new ArrayList<>();

        searchSymbols.addAll(PulmonicConsonant.getSearchList());
        searchSymbols.addAll(Vowel.getSearchList());
        searchSymbols.addAll(Ignore.getSearchList());

        searchSymbols.sort(new Comparator<PhoneticSymbol<?>>() {
            @Override
            public int compare(PhoneticSymbol<?> o1, PhoneticSymbol<?> o2) {
                return o2.length() - o1.length();
            }
        });
    }

    public static List<PhoneticSymbol<?>> parsePhoneticString(String s) {
        List<PhoneticSymbol<?>> symbols = new ArrayList<>();
        int i = 0;
        s = Ignore.scrub(s);
        int length = 0;
        crawl:
        while(i < s.length()) {
            if(s.substring(i).length() == length) {
                throw new IllegalArgumentException("Could not parse the symbol '" + s.substring(i).toCharArray()[0] + "'" + ", " + s);
            }
            length = s.substring(i).length();
            for(PhoneticSymbol<?> p : searchSymbols) {
                if(p.starts(s.substring(i))) {
                    if(!(p instanceof Ignore)){
                        symbols.add(p);
                    }
                    i += p.length();
                    continue crawl;
                }
            }

            throw new IllegalArgumentException("Could not parse the first symbol at '" + s.substring(i) + "', " + s);
        }

        return symbols;
    }
}
