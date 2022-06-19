package com.example.foodies_vezdecode.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodies_vezdecode.R
import com.example.foodies_vezdecode.adapter.RecyclerViewAdapter
import com.example.foodies_vezdecode.model.Product
import com.example.foodies_vezdecode.model.Tag
import com.example.foodies_vezdecode.storage.FetchJSON
import com.example.foodies_vezdecode.storage.SharedPref

class FoodMenuFragment(private val categoryId: Int) : Fragment(R.layout.food_menu_fragment) {

    private val productsJsonPath = "Products.json"
    private val products: MutableList<Product> = mutableListOf()

    private val tagsJsonPath = "Tags.json"
    private val tags: MutableList<Tag> = mutableListOf()

    private val adapter = RecyclerViewAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fetchJSON = FetchJSON(requireContext())
        products.clear()
        products.addAll(fetchJSON.parseProducts(productsJsonPath))
        tags.addAll(fetchJSON.parseTags(tagsJsonPath))

        adapter.setData(products.filter { it.categoryId == categoryId })

        view.findViewById<RecyclerView>(R.id.foodCards).apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = this@FoodMenuFragment.adapter
        }
    }
}