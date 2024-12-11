package com.example.aiattempt3.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.aiattempt3.R
import org.json.JSONObject
import java.io.IOException

class JsonDataFragment : Fragment() {
    private val TAG = "JsonDataFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_json_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(view)
        displayJsonContent(view)
    }

    private fun setupToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun displayJsonContent(view: View) {
        val jsonTextView = view.findViewById<TextView>(R.id.json_content)
        try {
            // List all files in assets to debug
            context?.assets?.list("")?.forEach {
                Log.d(TAG, "Asset file: $it")
            }

            val jsonString = context?.assets?.open("user.json")?.bufferedReader()?.use { it.readText() }
            Log.d(TAG, "Read JSON content: $jsonString")
            
            if (jsonString != null) {
                jsonTextView.text = jsonString
                Log.d(TAG, "Set text view content")
            } else {
                val errorMsg = "Error: Could not read user.json file"
                Log.e(TAG, errorMsg)
                jsonTextView.text = errorMsg
            }
        } catch (e: IOException) {
            val errorMsg = "Error reading file: ${e.localizedMessage}"
            Log.e(TAG, errorMsg, e)
            jsonTextView.text = errorMsg
        } catch (e: Exception) {
            val errorMsg = "Error processing JSON: ${e.localizedMessage}"
            Log.e(TAG, errorMsg, e)
            jsonTextView.text = errorMsg
        }
    }
} 