package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FoldLoadingTest {

    @Test
    fun `LCE loading foldLoading to content`() {
        val result = LCE.loading().foldLoading(
            onLoading = { CE.content(0) },
            onOther = { it }
        )

        assertThat(result).isEqualTo(CE.content(0))
    }

    @Test
    fun `UCE loading foldLoading to content`() {
        val result = UCE.loading().foldLoading(
            onLoading = { CE.content(0) },
            onOther = { it }
        )

        assertThat(result).isEqualTo(CE.content(0))
    }

    @Test
    fun `UCT loading foldLoading to content`() {
        val result = UCT.loading().foldLoading(
            onLoading = { CT.content(0) },
            onOther = { it }
        )

        assertThat(result).isEqualTo(CT.content(0))
    }
}