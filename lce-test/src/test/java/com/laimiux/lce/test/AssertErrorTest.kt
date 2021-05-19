package com.laimiux.lce.test

import com.google.common.truth.Truth.assertThat
import com.laimiux.lce.LCE
import org.junit.Test

class AssertErrorTest {

    @Test fun `when LCE content then assertError throws assertion error`() {
        val error = expectError { LCE.content("moo").assertError() }
        assertThat(error).isInstanceOf(AssertionError::class.java)
    }

    @Test fun `when LCE error then assertError succeeds`() {
        LCE.error("moo").assertError()
    }

    @Test fun `when LCE loading then assertError throws assertion error`() {
        val error = expectError { LCE.loading("moo").assertError() }
        assertThat(error).isInstanceOf(AssertionError::class.java)
    }
}