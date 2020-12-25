package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LceTest {

    @Test fun `LCE fold content`() {
        val lce: LCE<Unit, Int, Throwable> = LCE.content(0)
        val result = lce.fold(
            onLoading = { "loading" },
            onError = { "error: $it" },
            onContent = { "content: $it" }
        )
        assertThat(result).isEqualTo("content: 0")
    }
}
