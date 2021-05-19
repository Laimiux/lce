package com.laimiux.lce.test

import com.google.common.truth.Truth.assertThat
import com.laimiux.lce.LCE
import org.junit.Test

class AssertLoadingTest {

    @Test fun `when LCE loading then assertLoading succeeds`() {
        LCE.loading("").assertLoading()
    }

    @Test fun `when LCE content then assertLoading throws assertion error`() {
        val error = expectError { LCE.content("moo").assertLoading() }
        assertThat(error).isInstanceOf(AssertionError::class.java)
    }

    @Test fun `when LCE error then assertLoading throws assertion error`() {
        val error = expectError { LCE.error("moo").assertLoading() }
        assertThat(error).isInstanceOf(AssertionError::class.java)
    }
}