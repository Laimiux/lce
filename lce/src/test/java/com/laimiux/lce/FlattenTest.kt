package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FlattenTest {

    @Test fun `flatten LC with CE to LCE`() {
        val result = LC.content(CE.error(TestException(0))).flatten()
        assertThat(result).isEqualTo(LCE.error(TestException(0)))
    }

    @Test fun `flatten LC with CT to LCE`() {
        val result = LC.content(CT.error(TestException(0))).flatten()
        assertThat(result).isEqualTo(LCE.error(TestException(0)))
    }

    @Test fun `flatten UC with CE to UCE`() {
        val result = UC.content(CE.error(TestException(0))).flatten()
        assertThat(result).isEqualTo(UCE.error(TestException(0)))
    }

    @Test fun `flatten UC with CT to UCT`() {
        val result = UC.content(CT.error(TestException(0))).flatten()
        assertThat(result).isEqualTo(UCT.error(TestException(0)))
    }
}
