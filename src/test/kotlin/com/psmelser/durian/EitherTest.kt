package com.psmelser.durian

import com.psmelser.durian.Either.Companion.left
import com.psmelser.durian.Either.Companion.right
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.junit.Assert
import org.junit.Test

class EitherTest {
    @Test
    fun `When map is called with left value then value is result of left mapping closure`() {
        val either = left<String, String>("left")

        assertThat(either.map({"$it:OK"}, {"$it:BAD"})).isEqualTo("left:OK")
    }

    @Test
    fun `When apply is called with left value then only the left closure is applied`() {
        val either = left<String, String>("left")

        either.apply({ assertThat(it).isEqualTo("left") }, { Assert.fail() })
    }

    @Test
    fun `When ifLeft is called with left value then closure is applied`() {
        val either = left<String, String>("left")

        either.ifLeft { assertThat(it).isEqualTo("left") }
    }

    @Test
    fun `When ifRight is called with left value then closure is not applied`() {
        val either = left<String, String>("left")

        either.ifRight { Assert.fail() }
    }

    @Test
    fun `When map is called with right value then only right mapping closure is applied`() {
        val right = right<String, String>("right").map({"$it:BAD"}, {"$it:OK"})

        assertThat(right).isEqualTo("right:OK")
    }

    @Test
    fun `When apply is called with right value then only the right closure is applied`() {
        val either = right<String, String>("right")

        either.apply({ Assert.fail() }, { assertThat(it).isEqualTo("right") })
    }

    @Test
    fun `When ifRight is called with right value then closure is applied`() {
        val either = right<String, String>("right")

        either.ifRight { assertThat(it).isEqualTo("right") }
    }

    @Test
    fun `When ifLeft is called with right value then closure is not applied`() {
        val either = right<String, String>("right")

        either.ifLeft { Assert.fail() }
    }

    @Test
    fun `When isLeft is called with left value then result is true`() {
        val left = left<String, String>("hey")
        SoftAssertions.assertSoftly {
            it.assertThat(left.isLeft()).isTrue
            it.assertThat(left.isRight()).isFalse
        }
    }

    @Test
    fun `When isRight is called with right value then result is true`() {
        val left = right<String, String>("hey")
        SoftAssertions.assertSoftly {
            it.assertThat(left.isLeft()).isFalse
            it.assertThat(left.isRight()).isTrue
        }
    }

    @Test
    fun `When mapLeft is called with a left value then mapping is applied`() {
        val left = left<String, String>("hello").mapLeft { "$it:OK" }

        assertThat(left).isEqualTo("hello:OK")
    }

    @Test
    fun `When mapRight is called with a left value then result is null`() {
        val left = left<String, String>("hello").mapRight { "$it:OK" }

        assertThat(left).isNull()
    }

    @Test
    fun `When mapRight is called with a right value then mapping is applied`() {
        val left = right<String, String>("hello").mapRight { "$it:OK" }

        assertThat(left).isEqualTo("hello:OK")
    }

    @Test
    fun `When mapLeft is called with a right value then result is null`() {
        val left = right<String, String>("hello").mapLeft { "$it:OK" }

        assertThat(left).isNull()
    }
}