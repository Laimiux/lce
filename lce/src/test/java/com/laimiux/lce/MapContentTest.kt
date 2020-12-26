package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MapContentTest {

    @Test
    fun `mapContent LCE content maps to new value`() {
        val result = LCE.content(0).mapContent { it + 1 }
        assertThat(result).isEqualTo(LCE.content(1))
    }

    @Test
    fun `mapContent LCE error stays error`() {
        val error = LCE.error(TestException(0))
        val result = error.mapContent { it: Int -> it + 1 }
        assertThat(result).isEqualTo(UCT.error(TestException(0)))
    }

    @Test
    fun `mapContent LCE loading stays loading`() {
        val loading = LCE.loading()
        val result = loading.mapContent { it: Int -> it + 1 }
        assertThat(result).isEqualTo(loading)
    }

    @Test
    fun `mapContent UCT content maps to new value`() {
        val result = UCT.content(0).mapContent { it + 1 }
        assertThat(result).isEqualTo(UCT.content(1))
    }

    @Test
    fun `mapContent UCT error stays error`() {
        val error = UCT.error(TestException(""))
        val result = error.mapContent { it: Int -> it + 1 }
        assertThat(result).isEqualTo(error)
    }

    @Test
    fun `mapContent UCT loading stays loading`() {
        val loading: UCT<Int> = UCT.loading()
        val result = loading.mapContent { it + 1 }
        assertThat(result).isEqualTo(loading)
    }

    @Test
    fun `mapContent CE content maps to new value`() {
        val result = CE.content(0).mapContent { it + 1 }
        assertThat(result).isEqualTo(CE.content(1))
    }

    @Test
    fun `mapContent CE error stays error`() {
        val error = CE.error(TestException(""))
        val result = error.mapContent { it: Int -> it + 1 }
        assertThat(result).isEqualTo(error)
    }

    @Test
    fun `mapContent CT content maps to new value`() {
        val result = CT.content(0).mapContent { it + 1 }
        assertThat(result).isEqualTo(CT.content(1))
    }

    @Test
    fun `mapContent CT error stays error`() {
        val error = CT.error(TestException(""))
        val result = error.mapContent { it: Int -> it + 1 }
        assertThat(result).isEqualTo(error)
    }

    @Test
    fun `mapContent UC content maps to new value`() {
        val result = UC.content(0).mapContent { it + 1 }
        assertThat(result).isEqualTo(UC.content(1))
    }

    @Test
    fun `mapContent UC loading stays loading`() {
        val loading: UC<Int> = UC.loading()
        val result = loading.mapContent { it + 1 }
        assertThat(result).isEqualTo(loading)
    }
}