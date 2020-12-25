package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.lang.RuntimeException

class UCTTest {
    @Test fun `mapContent content maps to new value`() {
        val result = UCT.content(0).mapContent { it + 1 }
        assertThat(result).isEqualTo(UCT.content(1))
    }

    @Test fun `mapContent error stays error`() {
        val error: UCT<Int> = UCT.error(RuntimeException(""))
        val result = error.mapContent { it + 1 }
        assertThat(result).isEqualTo(error)
    }

    @Test fun `mapContent loading stays loading`() {
        val loading: UCT<Int> = UCT.loading()
        val result = loading.mapContent { it + 1 }
        assertThat(result).isEqualTo(loading)
    }

    @Test fun `mapError content stays content`() {
        val uct = UCT.content(0).mapError { Throwable("") }
        assertThat(uct).isEqualTo(UCT.content(0))
    }

    @Test fun `mapError error to new error value`() {
        val mappedException = Throwable("")
        val uct = UCT.error(RuntimeException()).mapError { mappedException }
        assertThat(uct).isEqualTo(UCT.error(mappedException))
    }

    @Test fun `flatMapLoading loading to content`() {
        val uct: UCT<Int> = UCT.loading()
        val result = uct.flatMapLoading { UCT.content(0) }
        assertThat(result).isEqualTo(UCT.content(0))
    }

    @Test fun `flatMapContent content to loading`() {
        val result = 0.toUCT().flatMapContent { UCT.loading() }
        assertThat(result).isEqualTo(UCT.loading())
    }

    @Test fun `flatMapError error to loading`() {
        val result = UCT.error(Throwable()).flatMapError { UCT.loading() }
        assertThat(result).isEqualTo(UCT.loading())
    }
}
