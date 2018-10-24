package com.psmelser.durian.file

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ResourceTest {
    @Test
    fun test() {
        assertThat(Resource.parseFile("test.txt").text).isEqualTo("This is a test")
    }

    @Test
    fun testFlat() {
        val file = Resource.parseFile("multi-line-text.txt")

        assertThat(file.flattenedText).isEqualTo("This is a testwith more than one line")
    }

    @Test
    fun testNoWhite() {
        val file = Resource.parseFile("multi-line-text.txt")

        assertThat(file.noWhitespaceText).isEqualTo("Thisisatestwithmorethanoneline")
    }

    @Test
    fun testMultiLine() {
        val file = Resource.parseFile("multi-line-text.txt")

        assertThat(file.text).isEqualTo("This is a test\nwith more than one line")
    }
}