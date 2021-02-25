package org.ajar.swadesh.model

import android.content.Context
import org.ajar.swadesh.model.ipa.PhoneticSymbol
import org.ajar.swadesh.model.ipa.PulmonicConsonant
import org.ajar.swadesh.model.ipa.Vowel
import org.ajar.swadesh.model.lang.Family
import org.ajar.swadesh.model.lang.FamilyTree
import org.ajar.swadesh.model.lang.Language
import org.ajar.swadesh.model.lang.NaturalLanguage
import org.ajar.swadesh.model.mutate.Distance
import java.lang.IllegalArgumentException

object WordMatcher {

    internal class SoundRange<A : PhoneticSymbol> private constructor(val soundOne: A, val soundTwo: A) {
        private fun distance(soundOne: PhoneticSymbol, soundTwo: PhoneticSymbol) : Double {
            return Distance.between(soundOne, soundTwo)?.weightedDistance?: Double.MAX_VALUE
        }

        private fun canMatch(soundThree: A, distance: Double) : Boolean {
            return  distance(soundOne, soundThree) <= distance || distance(soundTwo, soundThree) <= distance
        }

        fun matches(soundThree: PhoneticSymbol, distance: Double) : Boolean {
            return if(soundThree::class.java == soundOne::class.java) canMatch(soundThree as A, distance) else false
        }

        fun matches(soundRange: SoundRange<*>, distance: Double) : Boolean {
            return matches(soundRange.soundOne, distance) || matches(soundRange.soundTwo, distance)
        }

        fun combine(soundRange: SoundRange<*>) : SoundRange<*>? {
            val options = arrayOf(soundOne, soundTwo, soundRange.soundOne, soundRange.soundTwo)

            var highestDistance = 0.0
            var highestPair: SoundRange<*>? = null
            for(i in options.indices) {
                for(j in i+1 until options.size) {
                    val distance = distance(options[i], options[j])
                    if(distance > highestDistance) {
                        highestPair = SoundRange(options[i],options[j])
                        highestDistance = distance
                    }
                }
            }
            return highestPair
        }

        override fun equals(other: Any?): Boolean {
            return if(other is SoundRange<*> && soundOne::class.java == other::class.java) {
                canMatch(other.soundOne as A, 0.0) && canMatch(other.soundTwo as A, 0.0)
            } else {
                super.equals(other)
            }
        }

        companion object {
            fun make(soundOne: PhoneticSymbol, soundTwo: PhoneticSymbol) : SoundRange<*>? {
                return when(soundOne) {
                    is Vowel -> if (soundTwo is Vowel) return SoundRange(soundOne, soundTwo) else null
                    is PulmonicConsonant -> if (soundTwo is PulmonicConsonant) return SoundRange(soundOne, soundTwo) else null
                    else -> null
                }
            }
        }
    }

    private data class WordRule(val sounds: List<PhoneticSymbol>)

    private var topFamily: Family? = null

    private fun setup(context: Context) {
        topFamily = FamilyTree.loadTree(context) as Family
    }

    fun extrapolateWord(word: SwadeshWord, context: Context) {
        if(topFamily == null) {
            setup(context)
        }

        collectWords(word, topFamily!!, context)
    }

    private fun reduceSet(matches: Set<SoundRange<*>>, distance: Double) : Set<SoundRange<*>> {
        var result = HashSet<SoundRange<*>>()
        var reduced = HashSet(matches)
        while(reduced.none { firstRange ->
                    matches.none { secondRange ->
                        if(firstRange != secondRange && firstRange.matches(secondRange, distance)) {
                            firstRange.combine(secondRange)?.also { result.add(it) }
                            true
                        } else {
                            false
                        }
                    }
                }) {
            reduced = result
            result = HashSet()
        }
        return result
    }

    /**
     * Recurse the tree down till you get to a NL. Create a WordRule for the word it has.
     * Then, in the next highest family take the children's word rules and work out a midpoint
     * between them, along with a rule to change the midpoint into a word from the family to the
     * child languages.
     * Keep going up the tree finding a midpoint, then creating a rule to change it into a child
     * language.
     * Identify that there are two incompatible languages and then return up the HashMap with
     * each specified.
     * Back at the top, see where a compromise couldn't be reached.
     * MutateSound and judge distance with Distance. Need to start into something that can iteratively
     * Try different combinations of things to test distance.
     */
    private fun collectWords(word: SwadeshWord, language: Language, context: Context): HashMap<Language, WordRule> {
        when(language) {
            is Family -> {
                val map = HashMap<Language, WordRule>()

                language.children.forEach { child -> map += collectWords(word, child, context) }

                //First pass
                val shared = ArrayList<PhoneticSymbol>()
                map.values.forEach { wr ->
                    if(shared.isEmpty()) {
                        shared.addAll(wr.sounds)
                    } else {
                        shared.retainAll { wr.sounds.contains(it) }
                    }
                }

                val distance = 1.0
                //Second pass
                map.values.forEach { startingWord ->
                    shared.forEach { sharedSound ->
                        val splitIndex = startingWord.sounds.indexOf(sharedSound)

                        var result: MutableSet<SoundRange<*>> = HashSet()
                        when {
                            splitIndex > 0 -> {
                                //Part before
                                val beforePart = startingWord.sounds.subList(0, splitIndex)
                                map.values.forEach { comparisonWord ->
                                    if(comparisonWord != startingWord) {
                                        val comparisonSplit = comparisonWord.sounds.indexOf(sharedSound)

                                        when {
                                            comparisonSplit > 0 -> {
                                                val comparisonBefore = comparisonWord.sounds.subList(0, comparisonSplit)

                                                val matches = beforePart.flatMap { original ->
                                                    comparisonBefore.mapNotNull { comparison ->
                                                        if(Distance.between(original, comparison)?.weightedDistance?: Double.MAX_VALUE <= distance) {
                                                            SoundRange.make(original, comparison)
                                                        } else null
                                                    }
                                                }.toSet()

                                                //TODO: Verify that we want to add all these results together than reduce them.
                                                result.addAll(reduceSet(matches, distance))
                                            }
                                        }
                                    }
                                }
                                result = reduceSet(result, distance).toMutableSet()
                                //Part after
                                val afterPart = startingWord.sounds.subList(splitIndex, startingWord.sounds.size)
                                map.values.forEach { comparisonWord ->
                                    if(comparisonWord != startingWord) {
                                        val comparisonSplit = comparisonWord.sounds.indexOf(sharedSound)

                                        when {
                                            comparisonSplit > 0 -> {
                                                val comparisonAfter = comparisonWord.sounds.subList(0, comparisonSplit)

                                                val matches = afterPart.flatMap { original ->
                                                    comparisonAfter.mapNotNull { comparison ->
                                                        if(Distance.between(original, comparison)?.weightedDistance?: Double.MAX_VALUE <= distance) {
                                                            SoundRange.make(original, comparison)
                                                        } else null
                                                    }
                                                }.toSet()

                                                //TODO: Verify that we want to add all these results together than reduce them.
                                                result.addAll(reduceSet(matches, distance))
                                            }
                                        }
                                    }
                                }
                                result = reduceSet(result, distance).toMutableSet()
                            }
                            splitIndex == 0 -> {
                                //Part after
                                val afterPart = startingWord.sounds.subList(splitIndex, startingWord.sounds.size)
                                map.values.forEach { comparisonWord ->
                                    if(comparisonWord != startingWord) {
                                        val comparisonSplit = comparisonWord.sounds.indexOf(sharedSound)

                                        when {
                                            comparisonSplit > 0 -> {
                                                val comparisonAfter = comparisonWord.sounds.subList(0, comparisonSplit)

                                                val matches = afterPart.flatMap { original ->
                                                    comparisonAfter.mapNotNull { comparison ->
                                                        if(Distance.between(original, comparison)?.weightedDistance?: Double.MAX_VALUE <= distance) {
                                                            SoundRange.make(original, comparison)
                                                        } else null
                                                    }
                                                }.toSet()

                                                //TODO: Verify that we want to add all these results together than reduce them.
                                                result.addAll(reduceSet(matches, distance))
                                            }
                                        }
                                    }
                                }
                            }
                            //TODO: Take the result you've got here and do something with it.
                        }
                    }
                }
                //TODO: All these need to have something done with them to turn them into a coherent word rule.
            }
            is NaturalLanguage -> {
                var languageWord = language.getSwadeshList(context)[word]

                if(languageWord != null) {
                    val componentList = ArrayList<PhoneticSymbol>()

                    while(languageWord!!.isNotEmpty()) {
                        val leadingSymbol = PhoneticSymbol.leadingSymbol(languageWord) ?: throw IllegalArgumentException("Cannot find leading symbol in $languageWord")
                        componentList += leadingSymbol
                        languageWord = languageWord.replaceFirst(leadingSymbol.toString(),"")
                    }

                    return hashMapOf(Pair(language, WordRule(componentList)))
                }
            }
            else -> {
                throw IllegalArgumentException("Language ${language.name} is not a natural language or language family!")
            }
        }
    }
}