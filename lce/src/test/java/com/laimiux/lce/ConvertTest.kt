package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ConvertTest {

    @Test fun `lce as uct`() {
        val lce: LCE<Unit, Int, Throwable> = LCE.loading()
        val result: UCT<Int> = lce.asUCT()
        assertThat(result).isEqualTo(UCT.loading())
    }

    @Test fun `uct as lce`() {
        val uct: UCT<Int> = UCT.content(5)
        val lce: LCE<Unit, Int, Throwable> = uct.asLCE()
        assertThat(lce).isEqualTo(LCE.content(5))
    }

    @Test fun `ce to uct`() {
        val ce: CE<Int, Throwable> = CE.content(0)
        val uct: UCT<Int> = ce.asUCT()
        assertThat(uct).isEqualTo(UCT.content(0))
    }

    @Test fun `ce to lce`() {
        val ce: CE<Int, String> = CE.error("failure")
        val lce: LCE<Unit, Int, String> = ce.asLCE()
        assertThat(lce).isEqualTo(LCE.error("failure"))
    }

    @Test fun `ce to ct`() {
        val ce: CE<Int, Throwable> = CE.content(0)
        val ct: CT<Int> = ce.asCT()
        assertThat(ct).isEqualTo(CT.content(0))
    }

    @Test fun `ct as lce`() {
        val ct: CT<Int> = CT.content(0)
        val lce: LCE<Any, Int, Throwable> = ct.asLCE()
        assertThat(lce).isEqualTo(LCE.content(0))
    }

    @Test fun `ct as uct`() {
        val ct: CT<Int> = CT.content(0)
        val uct: UCT<Int> = ct.asUCT()
        assertThat(uct).isEqualTo(UCT.content(0))
    }

    @Test fun `ct as ce`() {
        val ct: CT<Int> = CT.content(0)
        val ce: CE<Int, Throwable> = ct.asCE()
        assertThat(ce).isEqualTo(CE.content(0))
    }

    @Test fun `uc to lce`() {
        val uc: UC<Int> = UC.content(0)
        val lce: LCE<Unit, Int, String> = uc.asLCE()
        assertThat(lce).isEqualTo(LCE.content(0))
    }

    @Test fun `uc to uct`() {
        val uc: UC<Int> = UC.content(0)
        val uct: UCT<Int> = uc.asUCT()
        assertThat(uct).isEqualTo(UCT.content(0))
    }
}