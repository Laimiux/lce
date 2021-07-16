package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MergeTest {
    companion object {
        val ERROR = TestException("error")
    }

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

    @Test fun `UCT error returns error if other UCT is loading`() {
        val result = UCT.error(ERROR).merge(UCT.loading())
        assertThat(result).isEqualTo(UCT.error(ERROR))
    }

    @Test fun `UCT error returns error if other UCT has content`() {
        val result = UCT.error(ERROR).merge(UCT.content(""))
        assertThat(result).isEqualTo(UCT.error(ERROR))
    }

    @Test fun `UCT content return merged content when other UCT has content`() {
        val result = UCT.content(0).merge(UCT.content(""))
        assertThat(result).isEqualTo(UCT.content(Pair(0, "")))
    }

    @Test fun `UCE error returns error if other UCE is loading`() {
        val result = UCE.error(ERROR).merge(UCE.loading())
        assertThat(result).isEqualTo(UCE.error(ERROR))
    }

    @Test fun `UCE error returns error if other UCE has content`() {
        val result = UCE.error(ERROR).merge(UCE.content(""))
        assertThat(result).isEqualTo(UCE.error(ERROR))
    }

    @Test fun `UCE content return merged content when other UCE has content`() {
        val result = UCE.content(0).merge(UCE.content(""))
        assertThat(result).isEqualTo(UCE.content(Pair(0, "")))
    }
}