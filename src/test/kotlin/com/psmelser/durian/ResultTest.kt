package com.psmelser.durian

import com.psmelser.durian.Result.Companion.error
import com.psmelser.durian.Result.Companion.value
import org.junit.Assert.fail
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ResultTest {

    @Test
    fun `Test apply with error`() {
        val result = error()

        result.apply({ assertThat(it).isNull() }, { assertThat(it).isNotNull() })
        result.apply({ assertThat(it).isNull() }, { assertThat(it.message).isEqualTo("hello") })
    }

    @Test
    fun `Test apply with value`() {
        val result = value()

        result.apply({ assertThat(it).isNotNull() }, { assertThat(it).isNull() })
        result.apply({ assertThat(it).isEqualTo("hello") }, { assertThat(it).isNull() })
    }

    @Test
    fun `Test map with value`() {
        val result = value()

        val map = result.map({ it }, { "${it.localizedMessage} ahh" })
        assertThat(map).isNotNull()
        assertThat(map).isEqualTo("hello")
        assertThat(result.mapValue { it }.value).isEqualTo("hello")
    }

    @Test
    fun `Test map with error`() {
        val result = error()

        val map = result.map({ it }, { "${it.localizedMessage} ahh" })
        assertThat(map).isNotNull()
        assertThat(map).isEqualTo("hello ahh")

        assertThat("${result.mapValue { it }.error!!.message} ahh").isEqualTo("hello ahh")
    }

    @Test
    fun `Test mapError`() {
        val result = error()

        val map = result.mapError { "${it.localizedMessage} fail" }
        assertThat(map).isNotNull
        assertThat(map.error).isEqualTo("hello fail")
    }

    @Test
    fun `Test hasValue`() {
        val result = value()

        assertThat(result.hasValue()).isTrue()
        assertThat(result.hasError()).isFalse()
    }

    @Test
    fun `Test hasError`() {
        val result = error()

        assertThat(result.hasValue()).isFalse()
        assertThat(result.hasError()).isTrue()
    }

    @Test
    fun `Test ifValue`() {
        val result = value()

        result.ifValue { assertThat(result.value).isEqualTo("hello") }
        result.ifError { fail("Should not have reached this closure") }
    }

    @Test
    fun `Test ifError`() {
        val result = error()

        result.ifError { assertThat(result.error?.localizedMessage).isEqualTo("hello") }
        result.ifValue { fail("Should not have reached this closure") }
    }

    private fun error() = error<String, RuntimeException>(RuntimeException("hello"))

    private fun value() = value<String, RuntimeException>("hello")
}
