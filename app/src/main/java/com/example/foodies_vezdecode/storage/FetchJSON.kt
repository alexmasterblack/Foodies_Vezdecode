package com.example.foodies_vezdecode.storage

import android.content.Context
import com.example.foodies_vezdecode.model.Category
import com.example.foodies_vezdecode.model.Product
import com.example.foodies_vezdecode.model.Tag
import com.google.gson.Gson
import java.io.InputStream

class FetchJSON(private val context: Context) {

    private fun readJSONFromAsset(pathJson: String): String? {
        val json: String?
        try {
            val inputStream: InputStream = context.assets.open(pathJson)
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun parseCategories(pathJson: String): List<Category> {
        return Gson().fromJson(readJSONFromAsset(pathJson), Array<Category>::class.java).toList()
    }

    fun parseProducts(pathJson: String): List<Product> {
        return Gson().fromJson(readJSONFromAsset(pathJson), Array<Product>::class.java).toList()
    }

    fun parseTags(pathJson: String): List<Tag> {
        return Gson().fromJson(readJSONFromAsset(pathJson), Array<Tag>::class.java).toList()
    }
}