package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FlatMapLoadingTest {

    @Test
    fun `flatMapLoading LCE loading to content`() {
        val result = LCE.loading().flatMapLoading { LCE.content(0) }
        assertThat(result).isEqualTo(LCE.content(0))
    }

    @Test
    fun `flatMapLoading LCE loading to new loading type`() {
        val result = LCE.loading().flatMapLoading { LCE.loading("load message") }
        assertThat(result).isEqualTo(LCE.loading("load message"))
    }

    @Test
    fun `flatMapLoading UCT loading to content`() {
        val result = UCT.loading().flatMapLoading { UCT.content(0) }
        assertThat(result).isEqualTo(UCT.content(0))
    }

    @Test
    fun `flatMapLoading UC loading to content`() {
        val result = UC.loading().flatMapLoading { UC.content(0) }
        assertThat(result).isEqualTo(UC.content(0))
    }
}