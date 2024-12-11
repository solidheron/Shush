package com.example.aiattempt3

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aiattempt3.databinding.ActivityMainBinding
import com.example.aiattempt3.utils.JsonComparator
import com.example.aiattempt3.utils.PreferencesManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferencesManager = PreferencesManager(this)

        try {
            val navView: BottomNavigationView = binding.navView
            val navController = findNavController(R.id.nav_host_fragment_activity_main)
            
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.navigation_home) {
                    supportActionBar?.hide()
                } else {
                    supportActionBar?.show()
                }
            }

            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_home,
                    R.id.navigation_chat,
                    R.id.navigation_notifications,
                    R.id.navigation_settings
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)

            checkJsonFiles()
        } catch (e: Exception) {
            Log.e("MainActivity", "Error during onCreate", e)
        }
    }

    private fun checkJsonFiles() {
        try {
            val files = assets.list("")
            val hasUserJson = files?.contains("user.json") ?: false
            val hasReferenceJson = files?.contains("reference.json") ?: false

            if (!hasUserJson || !hasReferenceJson) {
                val message = when {
                    !hasUserJson && !hasReferenceJson -> "Both JSON files are missing"
                    !hasUserJson -> "user.json is missing"
                    else -> "reference.json is missing"
                }
                preferencesManager.saveJsonMatchStatus(false)
                showJsonDialog("Missing Files", message)
                return
            }

            val jsonComparator = JsonComparator(this)
            val comparisonResult = jsonComparator.compareJsonFiles()
            
            preferencesManager.saveJsonMatchStatus(comparisonResult)
            
            val message = if (comparisonResult) {
                "JSON files match the expected format"
            } else {
                "Warning: JSON files do not match the expected format"
            }
            
            showJsonDialog("JSON Verification", message)

        } catch (e: Exception) {
            Log.e("MainActivity", "Error checking JSON files", e)
            preferencesManager.saveJsonMatchStatus(false)
            showJsonDialog("Error", "Failed to check JSON files: ${e.localizedMessage}")
        }
    }

    private fun showJsonDialog(title: String, message: String) {
        try {
            MaterialAlertDialogBuilder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        } catch (e: Exception) {
            Log.e("MainActivity", "Error showing dialog", e)
        }
    }
}