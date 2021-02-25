package org.ajar.swadesh.model.mutate

import org.ajar.swadesh.model.ipa.*

class MutateSound(val location: Class<out Location>, val distance: Int ) {

    fun mutate(symbol: PhoneticSymbol) : PhoneticSymbol {
        val mutable = symbol.components()!!.first { component -> component::class.java.isAssignableFrom(location) }

        val components = HashMap(symbol.components()!!.map { component -> Pair(component::class.java, component) }.toMap())
        val value = when(mutable) {
            is Place -> move(Place.values(), components[Place::class.java]!!, distance)
            is Rounding -> move(Rounding.values(), components[Rounding::class.java]!!, distance)
            is Coarticulation -> move(Coarticulation.values(), components[Coarticulation::class.java]!!, distance)
            is RColor -> move(RColor.values(), components[RColor::class.java]!!, distance)
            is Manner -> move(Manner.values(), components[Manner::class.java]!!, distance)
            is Backness -> move(Backness.values(), components[Backness::class.java]!!, distance)
            is Openness -> move(Openness.values(), components[Openness::class.java]!!, distance)
            is Voice -> move(Voice.values(), components[Voice::class.java]!!, distance)
            else -> throw IllegalArgumentException("Non-mapped location ${mutable::class.java.simpleName}")
        }

        components[value::class.java] = value

        return when(symbol) {
            is PulmonicConsonant -> PulmonicConsonant.find(components.values)!!
            is Vowel -> Vowel.find(components.values)!!
            else -> throw IllegalArgumentException("Non-consonant, non-vowel class ${symbol::class.java.simpleName}")
        }
    }

    private fun move(array: Array<out Location>, current: Location, distance: Int) : Location {
        val index = array.indexOf(current)

        return if(index == 0 && distance < 0) {
            current
        } else if (index == array.lastIndex && distance > 0) {
            current
        } else {
            array[index + distance]
        }
    }
}