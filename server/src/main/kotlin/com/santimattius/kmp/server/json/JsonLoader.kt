package com.santimattius.kmp.server.json

import java.io.InputStreamReader

class JsonLoader {

    fun load(file: String): String {
        val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(file))
        val jsonStr = loader.readText()
        loader.close()
        return jsonStr
    }
}