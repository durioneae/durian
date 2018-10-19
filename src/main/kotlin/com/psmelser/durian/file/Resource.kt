package com.psmelser.durian.file

import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.util.Scanner

class Resource : AutoCloseable {
    private val filePath: String
    private var reader: BufferedReader? = null
    private lateinit var scan: Scanner

    val text: String
        get() {
            if (!scan.hasNext()) {
                initialize(filePath)
            }
            val content = StringBuilder()
            while (scan.hasNext()) {
                content.append(scan.next())
            }
            return content.toString()
        }

    val flattenedText: String?
        get() {
            val content = StringBuilder()
            while (scan.hasNext()) {
                content.append(scan.next())
            }

            return content.toString().replace("\\n".toRegex(), "")

        }

    val noWhitespaceText: String?
        get() {
            val content = StringBuilder()
            while (scan.hasNext()) {
                content.append(scan.next())
            }

            return content.toString().replace("\\s".toRegex(), "")
        }

    @Throws(IOException::class)
    constructor(filePath: String) {
        this.filePath = filePath
        initialize(filePath)
    }

    @Throws(IOException::class)
    constructor(filePath: String, delimiter: String) {
        this.filePath = filePath
        val f = Resource::class.java.classLoader.getResource(filePath)
        reader = BufferedReader(
                InputStreamReader(f!!.openStream()))
        scan = Scanner(reader!!)
        scan.useDelimiter(delimiter)
    }

    private fun initialize(filePath: String) {
        val f = Resource::class.java.classLoader.getResource(filePath)
        reader = BufferedReader(
                InputStreamReader(f!!.openStream()))
        scan = Scanner(reader!!)
        scan.useDelimiter("\\r\\n")
    }


    @Throws(IOException::class)
    override fun close() {
        reader!!.close()
    }

    companion object {

        @Throws(ResourceReadException::class)
        fun parseFile(path: String): Resource {
            try {
                return Resource(path)
            } catch (e: IOException) {
                throw ResourceReadException(e)
            }

        }

        fun saveToNewFile(filename: String, text: String) {
            val file = File(filename)
            file.createNewFile()
            file.writeText(text)
        }
    }
}
