package com.example.foodies_vezdecode.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.foodies_vezdecode.R
import com.example.foodies_vezdecode.adapter.ViewPagerAdapter
import com.example.foodies_vezdecode.model.Category
import com.example.foodies_vezdecode.model.Tag
import com.example.foodies_vezdecode.storage.FetchJSON
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MenuFragment : Fragment(R.layout.menu_fragment) {

    private val categoriesJsonPath = "Categories.json"
    private val categories: MutableList<Category> = mutableListOf()

    private val tagsJsonPath = "Tags.json"
    private val tags: MutableList<Tag> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fetchJSON = FetchJSON(requireContext())
        categories.addAll(fetchJSON.parseCategories(categoriesJsonPath))
        tags.addAll(fetchJSON.parseTags(tagsJsonPath))

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val pager = view.findViewById<ViewPager2>(R.id.viewPager)

        val adapter = ViewPagerAdapter(this)
        adapter.setData(categories)
        pager.adapter = adapter

        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = categories[position].name
        }.attach()

        println(tags)

        view.findViewById<FloatingActionButton>(R.id.filterToolbar).setOnClickListener{
            val dialog = BottomSheetDialog(requireContext())

            val viewBottom = layoutInflater.inflate(R.layout.bottom_sheet, null)

            viewBottom.findViewById<TextView>(R.id.veggy).text = tags[1].name
            viewBottom.findViewById<TextView>(R.id.spicy).text = tags[4].name
            viewBottom.findViewById<TextView>(R.id.discount).text = "Со скидкой"

            viewBottom.findViewById<MaterialButton>(R.id.readyButton).setOnClickListener {
                dialog.dismiss()
            }

            dialog.setCancelable(true)
            dialog.setContentView(viewBottom)
            dialog.show()
        }

        view.findViewById<FloatingActionButton>(R.id.cartToolbar).setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_cartFragment)
        }
    }
}