package com.laimiux.lce

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FlatMapLoadingTest {

    @Test
    fun `flatMapLoading UCT loading to content`() {
        val uct: UCT<Int> = UCT.loading()
        val result = uct.flatMapLoading { UCT.content(0) }
        assertThat(result).isEqualTo(UCT.content(0))
    }
}