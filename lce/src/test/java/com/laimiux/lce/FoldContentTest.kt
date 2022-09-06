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

    @Test fun `LCE foldContent provides LE in other to handle remaining cases`() {
        val result = LCE.loading().foldContent(
            onContent = { LCE.content(1) },
            onOther = { it.asLCE() }
        )

        assertThat(result).isEqualTo(LCE.loading())
    }

    @Test fun `UCE foldContent provides UE in other to handle remaining cases`() {
        val result = UCT.loading().foldContent(
            onContent = { UCE.content(1) },
            onOther = { it.asUCE() }
        )

        assertThat(result).isEqualTo(UCE.loading())
    }

    @Test fun `UCT foldContent provides UT in other to handle remaining cases`() {
        val result = UCT.loading().foldContent(
            onContent = { UCT.content(1) },
            onOther = { it.asUCT() }
        )

        assertThat(result).isEqualTo(UCT.loading())
    }
}