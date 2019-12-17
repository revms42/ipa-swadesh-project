package org.ajar.swadesh.model.lang

import android.content.Context
import org.ajar.swadesh.model.lang.NaturalLanguage.*

class FamilyTree {

    sealed class TreeEntry(override val name: String, vararg val children: Language) : Language {
        //TODO: Should the names be resource strings?
        object Germanic : TreeEntry("Germanic", ENGLISH, GERMAN)

        object IberioRomance: TreeEntry("Iberio-Romance", PORTUGESE, SPANISH)
        object Romance: TreeEntry("Romance", IberioRomance, ITALIAN, FRENCH)

        object IndoIranian: TreeEntry("Indo-Iranian", PUNJABI, BENGALI, MARATHI, HINDI, URDU)

        object IndoEuropean: TreeEntry("Indo-European",Germanic, Romance, RUSSIAN, IndoIranian)

        object Dravidian: TreeEntry("Dravidian", TELUGU, TAMIL)

        object MacroAltaic: TreeEntry("Macro-Altaic", TURKISH, KOREAN, JAPANESE)

        object Eurasiatic: TreeEntry("Eurasiatic", IndoEuropean, MacroAltaic)

        object Nostric: TreeEntry("Nostric", Eurasiatic, Dravidian, ARABIC)

        object Sinatic: TreeEntry("Sinatic", MANDARIN, YUE, WU)

        object Austronesian: TreeEntry("Austronesian", MALAY, JAVANESE)
        object AustroThai: TreeEntry("Austro-Thai", Austronesian, THAI)

        object Austric: TreeEntry("Austric", VIETNAMESE, AustroThai)

        object DeneDiac: TreeEntry("Dene-Diac", Sinatic, Austric)

        object Borean: TreeEntry("Borean", DeneDiac, Nostric)

        override val weight: Int
            get() = children.sumBy { it.weight }
    }

    companion object {

        private val _map = HashMap<String, Family>()

        fun getFamily(name: String) : Family? {
            return _map[name]
        }

        val allFamilies: Set<String>
            get() {
                return _map.keys
            }

        fun loadTree(context: Context, language: Language = TreeEntry.Borean): Language {
            return when(language) {
                is TreeEntry -> {
                    val builder = Family.newBuilder(language.name, context)

                    for (child in language.children) {
                        builder.addChild(loadTree(context, child))
                    }
                    builder.build().also { _map[it.name] = it }
                }
                is NaturalLanguage -> {
                    language.getSwadeshList(context)
                    language
                }
                else -> throw IllegalArgumentException(language.toString())
            }
        }
    }
}