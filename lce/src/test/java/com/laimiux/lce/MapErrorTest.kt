package com.laimiux.lce

import com.google.common.truth.Truth
import org.junit.Test
import java.lang.RuntimeException

class MapErrorTest {

    @Test
    fun `mapError UCT content stays content`() {
        val uct = UCT.content(0).mapError { Throwable("") }
        Truth.assertThat(uct).isEqualTo(UCT.content(0))
    }

    @Test
    fun `mapError UCT error to new error value`() {
        val mappedException = Throwable("")
        val uct = UCT.error(RuntimeException()).mapError { mappedException }
        Truth.assertThat(uct).isEqualTo(UCT.error(mappedException))
    }
}