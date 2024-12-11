package com.example.aiattempt3.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.aiattempt3.R

class ProfileSettingsFragment : Fragment() {

    private var cityEdit: EditText? = null
    private var stateEdit: EditText? = null
    private var countryEdit: EditText? = null
    private var ageSpinner: Spinner? = null
    private var genderSpinner: Spinner? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupToolbar(view)
        setupViews(view)
        setupSpinners()
        setupSaveButton(view)
    }

    private fun setupToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupViews(view: View) {
        cityEdit = view.findViewById(R.id.city_edit)
        stateEdit = view.findViewById(R.id.state_edit)
        countryEdit = view.findViewById(R.id.country_edit)
        ageSpinner = view.findViewById(R.id.age_group_spinner)
        genderSpinner = view.findViewById(R.id.gender_spinner)
    }

    private fun setupSpinners() {
        context?.let { ctx ->
            // Age Groups
            val ageGroups = arrayOf(
                "Select Age Group",
                "Under 18",
                "18-24",
                "25-34",
                "35-44",
                "45-54",
                "55+"
            )
            ArrayAdapter(ctx, android.R.layout.simple_spinner_item, ageGroups).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                ageSpinner?.adapter = adapter
            }

            // Gender
            val genders = arrayOf(
                "Select Gender",
                "Male",
                "Female",
                "Non-binary",
                "Prefer not to say"
            )
            ArrayAdapter(ctx, android.R.layout.simple_spinner_item, genders).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                genderSpinner?.adapter = adapter
            }
        }
    }

    private fun setupSaveButton(view: View) {
        view.findViewById<Button>(R.id.save_button).setOnClickListener {
            saveSettings()
        }
    }

    private fun saveSettings() {
        val city = cityEdit?.text?.toString()
        val state = stateEdit?.text?.toString()
        val country = countryEdit?.text?.toString()
        val ageGroup = if (ageSpinner?.selectedItemPosition != 0) {
            ageSpinner?.selectedItem?.toString()
        } else null
        val gender = if (genderSpinner?.selectedItemPosition != 0) {
            genderSpinner?.selectedItem?.toString()
        } else null

        // TODO: Save settings to storage
        Toast.makeText(context, "Profile settings saved", Toast.LENGTH_SHORT).show()
    }
} 