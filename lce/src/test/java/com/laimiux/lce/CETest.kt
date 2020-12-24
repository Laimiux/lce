package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CETest {

    @Test fun `CE fold`() {
        val ce: CE<Int, Throwable> = CE.content(0)
        val result = ce.fold(
            onError = { "error: $it" },
            onContent = { "content: $it" }
        )
        assertThat(result).isEqualTo("content: 0")
    }
}
