package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CombineTest {

    @Test
    fun `UCT combines with UCT into a Pair`() {
        val first = UCT.content("one")
        val second = UCT.content(1)
        val result = first.combine(second)
        assertThat(result).isEqualTo(UCT.content(Pair("one", 1)))
    }
}