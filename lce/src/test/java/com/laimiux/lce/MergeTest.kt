package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MergeTest {

    @Test fun `LC loading merge returns loading`() {
        val result = LC.loading().merge(LC.content(0))
        assertThat(result).isEqualTo(LC.loading())
    }

    @Test fun `LC content returns loading when other LC is loading`() {
        val result = LC.content(0).merge(LC.loading())
        assertThat(result).isEqualTo(LC.loading())
    }

    @Test fun `LC content returns merged content when other LC is content`() {
        val result = LC.content(0).merge(LC.content(""))
        assertThat(result).isEqualTo(LC.content(Pair(0, "")))
    }

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