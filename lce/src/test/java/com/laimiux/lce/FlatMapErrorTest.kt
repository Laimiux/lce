package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FlatMapErrorTest {
    @Test
    fun `flatMapError UCT error to loading`() {
        val result = UCT.error(Throwable()).flatMapError { UCT.loading() }
        assertThat(result).isEqualTo(UCT.loading())
    }
}