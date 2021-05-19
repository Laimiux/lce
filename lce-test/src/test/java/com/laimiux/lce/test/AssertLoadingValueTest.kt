package com.laimiux.lce.test

import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import org.junit.Test

class AssertLoadingValueTest {

    @Test
    fun `LCE assertLoading testSuite`() {
        runSuite(
            content = { LCE.content("") },
            error = { LCE.error("error") },
            loading = { LCE.loading(it) },
            runAssertLoading = { assertLoading(it) }
        )
    }

    @Test
    fun `LC assertLoading testSuite`() {
        runSuite(
            content = { LC.content("content") },
            loading = { LC.loading(it) },
            runAssertLoading = { assertLoading(it) }
        )
    }

    private fun <Type> runSuite(
        loading: (String) -> Type,
        content: () -> Type,
        error: (() -> Type)? = null,
        runAssertLoading: Type.(expected: String) -> Unit,
    ) {

        val expectedValue = "loading value"

        // run - loading type - expected value matches
        loading(expectedValue).runAssertLoading(expectedValue)

        // run - loading type - expected value does not match
        expectError { loading(expectedValue).runAssertLoading("not match") }

        // run - content type - throws assertion error
        content.invoke().let {
            expectError { it.runAssertLoading(expectedValue) }
        }

        // run - error type - throws assertion error
        error?.invoke()?.let {
            expectError { it.runAssertLoading(expectedValue) }
        }
    }
}