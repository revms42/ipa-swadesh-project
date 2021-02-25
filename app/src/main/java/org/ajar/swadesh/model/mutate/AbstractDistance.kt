package org.ajar.swadesh.model.mutate

import org.ajar.swadesh.model.ipa.*
import kotlin.math.pow
import kotlin.math.sqrt

class ConsonantDistance(first: PulmonicConsonant, second: PulmonicConsonant) : AbstractDistance<PulmonicConsonant>(first, second) {

    override val weightedDistance: Double
        get() {
            val voice = weight(Voice::class.java).pow(2.0)
            val coart = weight(Coarticulation::class.java).pow(2.0)
            val place = weight(Place::class.java).pow(2.0)
            val manner = weight(Manner::class.java).pow(2.0)

            return sqrt(voice + coart + place + manner)
        }

    private fun weight(type: Class<out Location>): Double {
        return directionMap[type]!! * weights[type]!!
    }

    companion object {
        val weights = hashMapOf(
                Pair(Voice::class.java, 1.0),
                Pair(Coarticulation::class.java, 1.0),
                Pair(Place::class.java, 1.0),
                Pair(Manner::class.java, 1.0)
        )
    }
}

class VowelDistance(first: Vowel, second: Vowel) : AbstractDistance<Vowel>(first, second) {

    override val weightedDistance: Double
        get() {
            val open = weight(Openness::class.java).pow(2.0)
            val back = weight(Backness::class.java).pow(2.0)
            val round = weight(Rounding::class.java).pow(2.0)
            val color = weight(RColor::class.java).pow(2.0)

            return sqrt(open + back + round + color)
        }

    private fun weight(type: Class<out Location>): Double {
        return directionMap[type]!! * weights[type]!!
    }

    companion object {
        val weights = hashMapOf(
                Pair(Openness::class.java, 1.0),
                Pair(Backness::class.java, 1.0),
                Pair(Rounding::class.java, 1.0),
                Pair(RColor::class.java, 1.0)
        )
    }
}

interface Distance<P : PhoneticSymbol> {
    val first: P
    val second: P
    val unWeightedDistance: Double
    val weightedDistance: Double
    val directionMap: Map<Class<out Location>, Double>


    companion object {
        private fun betweenConsonants(first: PulmonicConsonant, second: PulmonicConsonant) : ConsonantDistance = ConsonantDistance(first, second)
        private fun betweenVowels(first: Vowel, second: Vowel) : VowelDistance = VowelDistance(first, second)

        fun between(first: PhoneticSymbol, second: PhoneticSymbol): Distance<*>? {
            return when(first) {
                is Vowel -> if(second is Vowel) betweenVowels(first, second) else null
                is PulmonicConsonant -> if(second is PulmonicConsonant) betweenConsonants(first, second) else null
                else -> null
            }
        }
    }
}

abstract class AbstractDistance<P : PhoneticSymbol> internal constructor(override val first: P, override val second: P) : Distance<P> {

    private var _directionMap: Map<Class<out Location>,Double>? = null
    override val directionMap: Map<Class<out Location>,Double>
        get() {
            if(_directionMap == null) {
                _directionMap = first.components()?.map { fc -> Pair(fc::class.java, fc.distanceTo(second.components()?.first { sc -> sc::class.java == fc::class.java})) }?.toMap()
            }
            return _directionMap!!
        }

    override val unWeightedDistance: Double
        get() {
            return first.distanceTo(second)
        }
}
