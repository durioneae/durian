package com.psmelser.durian.file.json.impl

import com.jayway.jsonpath.DocumentContext
import com.psmelser.jackson.json.Json

class DocumentContext (private val context: DocumentContext) {
    fun set(path: String, newValue: Any): com.psmelser.durian.file.json.impl.DocumentContext{
        context.set(path, newValue)
        return this
    }

    fun jsonString(): String {
        return context.jsonString()
    }

    inline fun <reified T> json(): T {
        val jsonString = jsonString()
        return Json.fromJson(jsonString)
    }
}