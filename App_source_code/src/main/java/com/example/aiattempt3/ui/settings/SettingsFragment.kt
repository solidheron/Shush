package com.example.aiattempt3.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.aiattempt3.R
import androidx.navigation.fragment.findNavController
import com.example.aiattempt3.utils.PreferencesManager

class SettingsFragment : Fragment() {
    private lateinit var preferencesManager: PreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferencesManager = PreferencesManager(requireContext())
        
        setupClickListeners(view)
        updateJsonStatus(view)
    }

    private fun setupClickListeners(view: View) {
        // Profile Settings
        view.findViewById<View>(R.id.profile_settings_button).setOnClickListener {
            findNavController().navigate(R.id.navigation_profile_settings)
        }

        // About
        view.findViewById<View>(R.id.about_button).setOnClickListener {
            findNavController().navigate(R.id.navigation_about)
        }

        // JSON Data
        view.findViewById<View>(R.id.json_button).setOnClickListener {
            findNavController().navigate(R.id.navigation_json_data)
        }
    }

    private fun updateJsonStatus(view: View) {
        val jsonStatusText = view.findViewById<TextView>(R.id.json_status)
        val matches = preferencesManager.getJsonMatchStatus()
        jsonStatusText.text = if (matches) {
            "JSON Status: Valid ✓"
        } else {
            "JSON Status: Invalid ✗"
        }
    }

    private fun showJsonDialog() {
        val sampleJson = """
            {
                "user_id": "12345",
                "username": "example_user",
                "email": "user@example.com",
                "join_date": "2024-01-01",
                "preferences": {
                    "theme": "light",
                    "notifications_enabled": true,
                    "language": "en"
                }
            }
        """.trimIndent()

        AlertDialog.Builder(requireContext())
            .setTitle("User JSON Data")
            .setMessage(sampleJson)
            .setPositiveButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
} 