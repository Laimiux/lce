package com.laimiux.lce

import com.google.common.truth.Truth
import org.junit.Test

class OrLoadingTest {

    @Test fun `lce orLoading`() {
        val event: LCE<Unit, Int, Int>? = null
        val result = event.orLoading()
        Truth.assertThat(result).isEqualTo(LCE.loading())
    }

    @Test fun `uce orLoading`() {
        val event: UCE<Int, Int>? = null
        val result = event.orLoading()
        Truth.assertThat(result).isEqualTo(UCE.loading())
    }

    @Test fun `uct orLoading`() {
        val event: UCT<Int>? = null
        val result = event.orLoading()
        Truth.assertThat(result).isEqualTo(UCT.loading())
    }

    @Test fun `uc orLoading`() {
        val event: UC<Int>? = null
        val result = event.orLoading()
        Truth.assertThat(result).isEqualTo(UC.loading())
    }
}