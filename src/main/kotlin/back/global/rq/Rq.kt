package com.back.global.rq

class Rq(cmd: String) {
    val action: String
    private val paramMap = mutableMapOf<String, String>()

    init {
        val cmdBits = cmd.split("?", limit = 2)

        action = cmdBits[0].trim()

        if (cmdBits.size == 2) {
            val queryStr = cmdBits[1]
            val queryBits = queryStr.split("&")

            for (queryBit in queryBits) {
                val queryParamBtis = queryBit.split("=", limit = 2)

                if (queryParamBtis.size != 2) {
                    continue
                }

                val paramName = queryParamBtis[0].trim()
                val paramValue = queryParamBtis[1].trim()

                paramMap[paramName] = paramValue
            }
        }
    }

    private fun getParamValue(name: String): String? {
        return paramMap[name]
    }

    fun getParamValue(name: String, default: String): String {
        return getParamValue(name) ?: default
    }

    fun getParamValueAsInt(name: String, default: Int = 0): Int {
        val paramValue = getParamValue(name) ?: return default

        return try {
            paramValue.toInt()
        } catch (e: NumberFormatException) {
            default
        }
    }
}