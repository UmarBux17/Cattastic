package com.cat.cattasticapp

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager

open class BaseActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        loadThemeFromPreferences() // Load the theme before calling super.onCreate()
        super.onCreate(savedInstanceState)
    }

    private fun loadThemeFromPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val theme = sharedPreferences.getString("theme_preference", "Light")
        when (theme) {
            "Light" -> setTheme(R.style.Theme_App_Light)
            "Dark" -> setTheme(R.style.Theme_App_Dark)
        }
    }
}
