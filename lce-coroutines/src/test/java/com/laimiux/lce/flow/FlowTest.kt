package com.laimiux.lce.flow

import com.google.common.truth.Truth.assertThat
import com.laimiux.lce.CT
import com.laimiux.lce.UCT
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FlowTest {

    @Test
    fun `loading from toUCT`() = runTest {
        val flow = flow<String> {
            // emits nothing
        }.toUCT()

        assertThat(flow.first()).isEqualTo(UCT.loading())
    }

    @Test
    fun `content from toUCT`() = runTest {
        val flow = flow {
            emit("Content")
        }.toUCT()

        assertThat(flow.last()).isEqualTo(CT.content("Content"))
    }

    @Test
    fun `toUCT then mapContent`() = runTest {
        val flow = flow {
            emit("Content")
        }
            .toUCT()
            .mapContent { "$it mapped" }

        assertThat(flow.last()).isEqualTo(CT.content("Content mapped"))
    }

    @Test
    fun `toLc then flatMapContent`() = runTest {
        val flow = flow {
            emit("Content")
        }
            .toLC()
            .flatMapLatestContent {
                flowOf("$it flatMapped").toLC()
            }

        assertThat(flow.last()).isEqualTo(CT.content("Content flatMapped"))
    }

    @Test
    fun `error from toUCT`() = runTest {
        val exception = RuntimeException("Some error")
        val flow = flow<String> {
            throw exception
        }.toUCT()

        assertThat(flow.last()).isEqualTo(UCT.error(exception))
    }

}