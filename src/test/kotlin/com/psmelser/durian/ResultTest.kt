package com.psmelser.durian

import org.junit.Test

class ResultTest

@Test
fun testResult() {
    val result = Result.error<String, RuntimeException>(RuntimeException())

    result.apply({  }, {  })
    result.map({ it }, { it.localizedMessage })
}
