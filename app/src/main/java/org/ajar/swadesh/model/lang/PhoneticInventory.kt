package org.ajar.swadesh.model.lang

import org.ajar.swadesh.model.GlobalStatistics
import org.ajar.swadesh.model.SwadeshWord
import org.ajar.swadesh.model.ipa.PhoneticParser
import org.ajar.swadesh.model.ipa.PhoneticSymbol
import org.ajar.swadesh.model.ipa.PulmonicConsonant
import org.ajar.swadesh.model.ipa.Vowel

class PhoneticInventory(val allSymbolFrequencies: HashMap<PhoneticSymbol<*>,Int>, val vowelsByFrequency: HashMap<Vowel,Int>, val consonantsByFrequency: HashMap<PulmonicConsonant,Int>) {

    companion object {
        fun emptyInventory() : PhoneticInventory {
            return PhoneticInventory(HashMap(), HashMap(), HashMap())
        }

        private fun filterInto(inventory: PhoneticInventory) {
            inventory.totalCount = 0
            inventory.vowelCount = 0
            inventory.consonantCount = 0

            val list = inventory.allSymbolFrequencies.toList().sortedBy { it.second }

            list.filter { it.first is Vowel }.forEach {
                inventory.vowelsByFrequency[it.first as Vowel] = it.second
                inventory.totalCount += it.second
                inventory.vowelCount += it.second
            }
            list.filter { it.first is PulmonicConsonant }.forEach {
                inventory.consonantsByFrequency[it.first as PulmonicConsonant] = it.second
                inventory.totalCount += it.second
                inventory.consonantCount += it.second
            }
        }

        private fun findFrequency(symbol: PhoneticSymbol<*>, frequencies: HashMap<PhoneticSymbol<*>,Int>, count: Int): Double {
            return if(frequencies.containsKey(symbol)) {
                frequencies[symbol]!!.toDouble() / (count.toDouble() / frequencies.size)
            } else {
                Double.NaN
            }
        }
    }

    constructor(inventory: Map<SwadeshWord, String>) : this(HashMap(), HashMap(), HashMap()) {
        for (words in inventory.values) {
            for(word in words.split(",")) {
                PhoneticParser.parsePhoneticString(word.trim()).forEach {
                    if(!allSymbolFrequencies.containsKey(it)) {
                        allSymbolFrequencies[it] = 0
                    }
                    allSymbolFrequencies[it] = allSymbolFrequencies[it]!!.plus(1)
                }
            }
        }

        filterInto(this)
    }

    operator fun plusAssign(secondInventory: PhoneticInventory) {
        for(symbol in secondInventory.allSymbolFrequencies.keys) {
            if(!allSymbolFrequencies.containsKey(symbol)) {
                allSymbolFrequencies[symbol] = 0
            }
            allSymbolFrequencies[symbol] = allSymbolFrequencies[symbol]!!.plus(1)
        }

        filterInto(this)
    }

    lateinit var uniqueSounds: List<PhoneticSymbol<*>>
    lateinit var uncommonSounds: List<PhoneticSymbol<*>>
    lateinit var commonSounds: List<PhoneticSymbol<*>>

    private var totalCount = 0
    private var vowelCount = 0
    private var consonantCount = 0

    fun frequency(symbol: PhoneticSymbol<*>): Double {
        return findFrequency(symbol, allSymbolFrequencies, totalCount)
    }

    fun relativeFrequency(symbol: PhoneticSymbol<*>) : Double {
        return when(symbol) {
            is Vowel -> findFrequency(symbol, allSymbolFrequencies, vowelCount)
            is PulmonicConsonant -> findFrequency(symbol, allSymbolFrequencies, consonantCount)
            else -> Double.NaN
        }
    }

    fun characterize(stats: GlobalStatistics) {
        uniqueSounds = allSymbolFrequencies.map { it.key }.filter { stats.uniquePhonemes.containsKey(it) }
        commonSounds = allSymbolFrequencies.map { it.key }.filter { stats.commonPhonemes.containsKey(it) }
        uncommonSounds = allSymbolFrequencies.map { it.key }.filter { !uniqueSounds.contains(it) && !commonSounds.contains(it) }
    }

    override fun toString(): String {
        val builder = StringBuilder()

        builder.append("\tUnique Vowels: ${uniqueSounds.filter { vowelsByFrequency.containsKey(it) }}\n")
        builder.append("\tUnique Consonants: ${uniqueSounds.filter { consonantsByFrequency.containsKey(it) }}\n")
        builder.append("\tUncommon Vowels: ${uncommonSounds.filter { vowelsByFrequency.containsKey(it) }}\n")
        builder.append("\tUncommon Consonants: ${uncommonSounds.filter { consonantsByFrequency.containsKey(it) }}\n")
        builder.append("\tCommon Vowels: ${commonSounds.filter { vowelsByFrequency.containsKey(it) }}\n")
        builder.append("\tCommon Consonants: ${commonSounds.filter { consonantsByFrequency.containsKey(it) }}\n")

        return builder.toString()
    }
}
