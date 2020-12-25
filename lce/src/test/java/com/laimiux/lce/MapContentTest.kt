package com.laimiux.lce

import com.google.common.truth.Truth
import org.junit.Test
import java.lang.RuntimeException

class MapContentTest {

    @Test
    fun `mapContent UCT content maps to new value`() {
        val result = UCT.content(0).mapContent { it + 1 }
        Truth.assertThat(result).isEqualTo(UCT.content(1))
    }

    @Test
    fun `mapContent UCT error stays error`() {
        val error: UCT<Int> = UCT.error(RuntimeException(""))
        val result = error.mapContent { it + 1 }
        Truth.assertThat(result).isEqualTo(error)
    }

    @Test
    fun `mapContent UCT loading stays loading`() {
        val loading: UCT<Int> = UCT.loading()
        val result = loading.mapContent { it + 1 }
        Truth.assertThat(result).isEqualTo(loading)
    }
}