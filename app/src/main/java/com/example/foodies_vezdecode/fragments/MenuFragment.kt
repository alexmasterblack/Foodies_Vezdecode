package com.example.foodies_vezdecode.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.foodies_vezdecode.R
import com.example.foodies_vezdecode.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MenuFragment : Fragment(R.layout.menu_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val pager = view.findViewById<ViewPager2>(R.id.viewPager)

        val adapter = ViewPagerAdapter(this)
        pager.adapter = adapter

        TabLayoutMediator(tabLayout, pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Роллы"
                }
                1 -> {
                    tab.text = "Суши"
                }
                2 -> {
                    tab.text = "Наборы"
                }
                3 -> {
                    tab.text = "Горячие блюда"
                }
                4 -> {
                    tab.text = "Супы"
                }
                5 -> {
                    tab.text = "Десерты"
                }
            }
        }.attach()
    }
}