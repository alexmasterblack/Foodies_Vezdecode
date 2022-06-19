package com.example.foodies_vezdecode.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodies_vezdecode.fragments.FoodMenuFragment
import com.example.foodies_vezdecode.model.Category
import com.example.foodies_vezdecode.model.Tag

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val categories: MutableList<Category> = mutableListOf()
    private val selectedTags: MutableList<Tag> = mutableListOf()

    override fun createFragment(position: Int): Fragment {
        return FoodMenuFragment(categories[position].id)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun setData(data: List<Category>) {
        categories.addAll(data)
    }

    fun setTags(tag: List<Tag>) {
        selectedTags.addAll(tag)
    }
}