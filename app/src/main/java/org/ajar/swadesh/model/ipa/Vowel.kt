package org.ajar.swadesh.model.ipa

import java.util.*
import kotlin.math.sqrt

enum class Vowel @JvmOverloads constructor(private val symbol: String, private val open: Openness, private val back: Backness, private val round: Rounding, private val color: RColor = RColor.NOT_RHOTIC) : PhoneticSymbol {
    i("i", Openness.CLOSE, Backness.FRONT, Rounding.UNROUNDED),
    ĩ("ĩ", Openness.CLOSE, Backness.FRONT, Rounding.UNROUNDED, RColor.NASALIZED),
    y("y", Openness.CLOSE, Backness.FRONT, Rounding.ROUNDED),
    ɨ("ɨ", Openness.CLOSE, Backness.CENTRAL, Rounding.UNROUNDED),
    ʉ("ʉ", Openness.CLOSE, Backness.CENTRAL, Rounding.ROUNDED),
    ɯ("ɯ", Openness.CLOSE, Backness.BACK, Rounding.UNROUNDED),
    u("u", Openness.CLOSE, Backness.BACK, Rounding.ROUNDED),
    ũ("ũ", Openness.CLOSE, Backness.BACK, Rounding.ROUNDED, RColor.NASALIZED),
    ɪ("ɪ", Openness.NEAR_CLOSE, Backness.FRONT, Rounding.UNROUNDED),
    ʏ("ʏ", Openness.NEAR_CLOSE, Backness.FRONT, Rounding.ROUNDED),
    ɨ_("ɨ", Openness.NEAR_CLOSE, Backness.CENTRAL, Rounding.UNROUNDED),
    ʉ_dot("̞ʉ̞", Openness.NEAR_CLOSE, Backness.CENTRAL, Rounding.ROUNDED),
    ɯ_dot("ɯ̞", Openness.NEAR_CLOSE, Backness.BACK, Rounding.UNROUNDED),
    ʊ("ʊ", Openness.NEAR_CLOSE, Backness.BACK, Rounding.ROUNDED),
    e("e", Openness.CLOSE_MID, Backness.FRONT, Rounding.UNROUNDED),
    ẽ("ẽ", Openness.CLOSE_MID, Backness.FRONT, Rounding.UNROUNDED, RColor.NASALIZED),
    ø("ø", Openness.CLOSE_MID, Backness.FRONT, Rounding.ROUNDED),
    ɘ("ɘ", Openness.CLOSE_MID, Backness.CENTRAL, Rounding.UNROUNDED),
    ɵ("ɵ", Openness.CLOSE_MID, Backness.CENTRAL, Rounding.ROUNDED),
    ɤ("ɤ", Openness.CLOSE_MID, Backness.BACK, Rounding.UNROUNDED),
    o("o", Openness.CLOSE_MID, Backness.BACK, Rounding.ROUNDED),
    õ("õ", Openness.CLOSE_MID, Backness.BACK, Rounding.ROUNDED, RColor.NASALIZED),
    e_dot("e̞", Openness.MID, Backness.FRONT, Rounding.UNROUNDED),
    ø_dot("ø̞", Openness.MID, Backness.FRONT, Rounding.ROUNDED),
    ə("ə", Openness.MID, Backness.CENTRAL, Rounding.UNROUNDED),
    ɚ("ɚ", Openness.MID, Backness.CENTRAL, Rounding.UNROUNDED, RColor.RHOTIC),
    ɤ_dot("ɤ̞", Openness.MID, Backness.BACK, Rounding.UNROUNDED),
    o_dot("o̞", Openness.MID, Backness.BACK, Rounding.ROUNDED),
    ɛ("ɛ", Openness.OPEN_MID, Backness.FRONT, Rounding.UNROUNDED),
    œ("œ", Openness.OPEN_MID, Backness.FRONT, Rounding.ROUNDED),
    ɜ("ɜ", Openness.OPEN_MID, Backness.CENTRAL, Rounding.UNROUNDED),
    ɝ("ɝ", Openness.OPEN_MID, Backness.CENTRAL, Rounding.UNROUNDED, RColor.RHOTIC),
    ɞ("ɞ", Openness.OPEN_MID, Backness.CENTRAL, Rounding.ROUNDED),
    ʌ("ʌ", Openness.OPEN_MID, Backness.BACK, Rounding.UNROUNDED),
    ɔ("ɔ", Openness.OPEN_MID, Backness.BACK, Rounding.ROUNDED),
    æ("æ", Openness.NEAR_OPEN, Backness.FRONT, Rounding.UNROUNDED),
    ɐ("ɐ", Openness.NEAR_OPEN, Backness.CENTRAL, Rounding.UNROUNDED),
    a("a", Openness.OPEN, Backness.FRONT, Rounding.UNROUNDED),
    ã("ã", Openness.OPEN, Backness.FRONT, Rounding.UNROUNDED, RColor.NASALIZED),
    ɶ("ɶ", Openness.OPEN, Backness.FRONT, Rounding.ROUNDED),
    ä("ä", Openness.OPEN, Backness.CENTRAL, Rounding.UNROUNDED),
    ɒ_dot("ɒ̈", Openness.OPEN, Backness.CENTRAL, Rounding.ROUNDED),
    ɑ("ɑ", Openness.OPEN, Backness.BACK, Rounding.UNROUNDED),
    ɑ_n("̃d", Openness.OPEN, Backness.BACK, Rounding.UNROUNDED, RColor.NASALIZED),
    ɒ("ɒ", Openness.OPEN, Backness.BACK, Rounding.ROUNDED);

    private val components: MutableList<Location>
    override fun length(): Int {
        return symbol.length
    }

    override fun description(): String {
        return "$open, $back, $round vowel"
    }

    override fun starts(string: String): Boolean {
        return string.startsWith(symbol)
    }

    override fun components(): List<Location> {
        return components
    }

    override fun distanceTo(location: Location): Double {
        //In theory, there should be more distance between adjacent sounds the more closed you get. That's not implemented here.
        return when(location) {
            is Vowel -> {
                val roundD = round.distanceTo(location.round)
                val openD = open.distanceTo(location.open)
                val backD = back.distanceTo(location.back)
                val colorD = color.distanceTo(location.color)
                sqrt(roundD * roundD + openD * openD + backD * backD + colorD * colorD)
            }
            else -> -1.0
        }
    }

    companion object {
        private val searchList: MutableList<Vowel?> = ArrayList()
        @JvmStatic
        fun getSearchList(): List<Vowel?> {
            if (searchList.size == 0) {
                searchList.addAll(listOf(*values()))
                searchList.sortWith(Comparator { o1, o2 -> o2!!.length() - o1!!.length() })
            }
            return searchList
        }

        fun find(locations: Collection<Location?>): PhoneticSymbol? {
            return getSearchList().stream().filter { vowel: Vowel? -> vowel!!.components().size == locations.size && vowel.components().containsAll(locations) }.findFirst().orElse(null)
        }
    }

    init {
        components = ArrayList()
        components.add(round)
        components.add(open)
        components.add(back)
        components.add(color)
    }
}