package org.ajar.swadesh.model.lang

import android.content.Context
import org.ajar.swadesh.model.ipa.PhoneticSymbol

class Family private constructor(override val name: String, val children: List<Language> = ArrayList()): Language {

    companion object {
        fun newBuilder(name: String, context: Context): FamilyBuilder {
            return FamilyBuilder(name, context)
        }
    }

    class FamilyBuilder internal constructor(val name: String, val context: Context) {

        private val children: ArrayList<Language> = ArrayList()

        fun addChild(child: Language) {
            children.add(child)
        }

        fun build(): Family {
            val family = Family(name, children)
            for(child in children) {
                family.inventories[child] = when(child) {
                    is NaturalLanguage -> PhoneticInventory(child.getSwadeshList(context))
                    is Family -> {
                        val combined = PhoneticInventory.emptyInventory()

                        for(component in child.inventories.values) {
                            combined += component
                        }

                        combined
                    }
                    else -> throw IllegalArgumentException(child.toString())
                }
                family.allPhonemes.addAll(family.inventories.values.flatMap { it.allSymbolFrequencies.keys }.toSet())
            }
            return family
        }
    }

    private val inventories = HashMap<Language, PhoneticInventory>()
    private val allPhonemes = HashSet<PhoneticSymbol<*>>()

    override val weight: Int
        get() = children.sumBy { it.weight }

    override fun toString(): String {
        return name
    }

    /**
     * For this family, take the given sound and compare it to the the combined inventory of the
     * children.
     * If the sound is in all of the children, and it is commonly used, then it's a strong
     * correlation.
     * If the sound is in most of the children, and commonly used, or if it's in all of the children
     * and not quite as commonly used, the correlation is weaker.
     *
     */
    fun soundsLike(sound: PhoneticSymbol<*>): Double {
        var totalCount = 0
        var totalValue = 0.0
        for(inventory in inventories.values) {
            totalCount++
            val frequency = inventory.frequency(sound)

            if(frequency != Double.NaN) {
                totalValue += frequency
            }
        }
        return totalValue / totalCount
    }

    fun soundsMostLike(sound: PhoneticSymbol<*>) : Language? {
        var highestValue = 0.0
        var highestLanguage: Language? = null

        for (inventory in inventories) {
            val checkValue = inventory.value.frequency(sound)
            if(checkValue != Double.NaN && checkValue > highestValue) {
                highestValue = checkValue
                highestLanguage = inventory.key
            }
        }

        return highestLanguage
    }

    fun soundsMostLikeRelative(sound: PhoneticSymbol<*>) : Language? {
        var highestValue = 0.0
        var highestLanguage: Language? = null

        for (inventory in inventories) {
            val checkValue = inventory.value.relativeFrequency(sound)
            if(checkValue != Double.NaN && checkValue > highestValue) {
                highestValue = checkValue
                highestLanguage = inventory.key
            }
        }

        return highestLanguage
    }

    fun soundsMostLikeRelativeWeighted(sound: PhoneticSymbol<*>) : Language? {
        var highestValue = 0.0
        var highestLanguage: Language? = null

        for (inventory in inventories) {
            val checkValue = inventory.value.relativeFrequency(sound) * inventory.key.weight
            if(checkValue != Double.NaN && checkValue > highestValue) {
                highestValue = checkValue
                highestLanguage = inventory.key
            }
        }

        return highestLanguage
    }

    fun soundsMostLikeRecursive(sound: PhoneticSymbol<*>) : Language? {
        val soundsLike = soundsMostLike(sound)

        return if(soundsLike is Family) {
            soundsLike.soundsMostLikeRecursive(sound)
        } else {
            soundsLike
        }
    }

    fun soundsMostLikeRelativeRecursive(sound: PhoneticSymbol<*>) : Language? {
        val soundsLike = soundsMostLikeRelative(sound)

        return if(soundsLike is Family) {
            soundsLike.soundsMostLikeRelativeRecursive(sound)
        } else {
            soundsLike
        }
    }

    fun soundsMostLikeRelativeRecursiveWeighted(sound: PhoneticSymbol<*>) : Language? {
        val soundsLike = soundsMostLikeRelativeWeighted(sound)

        return if(soundsLike is Family) {
            soundsLike.soundsMostLikeRelativeRecursiveWeighted(sound)
        } else {
            soundsLike
        }
    }

    fun isolateLanguage(language: Language) : List<PhoneticSymbol<*>> {
        return allPhonemes.filter { soundsMostLikeRelativeRecursive(it) == language}
    }

    fun isolateFromSiblings(language: Language) : List<PhoneticSymbol<*>>? {
        return if(children.contains(language)) {
            isolateLanguage(language)
        } else {
            children.map { child ->
                if(child is Family) {
                    child.isolateFromSiblings(language)
                } else {
                    null
                }
            }.firstOrNull { it != null }
        }
    }
}
