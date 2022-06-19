package com.example.foodies_vezdecode.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodies_vezdecode.R
import com.example.foodies_vezdecode.storage.SharedPref
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FoodMenuDetailsFragment : Fragment(R.layout.food_menu_details_fragment) {

    private val args by navArgs<FoodMenuDetailsFragmentArgs>()

    private val idProduct by lazy { args.id }

    private val foodName by lazy { args.foodName }

    private val foodDescription by lazy { args.foodDescription }

    private val weight by lazy { args.weight }

    private val energy by lazy { args.energy }

    private val proteins by lazy { args.proteins }

    private val fats by lazy { args.fats }

    private val carbohydrates by lazy { args.carbohydrates }

    private val measureUnit by lazy { args.measureUnit }

    private val price by lazy { args.price }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.backButton).setOnClickListener {
            findNavController().navigateUp()
        }

        view.findViewById<TextView>(R.id.foodName).text = foodName
        view.findViewById<TextView>(R.id.foodDescription).text = foodDescription
        view.findViewById<TextView>(R.id.weight).text =
            weight.toString().plus(" ").plus(measureUnit)
        view.findViewById<TextView>(R.id.energy).text = energy.toString().plus(" ккал")
        view.findViewById<TextView>(R.id.proteins).text =
            proteins.toString().plus(" ").plus(measureUnit)
        view.findViewById<TextView>(R.id.fats).text = fats.toString().plus(" ").plus(measureUnit)
        view.findViewById<TextView>(R.id.carbohydrates).text =
            carbohydrates.toString().plus(" ").plus(measureUnit)
        view.findViewById<MaterialButton>(R.id.toBasketButton).text =
            "В корзину за ".plus(price).plus(" ₽")

        view.findViewById<MaterialButton>(R.id.toBasketButton).setOnClickListener {
            val sharedPref = SharedPref(requireContext(), idProduct.toString())
            sharedPref.save("1")
            findNavController().navigateUp()
        }
    }
}