package org.ajar.swadesh.model.ipa

interface PhoneticSymbol : Location {
    fun length(): Int
    fun description(): String?
    fun starts(string: String): Boolean
    fun components(): List<Location>?

    fun sharedComponents(phoneme: PhoneticSymbol): List<Location>? {
        return components()?.filter { phoneme.components()?.contains(it)?: false }
    }

    companion object {
        fun leadingSymbol(string: String): PhoneticSymbol? {
            return PulmonicConsonant.getSearchList().firstOrNull {
                it?.starts(string)?:false
            }?: Vowel.getSearchList().firstOrNull {
                it?.starts(string)?:false
            }
        }
    }
}