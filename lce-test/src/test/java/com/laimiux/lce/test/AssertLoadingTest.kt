package com.laimiux.lce.test

import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import org.junit.Test

class AssertLoadingTest {
    @Test
    fun `LCE assertLoading testSuite`() {
        runSuite(
            content = { LCE.content("") },
            error = { LCE.error("error") },
            loading = { LCE.loading() },
            runAssertLoading = { assertLoading() }
        )
    }

    @Test
    fun `LC assertLoading testSuite`() {
        runSuite(
            content = { LC.content("content") },
            loading = { LC.loading() },
            runAssertLoading = { assertLoading() }
        )
    }

    private fun <Type> runSuite(
        loading: () -> Type,
        content: () -> Type,
        error: (() -> Type)? = null,
        runAssertLoading: Type.() -> Unit,
    ) {
        // run - loading type - succeeds
        loading().runAssertLoading()

        // run - content type - throws assertion error
        content.invoke().let {
            expectError { it.runAssertLoading() }
        }

        // run - error type - throws assertion error
        error?.invoke()?.let {
            expectError { it.runAssertLoading() }
        }
    }
}