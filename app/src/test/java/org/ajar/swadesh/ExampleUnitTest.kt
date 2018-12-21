package org.ajar.swadesh

import org.ajar.swadesh.model.lang.NaturalLanguage
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun checkSumLanguages() {
        var total = 0

        for (lang in NaturalLanguage.values()) {
            total += lang.weight
        }

        System.out.println(total)
    }
}
