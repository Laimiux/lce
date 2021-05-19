package com.laimiux.lce.test

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import org.junit.Test
import java.io.IOException

class AssertErrorValueTest {

    @Test
    fun `LCE assertError testSuite`() {
        runSuite(
            content = { LCE.content("") },
            error = { LCE.error(it) },
            loading = { LCE.loading() },
            runAssertError = { assertError(it) }
        )
    }

    @Test
    fun `UCE assertError testSuite`() {
        runSuite(
            content = { UCE.content("") },
            error = { UCE.error(it) },
            loading = { UCE.loading() },
            runAssertError = { assertError(it) }
        )
    }

    @Test
    fun `UCT assertError testSuite`() {
        runSuite(
            content = { UCT.content("") },
            error = { UCT.error(it) },
            loading = { UCT.loading() },
            runAssertError = { assertError(it) }
        )
    }

    @Test
    fun `CE assertError testSuite`() {
        runSuite(
            content = { CE.content("") },
            error = { CE.error(it) },
            runAssertError = { assertError(it) }
        )
    }

    @Test
    fun `CT assertError testSuite`() {
        runSuite(
            content = { CT.content("") },
            error = { CT.error(it) },
            runAssertError = { assertError(it) }
        )
    }

    private fun <Type> runSuite(
        error: (Throwable) -> Type,
        content: () -> Type,
        loading: (() -> Type)? = null,
        runAssertError: Type.(expected: Throwable) -> Unit,
    ) {

        val expectedError = IOException("input/output")

        // run - error type - expected value matches
        error(expectedError).runAssertError(expectedError)

        // run - error type - expected value does not match
        expectError { error(expectedError).runAssertError(Throwable("not match")) }

        // run - content type - throws assertion error
        content.invoke().let {
            expectError { it.runAssertError(expectedError) }
        }

        // run - loading type - throws assertion error
        loading?.invoke()?.let {
            expectError { it.runAssertError(expectedError) }
        }
    }
}