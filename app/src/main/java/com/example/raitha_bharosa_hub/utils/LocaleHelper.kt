package com.example.raitha_bharosa_hub.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import java.util.Locale

object LocaleHelper {

    private const val PREF_NAME = "settings"
    private const val KEY_LANG = "language"

    // SAVE + APPLY LANGUAGE
    fun setLocale(
        activity: Activity,
        language: String
    ) {

        saveLanguage(activity, language)

        val locale = Locale(language)

        Locale.setDefault(locale)

        val config = Configuration()

        config.setLocale(locale)

        activity.resources.updateConfiguration(
            config,
            activity.resources.displayMetrics
        )

        activity.recreate()
    }

    // APPLY SAVED LANGUAGE
    fun applyLanguage(
        activity: Activity
    ) {

        val language = getLanguage(activity)

        val locale = Locale(language)

        Locale.setDefault(locale)

        val config = Configuration()

        config.setLocale(locale)

        activity.resources.updateConfiguration(
            config,
            activity.resources.displayMetrics
        )
    }

    // SAVE LANGUAGE
    private fun saveLanguage(
        context: Context,
        language: String
    ) {

        val prefs: SharedPreferences =
            context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
            )

        prefs.edit()
            .putString(KEY_LANG, language)
            .apply()
    }

    // GET LANGUAGE
    fun getLanguage(
        context: Context
    ): String {

        val prefs =
            context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
            )

        return prefs.getString(
            KEY_LANG,
            "en"
        ) ?: "en"
    }
}