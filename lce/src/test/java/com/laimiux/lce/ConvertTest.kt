package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ConvertTest {

    @Test fun `LCE as UCT`() {
        val lce = LCE.loading()
        val result: UCT<Int> = lce.asUCT()
        assertThat(result).isEqualTo(UCT.loading())
    }

    @Test fun `LCE as CE returns null when loading`() {
        val result = LCE.loading().asCE()
        assertThat(result).isNull()
    }

    @Test fun `LCE as CE returns error when error`() {
        val result = LCE.error("").asCE()
        assertThat(result).isEqualTo(CE.error(""))
    }

    @Test fun `LCE as CE where we map loading to content`() {
        val result = LCE.loading().asCE { CE.content(0) }
        assertThat(result).isEqualTo(CE.content(0))
    }

    @Test fun `LCE as CT returns null when loading`() {
        val result = LCE.loading().asCT()
        assertThat(result).isNull()
    }

    @Test fun `UCT as LCE`() {
        val uct: UCT<Int> = UCT.content(5)
        val lce: LCE<Unit, Int, Throwable> = uct.asLCE()
        assertThat(lce).isEqualTo(LCE.content(5))
    }

    @Test fun `CE as UCT`() {
        val ce: CE<Int, Throwable> = CE.content(0)
        val uct: UCT<Int> = ce.asUCT()
        assertThat(uct).isEqualTo(UCT.content(0))
    }

    @Test fun `CE as LCE`() {
        val ce = CE.error("failure")
        val lce = ce.asLCE()
        assertThat(lce).isEqualTo(LCE.error("failure"))
    }

    @Test fun `CE as CT`() {
        val ce: CE<Int, Throwable> = CE.content(0)
        val ct: CT<Int> = ce.asCT()
        assertThat(ct).isEqualTo(CT.content(0))
    }

    @Test fun `CE as CT using throwable subclass`() {
        val ce: CE<Int, TestException> = CE.error<TestException>(TestException(""))
        val ct = ce.asCT()
        assertThat(ct).isEqualTo(CT.error(TestException("")))
    }

    @Test fun `CT as LCE`() {
        val ct: CT<Int> = CT.content(0)
        val lce: LCE<Any, Int, Throwable> = ct.asLCE()
        assertThat(lce).isEqualTo(LCE.content(0))
    }

    @Test fun `CT as UCT`() {
        val ct: CT<Int> = CT.content(0)
        val uct: UCT<Int> = ct.asUCT()
        assertThat(uct).isEqualTo(UCT.content(0))
    }

    @Test fun `CT as CE`() {
        val ct: CT<Int> = CT.content(0)
        val ce: CE<Int, Throwable> = ct.asCE()
        assertThat(ce).isEqualTo(CE.content(0))
    }

    @Test fun `LC as LCE`() {
        val initial: LC<String, String> = LC.loading("")
        val result: LCE<String, String, Nothing> = initial.asLCE()
        assertThat(result).isEqualTo(LCE.loading(""))
    }

    @Test fun `LC as UCT`() {
        val initial: LC<Unit, String> = LC.loading()
        val result: UCT<String> = initial.asUCT()
        assertThat(result).isEqualTo(UCT.loading())
    }

    @Test fun `UC as LCE`() {
        val uc: UC<Int> = UC.content(0)
        val lce: LCE<Unit, Int, String> = uc.asLCE()
        assertThat(lce).isEqualTo(LCE.content(0))
    }

    @Test fun `UC as UCT`() {
        val uc = UC.content(0)
        val uct = uc.asUCT()
        assertThat(uct).isEqualTo(UCT.content(0))
    }
}