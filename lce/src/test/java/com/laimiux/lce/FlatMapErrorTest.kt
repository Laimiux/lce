package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FlatMapErrorTest {
    @Test
    fun `flatMapError LCE error to loading`() {
        val result = LCE.error(TestException(0)).flatMapError { LCE.loading() }
        assertThat(result).isEqualTo(LCE.loading())
    }

    @Test
    fun `flatMapError LCE throwable to throwable`() {
        val result = LCE.error(TestException(0)).flatMapError { LCE.error(TestException(1)) }
        assertThat(result).isEqualTo(LCE.error(TestException(1)))
    }

    @Test
    fun `flatMapError LCE string error to throwable`() {
        val result = LCE.error("failure").flatMapError { LCE.error(TestException(1)) }
        assertThat(result).isEqualTo(LCE.error(TestException(1)))
    }

    @Test
    fun `flatMapError UCT error to loading`() {
        val result = UCT.error(Throwable()).flatMapError { UCT.loading() }
        assertThat(result).isEqualTo(UCT.loading())
    }

    @Test
    fun `flatMapError UCT error to error`() {
        val result = UCT.error(Throwable()).flatMapError { UCT.error(TestException(1)) }
        assertThat(result).isEqualTo(UCT.error(TestException(1)))
    }

    @Test
    fun `flatMapError CE throwable to throwable`() {
        val result = CE.error(Throwable()).flatMapError { CE.error(TestException(1)) }
        assertThat(result).isEqualTo(CE.error(TestException(1)))
    }

    @Test
    fun `flatMapError CE int to throwable`() {
        val result = CE.error(1).flatMapError { CE.error(TestException(it)) }
        assertThat(result).isEqualTo(CE.error(TestException(1)))
    }

    @Test
    fun `flatMapError CT throwable to throwable`() {
        val result = CT.error(Throwable()).flatMapError { CT.error(TestException(1)) }
        assertThat(result).isEqualTo(CT.error(TestException(1)))
    }
}