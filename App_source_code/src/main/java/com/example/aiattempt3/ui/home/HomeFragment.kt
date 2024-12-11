package com.example.aiattempt3.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aiattempt3.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.viewpager2.widget.ViewPager2

class HomeFragment : Fragment() {

    private var viewPager: ViewPager2? = null
    private var tabLayout: TabLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.view_pager)
        tabLayout = view.findViewById(R.id.tabs)

        viewPager?.adapter = HomeViewPagerAdapter(this)

        TabLayoutMediator(tabLayout!!, viewPager!!) { tab, position ->
            tab.text = when (position) {
                0 -> "Local"
                1 -> "World"
                2 -> "Group"
                else -> null
            }
        }.attach()

        // Set World tab as default (position 1)
        viewPager?.setCurrentItem(1, false)
    }
}