package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MergeTest {

    @Test fun `UC loading merge returns loading`() {
        val result = UC.loading().merge(UC.content(0))
        assertThat(result).isEqualTo(UC.loading())
    }

    @Test fun `UC content returns loading when other UC is loading`() {
        val result = UC.content(0).merge(UC.loading())
        assertThat(result).isEqualTo(UC.loading())
    }

    @Test fun `UC content returns merged content when other UC is content`() {
        val result = UC.content(0).merge(UC.content(""))
        assertThat(result).isEqualTo(UC.content(Pair(0, "")))
    }
}