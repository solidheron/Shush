package com.example.aiattempt3.utils

import android.content.Context
import org.json.JSONObject
import java.io.IOException

class JsonComparator(private val context: Context) {

    fun compareJsonFiles(): Boolean {
        try {
            val userJson = readJsonFromAssets("user.json")
            val referenceJson = readJsonFromAssets("reference.json")

            if (userJson == null || referenceJson == null) {
                return false
            }

            return compareJsonObjects(JSONObject(userJson), JSONObject(referenceJson))
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    private fun readJsonFromAssets(fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun compareJsonObjects(json1: JSONObject, json2: JSONObject): Boolean {
        if (json1.length() != json2.length()) {
            return false
        }

        val keys1 = json1.keys()
        while (keys1.hasNext()) {
            val key = keys1.next()
            if (!json2.has(key)) {
                return false
            }

            val value1 = json1.get(key)
            val value2 = json2.get(key)

            if (value1 is JSONObject && value2 is JSONObject) {
                if (!compareJsonObjects(value1, value2)) {
                    return false
                }
            } else if (value1.toString() != value2.toString()) {
                return false
            }
        }
        return true
    }
} 