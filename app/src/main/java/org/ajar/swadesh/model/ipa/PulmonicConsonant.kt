package org.ajar.swadesh.model.ipa

import java.util.*
import kotlin.math.sqrt

enum class PulmonicConsonant @JvmOverloads constructor(private val symbol: String, private val voice: Voice, private val place: Place, private val manner: Manner, private val coat: Coarticulation = Coarticulation.NONE) : PhoneticSymbol {
     m_dot("m̥", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.BILABIAL, Manner.NASAL),
	 m("m", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.BILABIAL, Manner.NASAL),
	 mʲ("mʲ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.BILABIAL, Manner.NASAL, Coarticulation.PALATALIZED),
	 ɱ("ɱ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.LABIO_DENTAL, Manner.NASAL),
	 n_grav("n̼", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.LINGUO_LABIAL, Manner.NASAL),
	 n_dot("n̥", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.NASAL),
	 n("n", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.NASAL),
	 nʲ("nʲ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.NASAL, Coarticulation.PALATALIZED),
	 ɳ_dot("ɳ̊", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.RETROFLEX, Manner.NASAL),
	 ɳ("ɳ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.RETROFLEX, Manner.NASAL),
	 ɲ_super("ɲ̊", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.PALATAL, Manner.NASAL),
	 ɲ("ɲ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PALATAL, Manner.NASAL),
	 ŋ_super("ŋ̊", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.VELAR, Manner.NASAL),
	 ŋ("ŋ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.VELAR, Manner.NASAL),
	 ɴ("ɴ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.UVULAR, Manner.NASAL),
	 p("p", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.BILABIAL, Manner.STOP),
	 pʲ("pʲ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.BILABIAL, Manner.STOP, Coarticulation.PALATALIZED),
	 pʰ("pʰ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.BILABIAL, Manner.STOP, Coarticulation.ASPIRATED),
	 p_n("̃p", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.BILABIAL, Manner.STOP, Coarticulation.NASALIZED),
	 b("b", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.BILABIAL, Manner.STOP),
	 bʲ("bʲ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.BILABIAL, Manner.STOP, Coarticulation.PALATALIZED),
	 bʱ("bʱ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.BILABIAL, Manner.STOP, Coarticulation.MURMURED),
	 b_n("̃b", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.BILABIAL, Manner.STOP, Coarticulation.NASALIZED),
	 p_grav("p̪", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.LABIO_DENTAL, Manner.STOP),
	 b_grav("b̪", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.LABIO_DENTAL, Manner.STOP),
	 t_grav("t̼", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.LINGUO_LABIAL, Manner.STOP),
	 d_grav("d̼", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.LINGUO_LABIAL, Manner.STOP),
	 t("t", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.STOP),
	 tˁ("tˁ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.STOP, Coarticulation.PHARYNGEALIZED),
	 tʱ("tʱ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.STOP, Coarticulation.MURMURED),
	 tʰ("tʰ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.STOP, Coarticulation.ASPIRATED),
	 tʲ("tʲ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.STOP, Coarticulation.PALATALIZED),
	 t_n("̃t", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.STOP, Coarticulation.NASALIZED),
	 d("d", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.STOP),
	 dˁ("dˁ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.STOP, Coarticulation.PHARYNGEALIZED),
	 dʱ("dʱ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.STOP, Coarticulation.MURMURED),
	 dʲ("dʲ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.STOP, Coarticulation.PALATALIZED),
	 ʈ("ʈ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.RETROFLEX, Manner.STOP),
	 ʈʰ("ʈʰ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.RETROFLEX, Manner.STOP, Coarticulation.ASPIRATED),
	 ʈ_n("̃ʈ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.RETROFLEX, Manner.STOP, Coarticulation.NASALIZED),
	 ɖ("ɖ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.RETROFLEX, Manner.STOP),
	 ɖʱ("ɖʱ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.RETROFLEX, Manner.STOP, Coarticulation.MURMURED),
	 c("c", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.PALATAL, Manner.STOP),
	 c_n("̃c", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.PALATAL, Manner.STOP, Coarticulation.NASALIZED),
	 ɟ("ɟ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PALATAL, Manner.STOP),
	 k("k", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.VELAR, Manner.STOP),
	 kʲ("kʲ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.VELAR, Manner.STOP, Coarticulation.PALATALIZED),
	 kʰ("kʰ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.VELAR, Manner.STOP, Coarticulation.ASPIRATED),
	 k_n("̃k", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.VELAR, Manner.STOP, Coarticulation.NASALIZED),
	  //ɡ("ɡ", VOICED, VELAR, STOP),
	
     g("g", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.VELAR, Manner.STOP),
	  //ASCII because search and replace messes up the xml otherwise.
     gʲ("gʲ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.VELAR, Manner.STOP, Coarticulation.PALATALIZED),
	 gʱ("gʱ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.VELAR, Manner.STOP, Coarticulation.MURMURED),
	 g_n("̃g", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.VELAR, Manner.STOP, Coarticulation.NASALIZED),
	 q("q", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.UVULAR, Manner.STOP),
	 ɢ("ɢ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.UVULAR, Manner.STOP),
	 ʡ("ʡ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.PHARYNGEAL_EPIGLOTTAL, Manner.STOP),
	 ʔ("ʔ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.GLOTTAL, Manner.STOP),
	 ts("ts", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.SIBILANT_AFFRICATIVE),
	 tsʰ("tsʰ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.SIBILANT_AFFRICATIVE, Coarticulation.ASPIRATED),
	 dz("dz", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.SIBILANT_AFFRICATIVE),
	 tʃ("tʃ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.POST_ALVEOLAR, Manner.SIBILANT_AFFRICATIVE),
	 tʃʰ("tʃʰ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.POST_ALVEOLAR, Manner.SIBILANT_AFFRICATIVE, Coarticulation.ASPIRATED),
	 dʒ("d̠ʒ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.POST_ALVEOLAR, Manner.SIBILANT_AFFRICATIVE),
	 dʒʱ("dʒʱ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.POST_ALVEOLAR, Manner.SIBILANT_AFFRICATIVE, Coarticulation.MURMURED),
	 ʈʂ("ʈʂ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.RETROFLEX, Manner.SIBILANT_AFFRICATIVE),
	 ʈʂʰ("ʈʂʰ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.RETROFLEX, Manner.SIBILANT_AFFRICATIVE, Coarticulation.ASPIRATED),
	 ɖʐ("ɖʐ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.RETROFLEX, Manner.SIBILANT_AFFRICATIVE),
	 tɕ("tɕ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.PALATAL, Manner.SIBILANT_AFFRICATIVE),
	 tɕʰ("tɕʰ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.PALATAL, Manner.SIBILANT_AFFRICATIVE, Coarticulation.ASPIRATED),
	 dʑ("dʑ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PALATAL, Manner.SIBILANT_AFFRICATIVE),
	 dʑʱ("dʑʱ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PALATAL, Manner.SIBILANT_AFFRICATIVE, Coarticulation.MURMURED),
	 pɸ("pɸ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.BILABIAL, Manner.NON_SIBILANT_AFFRICATIVE),
	 bβ("bβ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.BILABIAL, Manner.NON_SIBILANT_AFFRICATIVE),
	 pf("p̪f", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.LABIO_DENTAL, Manner.NON_SIBILANT_AFFRICATIVE),
	 bv("b̪v", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.LABIO_DENTAL, Manner.NON_SIBILANT_AFFRICATIVE),
	 tθ("t̪θ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.DENTAL, Manner.NON_SIBILANT_AFFRICATIVE),
	 dð("d̪ð", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.DENTAL, Manner.NON_SIBILANT_AFFRICATIVE),
	 tɹ("tɹ̝̊", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.NON_SIBILANT_AFFRICATIVE),
	 dɹ("dɹ̝", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.NON_SIBILANT_AFFRICATIVE),
	 tɹ_hyper("t̠ɹ̠̊˔", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.POST_ALVEOLAR, Manner.NON_SIBILANT_AFFRICATIVE),
	 dɹ_hyper("d̠ɹ̠˔", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.POST_ALVEOLAR, Manner.NON_SIBILANT_AFFRICATIVE),
	 cç("cç", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.PALATAL, Manner.NON_SIBILANT_AFFRICATIVE),
	 ɟʝ("ɟʝ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PALATAL, Manner.NON_SIBILANT_AFFRICATIVE),
	 kx("kx", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.VELAR, Manner.NON_SIBILANT_AFFRICATIVE),
	 ɡɣ("ɡɣ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.VELAR, Manner.NON_SIBILANT_AFFRICATIVE),
	 qχ("qχ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.UVULAR, Manner.NON_SIBILANT_AFFRICATIVE),
	 ʡʢ("ʡʢ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PHARYNGEAL_EPIGLOTTAL, Manner.NON_SIBILANT_AFFRICATIVE),
	 ʔh("ʔh", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.GLOTTAL, Manner.NON_SIBILANT_AFFRICATIVE),
	 s("s", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.SIBILANT_FRICATIVE),
	 sˁ("sˁ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.SIBILANT_FRICATIVE, Coarticulation.PHARYNGEALIZED),
	 sʰ("sʰ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.SIBILANT_FRICATIVE, Coarticulation.ASPIRATED),
	 sʲ("sʲ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.SIBILANT_FRICATIVE, Coarticulation.PALATALIZED),
	 s_n("̃s", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.SIBILANT_FRICATIVE, Coarticulation.NASALIZED),
	 z("z", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.SIBILANT_FRICATIVE),
	 zʲ("zʲ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.SIBILANT_FRICATIVE, Coarticulation.PALATALIZED),
	 ʃ("ʃ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.POST_ALVEOLAR, Manner.SIBILANT_FRICATIVE),
	 ʃ_n("̃ʃ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.POST_ALVEOLAR, Manner.SIBILANT_FRICATIVE, Coarticulation.NASALIZED),
	 ʒ("ʒ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.POST_ALVEOLAR, Manner.SIBILANT_FRICATIVE),
	 ʒ_n("̃ʒ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.POST_ALVEOLAR, Manner.SIBILANT_FRICATIVE, Coarticulation.NASALIZED),
	 ʂ("ʂ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.RETROFLEX, Manner.SIBILANT_FRICATIVE),
	 ʂʰ("ʂʰ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.RETROFLEX, Manner.SIBILANT_FRICATIVE, Coarticulation.ASPIRATED),
	 ʐ("ʐ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.RETROFLEX, Manner.SIBILANT_FRICATIVE),
	 ɕ("ɕ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.PALATAL, Manner.SIBILANT_FRICATIVE),
	 ʑ("ʑ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PALATAL, Manner.SIBILANT_FRICATIVE),
	 ɸ("ɸ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.BILABIAL, Manner.NON_SIBILANT_FRICATIVE),
	 β("β", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.BILABIAL, Manner.NON_SIBILANT_FRICATIVE),
	 f("f", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.LABIO_DENTAL, Manner.NON_SIBILANT_FRICATIVE),
	 fʲ("fʲ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.LABIO_DENTAL, Manner.NON_SIBILANT_FRICATIVE, Coarticulation.PALATALIZED),
	 f_n("̃f", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.LABIO_DENTAL, Manner.NON_SIBILANT_FRICATIVE, Coarticulation.NASALIZED),
	 v("v", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.LABIO_DENTAL, Manner.NON_SIBILANT_FRICATIVE),
	 vʲ("vʲ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.LABIO_DENTAL, Manner.NON_SIBILANT_FRICATIVE, Coarticulation.PALATALIZED),
	 θ_grav("θ̼", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.LINGUO_LABIAL, Manner.NON_SIBILANT_FRICATIVE),
	 ð_grav("ð̼", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.LINGUO_LABIAL, Manner.NON_SIBILANT_FRICATIVE),
	 θ("θ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.DENTAL, Manner.NON_SIBILANT_FRICATIVE),
	 ð("ð", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.DENTAL, Manner.NON_SIBILANT_FRICATIVE),
	 ðˁ("ðˁ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.DENTAL, Manner.NON_SIBILANT_FRICATIVE, Coarticulation.PHARYNGEALIZED),
	 θ_under("θ̠", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.NON_SIBILANT_FRICATIVE),
	 ð_under("ð̠", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.NON_SIBILANT_FRICATIVE),
	 ɹ_hyper("ɹ̠̊˔", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.POST_ALVEOLAR, Manner.NON_SIBILANT_FRICATIVE),
	 ɹ_toned("ɹ̠˔", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.POST_ALVEOLAR, Manner.NON_SIBILANT_FRICATIVE),
	 ɻ("ɻ˔", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.RETROFLEX, Manner.NON_SIBILANT_FRICATIVE),
	 ç("ç", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.PALATAL, Manner.NON_SIBILANT_FRICATIVE),
	 ʝ("ʝ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PALATAL, Manner.NON_SIBILANT_FRICATIVE),
	 x("x", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.VELAR, Manner.NON_SIBILANT_FRICATIVE),
	 ɣ("ɣ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.VELAR, Manner.NON_SIBILANT_FRICATIVE),
	 χ("χ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.UVULAR, Manner.NON_SIBILANT_FRICATIVE),
	 ʁ("ʁ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.UVULAR, Manner.NON_SIBILANT_FRICATIVE),
	 ħ("ħ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.PHARYNGEAL_EPIGLOTTAL, Manner.NON_SIBILANT_FRICATIVE),
	 ʕ("ʕ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PHARYNGEAL_EPIGLOTTAL, Manner.NON_SIBILANT_FRICATIVE),
	 h("h", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.GLOTTAL, Manner.NON_SIBILANT_FRICATIVE),
	 h_n("̃h", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.GLOTTAL, Manner.NON_SIBILANT_FRICATIVE, Coarticulation.NASALIZED),
	 ɦ("ɦ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.GLOTTAL, Manner.NON_SIBILANT_FRICATIVE),
	 ʋ_sub("ʋ̥", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.LABIO_DENTAL, Manner.APPROXIMATE),
	 ʋ("ʋ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.LABIO_DENTAL, Manner.APPROXIMATE),
	 ʋ_n("̃ʋ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.LABIO_DENTAL, Manner.APPROXIMATE, Coarticulation.NASALIZED),
	 ɹ_sub("ɹ̥", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.APPROXIMATE),
	 ɹ("ɹ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.APPROXIMATE),
	 ɻ_super("ɻ̊", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.RETROFLEX, Manner.APPROXIMATE),
	 ɻ_("ɻ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.RETROFLEX, Manner.APPROXIMATE),
	 j_super("j̊", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.PALATAL, Manner.APPROXIMATE),
	 j("j", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PALATAL, Manner.APPROXIMATE),
	 j_n("̃j", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PALATAL, Manner.APPROXIMATE),
	 ɰ_super("ɰ̊", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.VELAR, Manner.APPROXIMATE),
	 ɰ("ɰ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.VELAR, Manner.APPROXIMATE),
	 ʔ_sub("ʔ̞", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.GLOTTAL, Manner.APPROXIMATE),
	 ⱱ_sub("ⱱ̟", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.BILABIAL, Manner.TAP_FLAP),
	 ⱱ("ⱱ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.LABIO_DENTAL, Manner.TAP_FLAP),
	 ɾ_grav("ɾ̼", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.LINGUO_LABIAL, Manner.TAP_FLAP),
	 ɾ_sub("ɾ̥", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.TAP_FLAP),
	 ɾ("ɾ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.TAP_FLAP),
	 ɽ_super("ɽ̊", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.RETROFLEX, Manner.TAP_FLAP),
	 ɽ("ɽ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.RETROFLEX, Manner.TAP_FLAP),
	 ɽʱ("ɽʱ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.RETROFLEX, Manner.TAP_FLAP, Coarticulation.MURMURED),
	 ɽ_n("̃ɽ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.RETROFLEX, Manner.TAP_FLAP, Coarticulation.NASALIZED),
	 ɢ_super("ɢ̆", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.UVULAR, Manner.TAP_FLAP),
	 ʡ_super("ʡ̆", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PHARYNGEAL_EPIGLOTTAL, Manner.TAP_FLAP),
	 ʙ_sub("ʙ̥", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.BILABIAL, Manner.TRILL),
	 ʙ("ʙ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.BILABIAL, Manner.TRILL),
	 r_sub("r̥", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.TRILL),
	 r("r", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.TRILL),
	 rʲ("rʲ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.TRILL, Coarticulation.PALATALIZED),
	 ʀ_sub("ʀ̥", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.UVULAR, Manner.TRILL),
	 ʀ("ʀ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.UVULAR, Manner.TRILL),
	 ʜ("ʜ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.PHARYNGEAL_EPIGLOTTAL, Manner.TRILL),
	 ʢ("ʢ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PHARYNGEAL_EPIGLOTTAL, Manner.TRILL),
	 tɬ("tɬ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.LATERAL_AFFRICATIVE),
	 dɮ("dɮ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.LATERAL_AFFRICATIVE),
	 ʈɭ_super("ʈɭ̊˔", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.RETROFLEX, Manner.LATERAL_AFFRICATIVE),
	 cʎ_super("cʎ̝̊", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.PALATAL, Manner.LATERAL_AFFRICATIVE),
	 kʟ_super("kʟ̝̊", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.VELAR, Manner.LATERAL_AFFRICATIVE),
	 ɡʟ_sub("ɡʟ̝", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.VELAR, Manner.LATERAL_AFFRICATIVE),
	 ɬ("ɬ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.LATERAL_FRICATIVE),
	 ɮ("ɮ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.LATERAL_FRICATIVE),
	 ɭ_hyper("ɭ̊˔", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.RETROFLEX, Manner.LATERAL_FRICATIVE),
	 ɭ("ɭ˔", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.RETROFLEX, Manner.LATERAL_FRICATIVE),
	 ʎ_super("ʎ̝̊", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.PALATAL, Manner.LATERAL_FRICATIVE),
	 ʎ_grav("ʎ̝", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PALATAL, Manner.LATERAL_FRICATIVE),
	 ʟ_super("ʟ̝̊", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.VELAR, Manner.LATERAL_FRICATIVE),
	 ʟ_grav("ʟ̝", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.VELAR, Manner.LATERAL_FRICATIVE),
	 l_sub("l̥", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.ALVEOLAR, Manner.LATERAL_APPROXIMATE),
	 l("l", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.LATERAL_APPROXIMATE),
	 lʲ("lʲ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.LATERAL_APPROXIMATE, Coarticulation.PALATALIZED),
	 ɭ__super("ɭ̊", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.RETROFLEX, Manner.LATERAL_APPROXIMATE),
	 ɭ_("ɭ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.RETROFLEX, Manner.LATERAL_APPROXIMATE),
	 ʎ_sub("ʎ̥", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.PALATAL, Manner.LATERAL_APPROXIMATE),
	 ʎ("ʎ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PALATAL, Manner.LATERAL_APPROXIMATE),
	 ʟ_sub("ʟ̥", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.VELAR, Manner.LATERAL_APPROXIMATE),
	 ʟ("ʟ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.VELAR, Manner.LATERAL_APPROXIMATE),
	 ʟ_under("ʟ̠", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.UVULAR, Manner.LATERAL_APPROXIMATE),
	 ɺ("ɺ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.LATERAL_TAP_FLAP),
	 ɭ_super("ɭ̆", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.RETROFLEX, Manner.LATERAL_TAP_FLAP),
	 ʎ_hyper("ʎ̆", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PALATAL, Manner.LATERAL_TAP_FLAP),
	 ʟ_hyper("ʟ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.VELAR, Manner.LATERAL_TAP_FLAP),
	 ʍ("ʍ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.VELAR, Manner.APPROXIMATE, Coarticulation.LABIALIZED),
	 w("w", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.VELAR, Manner.APPROXIMATE, Coarticulation.LABIALIZED),
	 ɫ("ɫ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.LATERAL_APPROXIMATE, Coarticulation.VELARIZED),
	 ɥ("ɥ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.PALATAL, Manner.APPROXIMATE, Coarticulation.LABIALIZED),
	 ɓ("ɓ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.BILABIAL, Manner.IMPLOSIVE),
	 ɗ("ɗ", org.ajar.swadesh.model.ipa.Voice.VOICED, Place.ALVEOLAR, Manner.IMPLOSIVE),
	 kʷ("kʷ", org.ajar.swadesh.model.ipa.Voice.VOICELESS, Place.VELAR, Manner.PLOSIVE, Coarticulation.LABIALIZED);

    private val components: MutableList<Location>
    override fun toString(): String {
        return symbol
    }

    override fun description(): String {
        return "$voice, $place, $manner"
    }

    override fun starts(string: String): Boolean {
        return string.startsWith(symbol)
    }

    override fun components(): List<Location> {
        return components
    }

    override fun length(): Int {
        return symbol.length
    }

    override fun distanceTo(location: Location): Double {
        return when(location) {
            is PulmonicConsonant -> {
                val voiceD = voice.distanceTo(location.voice)
                val mannerD = manner.distanceTo(location.manner)
                val placeD = place.distanceTo(location.place)
                val coatD = coat.distanceTo(location.coat)
                sqrt(voiceD * voiceD + mannerD * mannerD + placeD * placeD + coatD * coatD)
            }
            else -> -1.0
        }

    }

    companion object {
        private val searchList: MutableList<PulmonicConsonant?> = ArrayList()
        @JvmStatic
        fun getSearchList(): List<PulmonicConsonant?> {
            if (searchList.size == 0) {
                searchList.addAll(listOf(*values()))
                searchList.sortWith(Comparator { o1, o2 -> o2!!.length() - o1!!.length() })
            }
            return searchList
        }

        fun find(locations: Collection<Location?>): PhoneticSymbol? {
            return getSearchList().stream().filter { consonant: PulmonicConsonant? -> consonant!!.components().size == locations.size && consonant.components().containsAll(locations) }.findFirst().orElse(null)
        }
    }

    init {
        components = ArrayList()
        components.add(voice)
        components.add(place)
        components.add(manner)
        components.add(coat)
    }
}