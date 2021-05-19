package com.laimiux.lce.test

import com.google.common.truth.Truth.assertThat
import com.laimiux.lce.LCE
import org.junit.Test

class AssertContentTest {

    @Test fun `when LCE content then assertContent succeeds`() {
        LCE.content("").assertContent()
    }

    @Test fun `when LCE error then assertContent throws assertion error`() {
        val error = expectError { LCE.error("moo").assertContent() }
        assertThat(error).isInstanceOf(AssertionError::class.java)
    }

    @Test fun `when LCE loading then assertContent throws assertion error`() {
        val error = expectError { LCE.loading("moo").assertContent() }
        assertThat(error).isInstanceOf(AssertionError::class.java)
    }
}