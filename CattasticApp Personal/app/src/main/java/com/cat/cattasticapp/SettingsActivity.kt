package com.cat.cattasticapp

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.preference.PreferenceManager
import com.google.firebase.messaging.FirebaseMessaging
import java.util.Locale

class SettingsActivity : BaseActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var themeSwitch: Switch
    private lateinit var notificationSwitch: Switch
    private var currentLanguage: String = "en" // Default language is English

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Initialize views
        themeSwitch = findViewById(R.id.switchTheme)
        notificationSwitch = findViewById(R.id.switchNotifications)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        setupThemeSwitch()
        setupNotificationSwitch()
        setLanguage()
    }

    private fun setupThemeSwitch() {
        // Set the current state of the switch based on the saved preference
        val currentTheme = sharedPreferences.getString("theme_preference", "Light")
        themeSwitch.isChecked = currentTheme == "Dark"

        // Handle switch toggles
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            val selectedTheme = if (isChecked) "Dark" else "Light"
            sharedPreferences.edit().putString("theme_preference", selectedTheme).apply()
            applyTheme(selectedTheme)
        }
    }

    private fun applyTheme(theme: String) {
        sharedPreferences.edit().putString("theme_preference", theme).apply()
        recreate() // Restart activity to apply the new theme
    }

    private fun setupNotificationSwitch() {
        // Set the current state of the switch based on the saved preference
        val notificationsEnabled = sharedPreferences.getBoolean("notifications_enabled", true)
        notificationSwitch.isChecked = notificationsEnabled

        // Handle switch toggles
        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                enableNotifications()
            } else {
                disableNotifications()
            }
            sharedPreferences.edit().putBoolean("notifications_enabled", isChecked).apply()
        }
    }

    private fun enableNotifications() {
        FirebaseMessaging.getInstance().subscribeToTopic("all")
            .addOnCompleteListener { task ->
                var msg = "Push notifications enabled"
                if (!task.isSuccessful) {
                    msg = "Failed to enable push notifications"
                }
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
    }

    private fun disableNotifications() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic("all")
            .addOnCompleteListener { task ->
                var msg = "Push notifications disabled"
                if (!task.isSuccessful) {
                    msg = "Failed to disable push notifications"
                }
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
    }

    private fun setLanguage() {
        // Get current system language
        currentLanguage = Locale.getDefault().language

        val spinner: Spinner = findViewById(R.id.spinner)

        // Set up the spinner adapter
        val languages = arrayOf("English", "Afrikaans")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Set the spinner selection based on the current language
        spinner.setSelection(if (currentLanguage == "af") 1 else 0)

        // Set a listener for the Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> setLocale("en")  // English
                    1 -> setLocale("af")  // Afrikaans
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun setLocale(lang: String) {
        if (lang != currentLanguage) { // Check if the language is different
            currentLanguage = lang // Update the current language

            val locale = Locale(lang)
            Locale.setDefault(locale)

            val config = Configuration()
            config.setLocale(locale)
            config.setLayoutDirection(locale) // To handle RTL (right-to-left) languages if needed

            // Update the app's configuration for the new locale
            resources.updateConfiguration(config, resources.displayMetrics)

            // Refresh the activity to apply the new language
            recreate()
        }
    }

}
