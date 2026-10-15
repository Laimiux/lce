package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UCTContentUnitTest {

    @Test
    fun `content Unit uses singleton data object`() {
        val content = UCT.content(Unit)

        assertThat(content).isSameInstanceAs(Type.Content.UnitType)
        assertThat(content.isContent()).isTrue()
        assertThat(content.contentOrNull()).isEqualTo(Unit)
        assertThat(content.asLceType()).isSameInstanceAs(Type.Content.UnitType)
    }
}
