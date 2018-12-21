package org.ajar.swadesh

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import org.ajar.swadesh.model.ipa.PhoneticParser
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

        for (lang in NaturalLanguage.values()) {
            Log.i("Parser", "Starting $lang")
            for (words in lang.getSwadeshList(appContext).values) {
                for(word in words.split(",")) {
                    PhoneticParser.parsePhoneticString(word.trim())
                }
            }
            Log.i("Parser", "Finished $lang")
        }
    }
}
