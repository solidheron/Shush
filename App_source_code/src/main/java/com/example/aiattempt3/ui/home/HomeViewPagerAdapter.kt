package com.example.aiattempt3.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LocalFragment()
            1 -> WorldFragment()
            2 -> GroupFragment()
            else -> WorldFragment()
        }
    }
} 