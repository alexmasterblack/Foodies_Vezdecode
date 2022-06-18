package com.example.foodies_vezdecode.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodies_vezdecode.fragments.FoodMenuFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FoodMenuFragment()
            }
            1 -> {
                FoodMenuFragment()
            }
            2 -> {
                FoodMenuFragment()
            }
            3 -> {
                FoodMenuFragment()
            }
            4 -> {
                FoodMenuFragment()
            }
            5 -> {
                FoodMenuFragment()
            }
            else -> {
                Fragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return 6
    }

}