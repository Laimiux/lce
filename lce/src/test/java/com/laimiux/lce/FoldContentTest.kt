package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FoldContentTest {

    @Test fun `LCE foldContent`() {
        val result = LCE.content(0).foldContent(
            onContent = { "success" },
            onOther = { "other" }
        )
        assertThat(result).isEqualTo("success")
    }

    @Test fun `UCE foldContent`() {
        val result = UCE.content(0).foldContent(
            onContent = { "success" },
            onOther = { "other" }
        )
        assertThat(result).isEqualTo("success")
    }

    @Test fun `UCT foldContent`() {
        val result = UCT.content(0).foldContent(
            onContent = { "success" },
            onOther = { "other" }
        )
        assertThat(result).isEqualTo("success")
    }
}