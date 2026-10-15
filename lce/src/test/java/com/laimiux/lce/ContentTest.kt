package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ContentTest {

    @Test
    fun `content Unit uses singleton across content types`() {
        val singleton = Type.Content.UnitType

        assertThat(LCE.content(Unit)).isSameInstanceAs(singleton)
        assertThat(UCE.content(Unit)).isSameInstanceAs(singleton)
        assertThat(UCT.content(Unit)).isSameInstanceAs(singleton)
        assertThat(CE.content(Unit)).isSameInstanceAs(singleton)
        assertThat(CT.content(Unit)).isSameInstanceAs(singleton)
        assertThat(LC.content(Unit)).isSameInstanceAs(singleton)
        assertThat(UC.content(Unit)).isSameInstanceAs(singleton)
    }
}
