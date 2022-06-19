package com.example.foodies_vezdecode.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodies_vezdecode.fragments.FoodMenuFragment
import com.example.foodies_vezdecode.model.Category

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val categories: MutableList<Category> = mutableListOf()

    override fun createFragment(position: Int): Fragment {
        return FoodMenuFragment(categories[position].id)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun setData(data: List<Category>) {
        categories.addAll(data)
    }
}