package com.psmelser.durian.file.json

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.jayway.jsonpath.JsonPath
import com.psmelser.durian.file.Resource
import com.psmelser.durian.file.ResourceReadException
import com.psmelser.durian.file.json.impl.DocumentContext
import com.psmelser.jackson.json.Json
import java.io.IOException

class JsonResource : Resource {
    @Throws(IOException::class)
    constructor(filePath: String) : super(filePath) {}

    @Throws(IOException::class)
    constructor(filePath: String, delimiter: String): super(filePath, delimiter) {}

    inline fun <reified T> getJsonAsObject(): T {
        return Json.fromJson(text)
    }

    fun getAsJsonNode() : JsonNode {
        return Json.toJsonNode(text)
    }

    fun getAsObjectNode() : ObjectNode {
        return getAsJsonNode() as ObjectNode
    }

    fun jsonPath() : DocumentContext {
        return DocumentContext(JsonPath.parse(text))
    }

    companion object {

        @Throws(ResourceReadException::class)
        fun parseFile(path: String): JsonResource {
            try {
                return JsonResource(path)
            } catch (e: IOException) {
                throw ResourceReadException(e)
            }

        }
    }
}