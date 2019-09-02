package com.example.nightmodeexample

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isNightMode() || isAppCompatNightMode()) {
            message.text = "Welcome to night mode"
            button_switch_mode.text = "Switch to day mode"
        } else {
            message.text = "Welcome to day mode"
            button_switch_mode.text = "Switch to night mode"
        }

        button_switch_mode.setOnClickListener {
            val button = it as Button
            if (isNightMode() || isAppCompatNightMode()) {
                turnNightModeOn(false)
                button.text = "Switching to day mode..."
            } else {
                turnNightModeOn()
                button.text = "Switching to night mode..."
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val currentUiMode = newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val message = when (currentUiMode) {
            Configuration.UI_MODE_NIGHT_NO -> "Changed to day mode"
            Configuration.UI_MODE_NIGHT_YES -> "Changed to night mode"
            else -> "Unknown"
        }
        Log.d("TESTTEST", message)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun isAppCompatNightMode(): Boolean {
        val uiMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        Log.d("isAppCompatNightMode", "$uiMode")
        return uiMode
    }

    private fun isNightMode(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val uiMode = currentNightMode == Configuration.UI_MODE_NIGHT_YES
        Log.d("isNightMode", "$uiMode")
        return uiMode
    }

    private fun turnNightModeOn(turnOn: Boolean = true){
        if (turnOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        delegate.applyDayNight()
        recreate()
    }
}
