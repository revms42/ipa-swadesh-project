package org.ajar.swadesh.model

import android.content.Context
import org.ajar.swadesh.model.ipa.PhoneticSymbol
import org.ajar.swadesh.model.lang.NaturalLanguage
import org.ajar.swadesh.model.lang.PhoneticInventory

class GlobalStatistics(context: Context) {

    val langInventories: Map<NaturalLanguage, PhoneticInventory>
    val commonPhonemes: Map<PhoneticSymbol<*>, Int>
    val uncommonPhonemes: Map<PhoneticSymbol<*>, Int>
    val uniquePhonemes: Map<PhoneticSymbol<*>, Int>

    init {
        var totalPop = 0
        langInventories = HashMap()
        val phonemesByReceivedWeight = HashMap<PhoneticSymbol<*>, Int>()
        val phonemesByLanguageUsage = HashMap<PhoneticSymbol<*>, Int>()
        for (lang in NaturalLanguage.values()) {
            val inv = PhoneticInventory(lang.getSwadeshList(context))
            langInventories[lang] = inv

            totalPop += lang.weight
            for(phoneme in inv.allSymbolFrequencies.keys) {
                if (phonemesByReceivedWeight.containsKey(phoneme)) {
                    phonemesByReceivedWeight[phoneme] = phonemesByReceivedWeight[phoneme]!!.plus(lang.weight)
                } else {
                    phonemesByReceivedWeight[phoneme] = lang.weight
                }

                if(phonemesByLanguageUsage.containsKey(phoneme)) {
                    phonemesByLanguageUsage[phoneme] = phonemesByLanguageUsage[phoneme]!!.plus(1)
                } else {
                    phonemesByLanguageUsage[phoneme] = 1
                }
            }
        }

        val commonThreshold = totalPop/2
        val uncommonThreshold = NaturalLanguage.values().size/8

        commonPhonemes = phonemesByReceivedWeight.filterValues { it >= commonThreshold }
        uncommonPhonemes = phonemesByLanguageUsage.filterValues { it in 2..uncommonThreshold }
        uniquePhonemes = phonemesByLanguageUsage.filterValues { it == 1 }

        langInventories.values.forEach { it.characterize(this) }
    }
}
