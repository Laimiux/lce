package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FoldErrorTest {
    @Test
    fun `fold LCE error to UC loading`() {
        val error = LCE.error(TestException(0))
        val result = error.foldError(
            onError = { UC.loading() },
            onOther = { it }
        )
        assertThat(result).isEqualTo(UC.loading())
    }

    @Test
    fun `fold UCE error to UC loading`() {
        val error = UCE.error(TestException(0))
        val result = error.foldError(
            onError = { UC.loading() },
            onOther = { it }
        )
        assertThat(result).isEqualTo(UC.loading())
    }

    @Test
    fun `fold UCT error to UC loading`() {
        val error = UCT.error(TestException(0))
        val result = error.foldError(
            onError = { UC.loading() },
            onOther = { it }
        )
        assertThat(result).isEqualTo(UC.loading())
    }
}