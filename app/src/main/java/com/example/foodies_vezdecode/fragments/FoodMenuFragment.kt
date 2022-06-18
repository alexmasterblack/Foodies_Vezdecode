package com.example.foodies_vezdecode.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodies_vezdecode.R
import com.example.foodies_vezdecode.adapter.RecyclerViewAdapter

class FoodMenuFragment : Fragment(R.layout.food_menu_fragment) {

    private val adapter = RecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.foodCards).apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = this@FoodMenuFragment.adapter
        }
    }
}