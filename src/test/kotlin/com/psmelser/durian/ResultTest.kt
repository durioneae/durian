package com.psmelser.durian

import org.junit.Test
import java.util.function.Consumer
import java.util.function.Function

class ResultTest

@Test
fun testResult() {
    val result = Result.error<String, RuntimeException>(RuntimeException())

    result.apply(Consumer {  }, Consumer {  })
    result.map(Function<String, String> { it }, Function { it.localizedMessage })
}
