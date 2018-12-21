package org.ajar.swadesh.model.ipa;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.ajar.swadesh.model.ipa.Voice.VOICELESS;
import static org.ajar.swadesh.model.ipa.Voice.VOICED;

import static org.ajar.swadesh.model.ipa.Place.*;
import static org.ajar.swadesh.model.ipa.Manner.*;
import static org.ajar.swadesh.model.ipa.Coarticulation.*;

public enum PulmonicConsonant implements PhoneticSymbol<PulmonicConsonant> {
    m̥("m̥", VOICELESS, BILABIAL, NASAL),
    m("m", VOICED, BILABIAL, NASAL),
    mʲ("mʲ", VOICED, BILABIAL, NASAL, PALATALIZED),
    ɱ("ɱ", VOICED, LABIO_DENTAL, NASAL),
    n̼("n̼", VOICED, LINGUO_LABIAL, NASAL),
    n̥("n̥", VOICELESS, ALVEOLAR, NASAL),
    n("n", VOICED, ALVEOLAR, NASAL),
    nʲ("nʲ", VOICED, ALVEOLAR, NASAL, PALATALIZED),
    ɳ̊("ɳ̊", VOICELESS, RETROFLEX, NASAL),
    ɳ("ɳ", VOICED, RETROFLEX, NASAL),
    ɲ̊("ɲ̊", VOICELESS, PALATAL, NASAL),
    ɲ("ɲ", VOICED, PALATAL, NASAL),
    ŋ̊("ŋ̊", VOICELESS, VELAR, NASAL),
    ŋ("ŋ", VOICED, VELAR, NASAL),
    ɴ("ɴ", VOICED, UVULAR, NASAL),

    p("p", VOICELESS, BILABIAL, STOP),
    pʲ("pʲ", VOICELESS, BILABIAL, STOP, PALATALIZED),
    pʰ("pʰ", VOICELESS, BILABIAL, STOP, ASPIRATED),
    p_n("̃p", VOICELESS, BILABIAL, STOP, NASALIZED),
    b("b", VOICED, BILABIAL, STOP),
    bʲ("bʲ", VOICED, BILABIAL, STOP, PALATALIZED),
    bʱ("bʱ", VOICED, BILABIAL, STOP, MURMURED),
    b_n("̃b", VOICED, BILABIAL, STOP, NASALIZED),
    p̪("p̪", VOICELESS, LABIO_DENTAL, STOP),
    b̪("b̪", VOICED, LABIO_DENTAL, STOP),
    t̼("t̼", VOICELESS, LINGUO_LABIAL, STOP),
    d̼("d̼", VOICED, LINGUO_LABIAL, STOP),
    t("t", VOICELESS, ALVEOLAR, STOP),
    tˁ("tˁ", VOICELESS, ALVEOLAR, STOP, PHARYNGEALIZED),
    tʱ("tʱ", VOICELESS, ALVEOLAR, STOP, MURMURED),
    tʰ("tʰ", VOICELESS, ALVEOLAR, STOP, ASPIRATED),
    tʲ("tʲ", VOICELESS, ALVEOLAR, STOP, PALATALIZED),
    t_n("̃t", VOICELESS, ALVEOLAR, STOP, NASALIZED),
    d("d", VOICED, ALVEOLAR, STOP),
    dˁ("dˁ", VOICED, ALVEOLAR, STOP, PHARYNGEALIZED),
    dʱ("dʱ", VOICED, ALVEOLAR, STOP, MURMURED),
    dʲ("dʲ", VOICED, ALVEOLAR, STOP, PALATALIZED),
    ʈ("ʈ", VOICELESS, RETROFLEX, STOP),
    ʈʰ("ʈʰ", VOICELESS, RETROFLEX, STOP, ASPIRATED),
    ʈ_n("̃ʈ", VOICELESS, RETROFLEX, STOP, NASALIZED),
    ɖ("ɖ", VOICED, RETROFLEX, STOP),
    ɖʱ("ɖʱ", VOICED, RETROFLEX, STOP, MURMURED),
    c("c", VOICELESS, PALATAL, STOP),
    c_n("̃c", VOICELESS, PALATAL, STOP, NASALIZED),
    ɟ("ɟ", VOICED, PALATAL, STOP),
    k("k", VOICELESS, VELAR, STOP),
    kʲ("kʲ", VOICELESS, VELAR, STOP, PALATALIZED),
    kʰ("kʰ", VOICELESS, VELAR, STOP, ASPIRATED),
    k_n("̃k", VOICELESS, VELAR, STOP, NASALIZED),
    //ɡ("ɡ", VOICED, VELAR, STOP),
    g("g", VOICED, VELAR, STOP), //ASCII because search and replace messes up the xml otherwise.
    gʲ("gʲ", VOICED, VELAR, STOP, PALATALIZED),
    gʱ("gʱ", VOICED, VELAR, STOP, MURMURED),
    g_n("̃g", VOICED, VELAR, STOP, NASALIZED),
    q("q", VOICELESS, UVULAR, STOP),
    ɢ("ɢ", VOICED, UVULAR, STOP),
    ʡ("ʡ", VOICELESS, PHARYNGEAL_EPIGLOTTAL, STOP),
    ʔ("ʔ", VOICELESS, GLOTTAL, STOP),

    ts("ts", VOICELESS, ALVEOLAR, SIBILANT_AFFRICATIVE),
    tsʰ("tsʰ", VOICELESS, ALVEOLAR, SIBILANT_AFFRICATIVE, ASPIRATED),
    dz("dz", VOICED, ALVEOLAR, SIBILANT_AFFRICATIVE),
    tʃ("tʃ", VOICELESS, POST_ALVEOLAR, SIBILANT_AFFRICATIVE),
    tʃʰ("tʃʰ", VOICELESS, POST_ALVEOLAR, SIBILANT_AFFRICATIVE, ASPIRATED),
    d̠ʒ("d̠ʒ", VOICED, POST_ALVEOLAR, SIBILANT_AFFRICATIVE),
    dʒʱ("dʒʱ", VOICED, POST_ALVEOLAR, SIBILANT_AFFRICATIVE, MURMURED),
    ʈʂ("ʈʂ", VOICELESS, RETROFLEX, SIBILANT_AFFRICATIVE),
    ʈʂʰ("ʈʂʰ", VOICELESS, RETROFLEX, SIBILANT_AFFRICATIVE, ASPIRATED),
    ɖʐ("ɖʐ", VOICED, RETROFLEX, SIBILANT_AFFRICATIVE),
    tɕ("tɕ", VOICELESS, PALATAL, SIBILANT_AFFRICATIVE),
    tɕʰ("tɕʰ", VOICELESS, PALATAL, SIBILANT_AFFRICATIVE, ASPIRATED),
    dʑ("dʑ", VOICED, PALATAL, SIBILANT_AFFRICATIVE),
    dʑʱ("dʑʱ", VOICED, PALATAL, SIBILANT_AFFRICATIVE, MURMURED),

    pɸ("pɸ", VOICELESS, BILABIAL, NON_SIBILANT_AFFRICATIVE),
    bβ("bβ", VOICED, BILABIAL, NON_SIBILANT_AFFRICATIVE),
    p̪f("p̪f", VOICELESS, LABIO_DENTAL, NON_SIBILANT_AFFRICATIVE),
    b̪v("b̪v", VOICED, LABIO_DENTAL, NON_SIBILANT_AFFRICATIVE),
    t̪θ("t̪θ", VOICELESS, DENTAL, NON_SIBILANT_AFFRICATIVE),
    d̪ð("d̪ð", VOICED, DENTAL, NON_SIBILANT_AFFRICATIVE),
    tɹ̝̊("tɹ̝̊", VOICELESS, ALVEOLAR, NON_SIBILANT_AFFRICATIVE),
    dɹ̝("dɹ̝", VOICED, ALVEOLAR, NON_SIBILANT_AFFRICATIVE),
    t̠ɹ̠̊("t̠ɹ̠̊˔", VOICELESS, POST_ALVEOLAR, NON_SIBILANT_AFFRICATIVE),
    d̠ɹ̠("d̠ɹ̠˔", VOICED, POST_ALVEOLAR, NON_SIBILANT_AFFRICATIVE),
    cç("cç", VOICELESS, PALATAL, NON_SIBILANT_AFFRICATIVE),
    ɟʝ("ɟʝ", VOICED, PALATAL, NON_SIBILANT_AFFRICATIVE),
    kx("kx", VOICELESS, VELAR, NON_SIBILANT_AFFRICATIVE),
    ɡɣ("ɡɣ", VOICED, VELAR, NON_SIBILANT_AFFRICATIVE),
    qχ("qχ", VOICELESS, UVULAR, NON_SIBILANT_AFFRICATIVE),
    ʡʢ("ʡʢ", VOICED, PHARYNGEAL_EPIGLOTTAL, NON_SIBILANT_AFFRICATIVE),
    ʔh("ʔh", VOICELESS, GLOTTAL, NON_SIBILANT_AFFRICATIVE),

    s("s", VOICELESS, ALVEOLAR, SIBILANT_FRICATIVE),
    sˁ("sˁ", VOICELESS, ALVEOLAR, SIBILANT_FRICATIVE, PHARYNGEALIZED),
    sʰ("sʰ", VOICELESS, ALVEOLAR, SIBILANT_FRICATIVE, ASPIRATED),
    sʲ("sʲ", VOICELESS, ALVEOLAR, SIBILANT_FRICATIVE, PALATALIZED),
    s_n("̃s", VOICELESS, ALVEOLAR, SIBILANT_FRICATIVE, NASALIZED),
    z("z", VOICED, ALVEOLAR, SIBILANT_FRICATIVE),
    zʲ("zʲ", VOICED, ALVEOLAR, SIBILANT_FRICATIVE, PALATALIZED),
    ʃ("ʃ", VOICELESS, POST_ALVEOLAR, SIBILANT_FRICATIVE),
    ʃ_n("̃ʃ", VOICELESS, POST_ALVEOLAR, SIBILANT_FRICATIVE, NASALIZED),
    ʒ("ʒ", VOICED, POST_ALVEOLAR, SIBILANT_FRICATIVE),
    ʒ_n("̃ʒ", VOICED, POST_ALVEOLAR, SIBILANT_FRICATIVE, NASALIZED),
    ʂ("ʂ", VOICELESS, RETROFLEX, SIBILANT_FRICATIVE),
    ʂʰ("ʂʰ", VOICELESS, RETROFLEX, SIBILANT_FRICATIVE, ASPIRATED),
    ʐ("ʐ", VOICED, RETROFLEX, SIBILANT_FRICATIVE),
    ɕ("ɕ", VOICELESS, PALATAL, SIBILANT_FRICATIVE),
    ʑ("ʑ", VOICED, PALATAL, SIBILANT_FRICATIVE),

    ɸ("ɸ", VOICELESS, BILABIAL, NON_SIBILANT_FRICATIVE),
    β("β", VOICED, BILABIAL, NON_SIBILANT_FRICATIVE),
    f("f", VOICELESS, LABIO_DENTAL, NON_SIBILANT_FRICATIVE),
    fʲ("fʲ", VOICELESS, LABIO_DENTAL, NON_SIBILANT_FRICATIVE, PALATALIZED),
    f_n("̃f", VOICELESS, LABIO_DENTAL, NON_SIBILANT_FRICATIVE, NASALIZED),
    v("v", VOICED, LABIO_DENTAL, NON_SIBILANT_FRICATIVE),
    vʲ("vʲ", VOICED, LABIO_DENTAL, NON_SIBILANT_FRICATIVE, PALATALIZED),
    θ̼("θ̼", VOICELESS, LINGUO_LABIAL, NON_SIBILANT_FRICATIVE),
    ð̼("ð̼", VOICED, LINGUO_LABIAL, NON_SIBILANT_FRICATIVE),
    θ("θ", VOICELESS, DENTAL, NON_SIBILANT_FRICATIVE),
    ð("ð", VOICED, DENTAL, NON_SIBILANT_FRICATIVE),
    ðˁ("ðˁ", VOICED, DENTAL, NON_SIBILANT_FRICATIVE, PHARYNGEALIZED),
    θ̠("θ̠", VOICELESS, ALVEOLAR, NON_SIBILANT_FRICATIVE),
    ð̠("ð̠", VOICED, ALVEOLAR, NON_SIBILANT_FRICATIVE),
    ɹ̠̊("ɹ̠̊˔", VOICELESS, POST_ALVEOLAR, NON_SIBILANT_FRICATIVE),
    ɹ̠("ɹ̠˔", VOICED, POST_ALVEOLAR, NON_SIBILANT_FRICATIVE),
    ɻ("ɻ˔", VOICED, RETROFLEX, NON_SIBILANT_FRICATIVE),
    ç("ç", VOICELESS, PALATAL, NON_SIBILANT_FRICATIVE),
    ʝ("ʝ", VOICED, PALATAL, NON_SIBILANT_FRICATIVE),
    x("x", VOICELESS, VELAR, NON_SIBILANT_FRICATIVE),
    ɣ("ɣ", VOICED, VELAR, NON_SIBILANT_FRICATIVE),
    χ("χ", VOICELESS, UVULAR, NON_SIBILANT_FRICATIVE),
    ʁ("ʁ", VOICED, UVULAR, NON_SIBILANT_FRICATIVE),
    ħ("ħ", VOICELESS, PHARYNGEAL_EPIGLOTTAL, NON_SIBILANT_FRICATIVE),
    ʕ("ʕ", VOICED, PHARYNGEAL_EPIGLOTTAL, NON_SIBILANT_FRICATIVE),
    h("h", VOICELESS, GLOTTAL, NON_SIBILANT_FRICATIVE),
    h_n("̃h", VOICELESS, GLOTTAL, NON_SIBILANT_FRICATIVE, NASALIZED),
    ɦ("ɦ", VOICED, GLOTTAL, NON_SIBILANT_FRICATIVE),

    ʋ̥("ʋ̥", VOICELESS, LABIO_DENTAL, APPROXIMATE),
    ʋ("ʋ", VOICED, LABIO_DENTAL, APPROXIMATE),
    ʋ_n("̃ʋ", VOICED, LABIO_DENTAL, APPROXIMATE, NASALIZED),
    ɹ̥("ɹ̥", VOICELESS, ALVEOLAR, APPROXIMATE),
    ɹ("ɹ", VOICED, ALVEOLAR, APPROXIMATE),
    ɻ̊("ɻ̊", VOICELESS, RETROFLEX, APPROXIMATE),
    ɻ_("ɻ", VOICED, RETROFLEX, APPROXIMATE),
    j̊("j̊", VOICELESS, PALATAL, APPROXIMATE),
    j("j", VOICED, PALATAL, APPROXIMATE),
    j_n("̃j", VOICED, PALATAL, APPROXIMATE),
    ɰ̊("ɰ̊", VOICELESS, VELAR, APPROXIMATE),
    ɰ("ɰ", VOICED, VELAR, APPROXIMATE),
    ʔ̞("ʔ̞", VOICED, GLOTTAL, APPROXIMATE),

    ⱱ̟("ⱱ̟", VOICED, BILABIAL, TAP_FLAP),
    ⱱ("ⱱ", VOICED, LABIO_DENTAL, TAP_FLAP),
    ɾ̼("ɾ̼", VOICED, LINGUO_LABIAL, TAP_FLAP),
    ɾ̥("ɾ̥", VOICELESS, ALVEOLAR, TAP_FLAP),
    ɾ("ɾ", VOICED, ALVEOLAR, TAP_FLAP),
    ɽ̊("ɽ̊", VOICELESS, RETROFLEX, TAP_FLAP),
    ɽ("ɽ", VOICED, RETROFLEX, TAP_FLAP),
    ɽʱ("ɽʱ", VOICED, RETROFLEX, TAP_FLAP,MURMURED),
    ɽ_n("̃ɽ", VOICED, RETROFLEX, TAP_FLAP, NASALIZED),
    ɢ̆("ɢ̆", VOICED, UVULAR, TAP_FLAP),
    ʡ̆("ʡ̆", VOICED, PHARYNGEAL_EPIGLOTTAL, TAP_FLAP),

    ʙ̥("ʙ̥", VOICELESS, BILABIAL, TRILL),
    ʙ("ʙ", VOICED, BILABIAL, TRILL),
    r̥("r̥", VOICELESS, ALVEOLAR, TRILL),
    r("r", VOICED, ALVEOLAR, TRILL),
    rʲ("rʲ", VOICED, ALVEOLAR, TRILL, PALATALIZED),
    ʀ̥("ʀ̥", VOICELESS, UVULAR, TRILL),
    ʀ("ʀ", VOICED, UVULAR, TRILL),
    ʜ("ʜ", VOICELESS, PHARYNGEAL_EPIGLOTTAL, TRILL),
    ʢ("ʢ", VOICED, PHARYNGEAL_EPIGLOTTAL, TRILL),

    tɬ("tɬ", VOICELESS, ALVEOLAR, LATERAL_AFFRICATIVE),
    dɮ("dɮ", VOICED, ALVEOLAR, LATERAL_AFFRICATIVE),
    ʈɭ̊("ʈɭ̊˔", VOICELESS, RETROFLEX, LATERAL_AFFRICATIVE),
    cʎ̝̊("cʎ̝̊", VOICELESS, PALATAL, LATERAL_AFFRICATIVE),
    kʟ̝̊("kʟ̝̊", VOICELESS, VELAR, LATERAL_AFFRICATIVE),
    ɡʟ̝("ɡʟ̝", VOICED, VELAR, LATERAL_AFFRICATIVE),

    ɬ("ɬ", VOICELESS, ALVEOLAR, LATERAL_FRICATIVE),
    ɮ("ɮ", VOICED, ALVEOLAR, LATERAL_FRICATIVE),
    ɭ̊("ɭ̊˔", VOICELESS, RETROFLEX, LATERAL_FRICATIVE),
    ɭ("ɭ˔", VOICED, RETROFLEX, LATERAL_FRICATIVE),
    ʎ̝̊("ʎ̝̊", VOICELESS, PALATAL, LATERAL_FRICATIVE),
    ʎ̝("ʎ̝", VOICED, PALATAL, LATERAL_FRICATIVE),
    ʟ̝̊("ʟ̝̊", VOICELESS, VELAR, LATERAL_FRICATIVE),
    ʟ̝("ʟ̝", VOICED, VELAR, LATERAL_FRICATIVE),

    l̥("l̥", VOICELESS, ALVEOLAR, LATERAL_APPROXIMATE),
    l("l", VOICED, ALVEOLAR, LATERAL_APPROXIMATE),
    lʲ("lʲ", VOICED, ALVEOLAR, LATERAL_APPROXIMATE, PALATALIZED),
    ɭ_̊("ɭ̊", VOICELESS, RETROFLEX, LATERAL_APPROXIMATE),
    ɭ_("ɭ", VOICED, RETROFLEX, LATERAL_APPROXIMATE),
    ʎ̥("ʎ̥", VOICELESS, PALATAL, LATERAL_APPROXIMATE),
    ʎ("ʎ", VOICED, PALATAL, LATERAL_APPROXIMATE),
    ʟ̥("ʟ̥", VOICELESS, VELAR, LATERAL_APPROXIMATE),
    ʟ("ʟ", VOICED, VELAR, LATERAL_APPROXIMATE),
    ʟ̠("ʟ̠", VOICED, UVULAR, LATERAL_APPROXIMATE),

    ɺ("ɺ", VOICED, ALVEOLAR, LATERAL_TAP_FLAP),
    ɭ̆("ɭ̆", VOICED, RETROFLEX, LATERAL_TAP_FLAP),
    ʎ̆("ʎ̆", VOICED, PALATAL, LATERAL_TAP_FLAP),
    ʟ̆("ʟ", VOICED, VELAR, LATERAL_TAP_FLAP),

    ʍ("ʍ", VOICELESS, VELAR, APPROXIMATE, LABIALIZED),
    w("w", VOICED, VELAR, APPROXIMATE, LABIALIZED),
    ɫ("ɫ", VOICED, ALVEOLAR, LATERAL_APPROXIMATE, VELARIZED),
    ɥ("ɥ", VOICED, PALATAL, APPROXIMATE, LABIALIZED),
    ɓ("ɓ", VOICED, BILABIAL, IMPLOSIVE),
    ɗ("ɗ", VOICED, ALVEOLAR, IMPLOSIVE),
    kʷ("kʷ", VOICELESS, VELAR, PLOSIVE, LABIALIZED);

    private final static List<PulmonicConsonant> searchList = new ArrayList<>();

    private final String symbol;
    private final Place place;
    private final Manner manner;
    private final Voice voice;
    private final Coarticulation coat;

    PulmonicConsonant(String symbol, Voice voice, Place place, Manner manner) {
        this(symbol, voice, place, manner, NONE);
    }

    PulmonicConsonant(String symbol, Voice voice, Place place, Manner manner, Coarticulation coat) {
        this.symbol = symbol;
        this.place = place;
        this.manner = manner;
        this.voice = voice;
        this.coat = coat;
    }

    @Override
    public String toString() {
        return symbol;
    }

    @Override
    public String description() {
        return voice.toString() + ", " + place.toString() + ", " + manner.toString();
    }

    @Override
    public boolean starts(String string) {
        return string.startsWith(symbol);
    }

    @Override
    public int length() {
        return symbol.length();
    }

    public static List<PulmonicConsonant> getSearchList() {
        if(searchList.size() == 0) {
            searchList.addAll(Arrays.asList(PulmonicConsonant.values()));
            searchList.sort(new Comparator<PulmonicConsonant>() {
                @Override
                public int compare(PulmonicConsonant o1, PulmonicConsonant o2) {
                    return o2.length() - o1.length();
                }
            });
        }

        return searchList;
    }

    @Override
    public double distanceTo(PulmonicConsonant location) {
        double voiceD = voice.distanceTo(location.voice);
        double mannerD = manner.distanceTo(location.manner);
        double placeD = place.distanceTo(location.place);
        double coatD = coat.distanceTo(location.coat);
        return Math.sqrt((voiceD * voiceD) + (mannerD * mannerD) + (placeD * placeD) + (coatD * coatD));
    }
}
