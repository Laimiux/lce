package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FlatMapContentTest {
    @Test
    fun `flatMapContent UCT content to loading`() {
        val result = 0.toUCT().flatMapContent { UCT.loading() }
        assertThat(result).isEqualTo(UCT.loading())
    }
}