package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ContentOrElseTest {

    @Test fun `LCE when loading contentOrElse returns else`() {
        val result = LCE.loading().contentOrElse { "other" }
        assertThat(result).isEqualTo("other")
    }

    @Test fun `UCE when loading contentOrElse returns else`() {
        val result = UCE.loading().contentOrElse { "other" }
        assertThat(result).isEqualTo("other")
    }

    @Test fun `UCT when loading contentOrElse returns else`() {
        val result = UCT.loading().contentOrElse { "other" }
        assertThat(result).isEqualTo("other")
    }

    @Test fun `CE when error contentOrElse returns else`() {
        val result = CE.error(RuntimeException()).contentOrElse { "other" }
        assertThat(result).isEqualTo("other")
    }

    @Test fun `CT when error contentOrElse returns else`() {
        val result = CT.error(RuntimeException()).contentOrElse { "other" }
        assertThat(result).isEqualTo("other")
    }

    @Test fun `LC when loading contentOrElse returns else`() {
        val result = LC.loading().contentOrElse { "other" }
        assertThat(result).isEqualTo("other")
    }

    @Test fun `UC when loading contentOrElse returns else`() {
        val result = UC.loading().contentOrElse { "other" }
        assertThat(result).isEqualTo("other")
    }
}