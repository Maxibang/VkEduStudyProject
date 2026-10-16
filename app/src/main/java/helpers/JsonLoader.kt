package com.example.vkeducation

import android.content.Context
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable

@Serializable
data class AppDetailsModel(
    val title: String,
    val developer: String,
    val rating: String,
    val downloads: String,
    val description: String,
    val iconName: String,
    val screenshots: List<String>,
    val size: String,
    val age: String,
    val type: String
)

class JSONLoader(private val context: Context) {
    fun loadAppDetails(): List<AppDetailsModel> {
        val jsonText = context.assets.open("apps_descriptions.json")
            .bufferedReader()
            .use { it.readText() }
        return Json.decodeFromString<List<AppDetailsModel>>(jsonText)
    }
}