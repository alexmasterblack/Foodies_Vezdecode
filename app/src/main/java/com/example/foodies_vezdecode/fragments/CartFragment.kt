package com.example.foodies_vezdecode.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodies_vezdecode.R
import com.example.foodies_vezdecode.adapter.RecyclerViewCartAdapter
import com.example.foodies_vezdecode.model.Product
import com.example.foodies_vezdecode.storage.FetchJSON
import com.example.foodies_vezdecode.storage.SharedPref
import com.google.android.material.button.MaterialButton

class CartFragment : Fragment(R.layout.cart_fragment) {

    private val productsJsonPath = "Products.json"
    private val products: MutableList<Product> = mutableListOf()

    private val adapter = RecyclerViewCartAdapter(this)

    private val cartProducts: MutableList<Product> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        var count = 0

        val fetchJSON = FetchJSON(requireContext())
        products.addAll(fetchJSON.parseProducts(productsJsonPath))
        for (product in products) {
            val sharedPref = SharedPref(requireContext(), product.id.toString())
            if (sharedPref.check()) {
                cartProducts.add(product)
                count += sharedPref.get().toInt() * product.priceCurrent
            }
        }

        if (count == 0) {
            view.findViewById<TextView>(R.id.emptyText).visibility = View.VISIBLE
        }

        adapter.setData(cartProducts)

        view.findViewById<RecyclerView>(R.id.cartCards).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CartFragment.adapter

            this.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        view.findViewById<MaterialButton>(R.id.buyButton).text =
            "Заказать за ".plus(count).plus(" ₽")

    }
}