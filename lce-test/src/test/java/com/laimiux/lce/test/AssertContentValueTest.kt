package com.laimiux.lce.test

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import org.junit.Test

class AssertContentValueTest {

    @Test
    fun `LCE assertContent testSuite`() {
        runSuite(
            content = { LCE.content(it) },
            error = { LCE.error("") },
            loading = { LCE.loading() },
            runAssertContent = { assertContent(it) }
        )
    }

    @Test
    fun `UCE assertContent testSuite`() {
        runSuite(
            content = { UCE.content(it) },
            error = { UCE.error("") },
            loading = { UCE.loading() },
            runAssertContent = { assertContent(it) }
        )
    }

    @Test
    fun `UCT assertContent testSuite`() {
        runSuite(
            content = { UCT.content(it) },
            error = { UCT.error(Throwable("")) },
            loading = { UCT.loading() },
            runAssertContent = { assertContent(it) }
        )
    }

    @Test
    fun `CE assertContent testSuite`() {
        runSuite(
            content = { CE.content(it) },
            error = { CE.error("") },
            runAssertContent = { assertContent(it) }
        )
    }

    @Test
    fun `CT assertContent testSuite`() {
        runSuite(
            content = { CT.content(it) },
            error = { CT.error(Throwable("")) },
            runAssertContent = { assertContent(it) }
        )
    }

    @Test
    fun `LC assertContent testSuite`() {
        runSuite(
            content = { LC.content(it) },
            loading = { LC.loading() },
            runAssertContent = { assertContent(it) }
        )
    }

    @Test
    fun `UC assertContent testSuite`() {
        runSuite(
            content = { UC.content(it) },
            loading = { UC.loading() },
            runAssertContent = { assertContent(it) }
        )
    }

    private fun <Type> runSuite(
        content: (String) -> Type,
        error: (() -> Type)? = null,
        loading: (() -> Type)? = null,
        runAssertContent: Type.(expected: String) -> Unit,
    ) {

        // run - content type - expected value matches
        content("value").runAssertContent("value")

        // run - content type - expected value does not match
        expectError { content("value").runAssertContent("not match") }

        // run - error type - throws assertion error
        error?.invoke()?.let {
            expectError { it.runAssertContent("value") }
        }

        // run - loading type - throws assertion error
        loading?.invoke()?.let {
            expectError { it.runAssertContent("value") }
        }
    }
}