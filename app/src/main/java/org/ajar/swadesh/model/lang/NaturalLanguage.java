package org.ajar.swadesh.model.lang;

import android.content.Context;
import android.content.res.Resources;

import org.ajar.swadesh.R;
import org.ajar.swadesh.model.SwadeshWord;

import java.util.EnumMap;
import java.util.Map;

public enum NaturalLanguage implements Language {
    MANDARIN("Mandarin", 935, R.array.Mandarin), // (entire branch)
    SPANISH("Spanish", 390, R.array.Spanish),
    ENGLISH("English", 365, R.array.English),
    HINDI("Hindi", 295, R.array.Hindi),
    ARABIC("Arabic", 280, R.array.Arabic),
    PORTUGESE("Portuguese", 205, R.array.Portuguese),
    BENGALI("Bengali", 200, R.array.Bengali), // (Bangla)
    RUSSIAN("Russian", 160, R.array.Russian),
    JAPANESE("Japanese", 125, R.array.Japanese),
    PUNJABI("Punjabi", 95, R.array.Punjabi),
    GERMAN("German", 92, R.array.German),
    JAVANESE("Javanese", 82, R.array.Javanese),
    WU("Wu", 80, R.array.Wu), // (e.g. Shanghainese)
    MALAY("Malay", 77, R.array.Malay), // (inc. Malaysian and Indonesian)
    TELUGU("Telugu", 76, R.array.Telugu),
    VIETNAMESE("Vietnamese", 76, R.array.Vietnamese),
    KOREAN("Korean", 76, R.array.Korean),
    FRENCH("French", 75, R.array.French),
    MARATHI("Marathi", 73, R.array.Marathi),
    TAMIL("Tamil", 70, R.array.Tamil),
    URDU("Urdu", 66, R.array.Urdu),
    TURKISH("Turkish", 63, R.array.Turkish),
    ITALIAN("Italian", 59, R.array.Italian),
    YUE("Yue", 59, R.array.Yue), // (incl. Cantonese)
    THAI("Thai", 56, R.array.Thai);

    private final String name;
    private final int weight;
    private final Map<SwadeshWord,String> swadeshList;
    private final int arrayInt;

    NaturalLanguage(String name, int weight, int array){
        this.name = name;
        this.weight = weight;
        this.arrayInt = array;
        this.swadeshList = new EnumMap<>(SwadeshWord.class);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    public Map<SwadeshWord,String> getSwadeshList(Context context) {
        if(swadeshList.isEmpty()) {
            try{
                String[] swadesh = context.getResources().getStringArray(arrayInt);
                for(int i = 0; i < swadesh.length; i++) {
                    this.swadeshList.put(SwadeshWord.values()[i],swadesh[i]);
                }
            } catch (Resources.NotFoundException e) {
                throw new Resources.NotFoundException("Couldn't find " + name, e);
            }

        }

        return swadeshList;
    }

    @Override
    public String toString() {
        return name;
    }
}
