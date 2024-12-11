package com.example.aiattempt3.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.aiattempt3.R

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        
        // Basic setup of the view
        val textView = root.findViewById<TextView>(R.id.text_dashboard)
        textView?.text = "Dashboard"
        
        return root
    }
}