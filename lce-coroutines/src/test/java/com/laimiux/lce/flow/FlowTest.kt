package com.laimiux.lce.flow

import com.google.common.truth.Truth.assertThat
import com.laimiux.lce.CT
import com.laimiux.lce.UCT
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.toList
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

    @Test
    fun `toUCT rethrows CancellationException`() = runTest {
        assertRethrowsCancellationException { it.toUCT() }
    }

    @Test
    fun `toUCE rethrows CancellationException`() = runTest {
        assertRethrowsCancellationException { it.toUCE { "not cancellation" } }
    }

    @Test
    fun `toCT rethrows CancellationException`() = runTest {
        assertRethrowsCancellationException { it.toCT() }
    }

    @Test
    fun `toCE rethrows CancellationException`() = runTest {
        assertRethrowsCancellationException { it.toCE { "not cancellation" } }
    }

    private suspend fun assertRethrowsCancellationException(
        transform: (Flow<String>) -> Flow<*>
    ) {
        val flow = transform(flow { throw CancellationException("cancelled") })
        try {
            flow.collect()
            throw AssertionError("Expected CancellationException to be thrown")
        } catch (e: CancellationException) {
            assertThat(e.message).isEqualTo("cancelled")
        }
    }

    @Test
    fun `runCT returns content on success`() = runTest {
        val result = runCT { "Success" }
        assertThat(result).isEqualTo(CT.content("Success"))
    }

    @Test
    fun `runCT returns error on exception`() = runTest {
        val exception = RuntimeException("Error")
        val result = runCT { throw exception }
        assertThat(result).isEqualTo(CT.error(exception))
    }

    @Test
    fun `runCT rethrows CancellationException`() = runTest {
        try {
            runCT { throw CancellationException("cancelled") }
            throw AssertionError("Expected CancellationException to be thrown")
        } catch (e: CancellationException) {
            assertThat(e.message).isEqualTo("cancelled")
        }
    }

    @Test
    fun `uctFlow emits loading first`() = runTest {
        val flow = uctFlow { "Content" }
        assertThat(flow.first()).isEqualTo(UCT.loading())
    }

    @Test
    fun `uctFlow emits content on success`() = runTest {
        val flow = uctFlow { "Success" }
        val emissions = flow.toList()

        assertThat(emissions).hasSize(2)
        assertThat(emissions[0]).isEqualTo(UCT.loading())
        assertThat(emissions[1]).isEqualTo(UCT.content("Success"))
    }

    @Test
    fun `uctFlow emits error on exception`() = runTest {
        val exception = RuntimeException("Error")
        val flow = uctFlow<String> { throw exception }
        val emissions = flow.toList()

        assertThat(emissions).hasSize(2)
        assertThat(emissions[0]).isEqualTo(UCT.loading())
        assertThat(emissions[1]).isEqualTo(UCT.error(exception))
    }

    @Test
    fun `uctFlow rethrows CancellationException`() = runTest {
        val flow = uctFlow<String> { throw CancellationException("cancelled") }
        try {
            flow.collect()
            throw AssertionError("Expected CancellationException to be thrown")
        } catch (e: CancellationException) {
            assertThat(e.message).isEqualTo("cancelled")
        }
    }
}
