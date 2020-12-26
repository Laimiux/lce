package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.lang.RuntimeException

class MapErrorTest {

    @Test
    fun `mapError LCE error to new error type`() {
        val result = LCE.error(0).mapError { "" }
        assertThat(result).isEqualTo(LCE.error(""))
    }

    @Test
    fun `mapError UCT content stays content`() {
        val result = UCT.content(0).mapError { Throwable("") }
        assertThat(result).isEqualTo(UCT.content(0))
    }

    @Test
    fun `mapError UCT error to new error value`() {
        val result = UCT.error(RuntimeException()).mapError { TestException(0) }
        assertThat(result).isEqualTo(UCT.error(TestException(0)))
    }

    @Test
    fun `mapError CE error to new error type`() {
        val result = CE.error(0).mapError { "" }
        assertThat(result).isEqualTo(CE.error(""))
    }

    @Test
    fun `mapError CT throwable to throwable`() {
        val result = CT.error(TestException(0)).mapError { TestException(1) }
        assertThat(result).isEqualTo(CT.error(TestException(1)))
    }
}