package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FlatMapContentTest {
    @Test
    fun `flatMapContent LCE content to loading`() {
        val result = LCE.content(0).flatMapContent { LCE.loading() }
        assertThat(result).isEqualTo(LCE.loading())
    }

    @Test
    fun `flatMapContent UCT content to loading`() {
        val result = 0.toUCT().flatMapContent { UCT.loading() }
        assertThat(result).isEqualTo(UCT.loading())
    }

    @Test
    fun `flatMapContent CE content to error`() {
        val result = CE.content("").flatMapContent { CE.error(0) }
        assertThat(result).isEqualTo(CE.error(0))
    }

    @Test
    fun `flatMapContent CT content to error`() {
        val result = CT.content("").flatMapContent { CT.error(TestException(0)) }
        assertThat(result).isEqualTo(CT.error(TestException(0)))
    }

    @Test
    fun `flatMapContent UC content to loading`() {
        val result = UC.content("").flatMapContent { UC.loading() }
        assertThat(result).isEqualTo(UC.loading())
    }
}