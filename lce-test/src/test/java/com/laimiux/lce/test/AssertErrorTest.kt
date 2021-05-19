package com.laimiux.lce.test

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LCE
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import org.junit.Test

class AssertErrorTest {

    @Test
    fun `LCE assertError testSuite`() {
        runSuite(
            content = { LCE.content("") },
            error = { LCE.error(Throwable("error")) },
            loading = { LCE.loading() },
            runAssertError = { assertError() }
        )
    }

    @Test
    fun `UCE assertError testSuite`() {
        runSuite(
            content = { UCE.content("") },
            error = { UCE.error(Throwable("error")) },
            loading = { UCE.loading() },
            runAssertError = { assertError() }
        )
    }

    @Test
    fun `UCT assertError testSuite`() {
        runSuite(
            content = { UCT.content("") },
            error = { UCT.error(Throwable("error")) },
            loading = { UCT.loading() },
            runAssertError = { assertError() }
        )
    }

    @Test
    fun `CE assertError testSuite`() {
        runSuite(
            content = { CE.content("") },
            error = { CE.error(Throwable("error")) },
            runAssertError = { assertError() }
        )
    }

    @Test
    fun `CT assertError testSuite`() {
        runSuite(
            content = { CT.content("") },
            error = { CT.error(Throwable("error")) },
            runAssertError = { assertError() }
        )
    }

    private fun <Type> runSuite(
        error: () -> Type,
        content: () -> Type,
        loading: (() -> Type)? = null,
        runAssertError: Type.() -> Unit,
    ) {
        // run - error type - succeeds
        error().runAssertError()

        // run - content type - throws assertion error
        content.invoke().let {
            expectError { it.runAssertError() }
        }

        // run - loading type - throws assertion error
        loading?.invoke()?.let {
            expectError { it.runAssertError() }
        }
    }
}