package com.laimiux.lce.test

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import org.junit.Test

class AssertContentTest {

    @Test fun `LCE assertContent suite`() {
        runSuite(
            content = { LCE.content("") },
            error = { LCE.error("error") },
            loading = { LCE.loading() },
            runAssertContent = { assertContent() }
        )
    }

    @Test fun `UCE assertContent suite`() {
        runSuite(
            content = { UCE.content("") },
            error = { UCE.error("error") },
            loading = { UCE.loading() },
            runAssertContent = { assertContent() }
        )
    }

    @Test fun `UCT assertContent suite`() {
        runSuite(
            content = { UCT.content("") },
            error = { UCT.error(Throwable("error")) },
            loading = { UCT.loading() },
            runAssertContent = { assertContent() }
        )
    }

    @Test fun `CE assertContent suite`() {
        runSuite(
            content = { CE.content("") },
            error = { CE.error(Throwable("error")) },
            runAssertContent = { assertContent() }
        )
    }

    @Test fun `CT assertContent suite`() {
        runSuite(
            content = { CT.content("") },
            error = { CT.error(Throwable("error")) },
            runAssertContent = { assertContent() }
        )
    }

    @Test fun `LC assertContent suite`() {
        runSuite(
            content = { LC.content("") },
            loading = { LC.loading() },
            runAssertContent = { assertContent() }
        )
    }

    @Test fun `UC assertContent suite`() {
        runSuite(
            content = { UC.content("") },
            loading = { UC.loading() },
            runAssertContent = { assertContent() }
        )
    }

    private fun <Type> runSuite(
        content: () -> Type,
        error: (() -> Type)? = null,
        loading: (() -> Type)? = null,
        runAssertContent: Type.() -> Unit,
    ) {

        // run - content type - succeeds
        content().runAssertContent()

        // run - error type - throws assertion error
        error?.invoke()?.let {
            expectError { it.runAssertContent() }
        }

        // run - loading type - throws assertion error
        loading?.invoke()?.let {
            expectError { it.runAssertContent() }
        }
    }
}