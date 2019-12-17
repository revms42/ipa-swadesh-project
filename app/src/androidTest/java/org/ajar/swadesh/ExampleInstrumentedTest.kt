package org.ajar.swadesh

import android.util.Log
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.ajar.swadesh.model.GlobalStatistics
import org.ajar.swadesh.model.ipa.PhoneticParser
import org.ajar.swadesh.model.ipa.PhoneticSymbol
import org.ajar.swadesh.model.ipa.PulmonicConsonant
import org.ajar.swadesh.model.ipa.Vowel
import org.ajar.swadesh.model.lang.Family
import org.ajar.swadesh.model.lang.FamilyTree
import org.ajar.swadesh.model.lang.Language
import org.ajar.swadesh.model.lang.NaturalLanguage

import org.junit.Test
import org.junit.runner.RunWith

import kotlin.IllegalArgumentException as IAE

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun characterUsage() {
        val appContext = InstrumentationRegistry.getTargetContext()

        val gs = GlobalStatistics(appContext)

        gs.langInventories.forEach{ nl, pi -> Log.i(nl.name, "$pi")}
        Log.i("Common Phonemes", "${gs.commonPhonemes.keys}")

        val borean = FamilyTree.loadTree(appContext) as Family

        Log.i("Break", "------------------------------------------------------")

        NaturalLanguage.values().forEach { Log.i(it.name, "${borean.isolateFromSiblings(it)}") }
    }
}
