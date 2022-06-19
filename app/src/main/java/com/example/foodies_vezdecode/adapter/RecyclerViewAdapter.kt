package com.example.foodies_vezdecode.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.foodies_vezdecode.R
import com.example.foodies_vezdecode.fragments.MenuFragmentDirections
import com.example.foodies_vezdecode.model.Product
import com.example.foodies_vezdecode.storage.SharedPref
import com.google.android.material.button.MaterialButton


class RecyclerViewAdapter(private val fragment: Fragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FoodCardViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.food_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FoodCardViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Product>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class FoodCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodTagOne = itemView.findViewById<ImageView>(R.id.foodTagOne)
        private val foodTagTwo = itemView.findViewById<ImageView>(R.id.foodTagTwo)
        private val foodName = itemView.findViewById<TextView>(R.id.foodName)
        private val foodWeight = itemView.findViewById<TextView>(R.id.foodWeight)
        private val foodButton = itemView.findViewById<MaterialButton>(R.id.foodButton)

        private val minusButton = itemView.findViewById<MaterialButton>(R.id.minusButton)
        private val counter = itemView.findViewById<TextView>(R.id.counter)
        private val plusButton = itemView.findViewById<MaterialButton>(R.id.plusButton)

        fun bind(card: Product) {
            val sharedPref = SharedPref(fragment.requireContext(), card.id.toString())

            foodName.text = card.name
            foodWeight.text = card.measure.toString().plus(" ").plus(card.measureUnit)
            foodButton.text = card.priceCurrent.toString().plus(" ₽")
            foodTagOne.visibility = View.GONE
            foodTagTwo.visibility = View.GONE

            foodButton.visibility = View.VISIBLE

            minusButton.visibility = View.GONE
            counter.visibility = View.GONE
            plusButton.visibility = View.GONE

            counter.text = sharedPref.get()

            if (sharedPref.check()) {
                foodButton.visibility = View.VISIBLE
                minusButton.visibility = View.GONE
                counter.visibility = View.GONE
                plusButton.visibility = View.GONE
            }

            if (card.priceOld > 0) {
                val priceCurrent = card.priceCurrent.toString().plus(" ₽ ")
                val priceOld = card.priceOld.toString().plus(" ₽")

                val span = SpannableString(priceCurrent.plus(priceOld))
                val spanStartLength = span.length - priceOld.length

                span.setSpan(
                    StrikethroughSpan(),
                    spanStartLength,
                    span.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                span.setSpan(
                    ForegroundColorSpan(Color.GRAY),
                    spanStartLength,
                    span.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                span.setSpan(
                    AbsoluteSizeSpan(14, true),
                    spanStartLength,
                    span.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                foodButton.text = span
                foodTagOne.visibility = View.VISIBLE
                foodTagOne.setImageResource(R.drawable.ic_discount)
            }

            if (card.tagIds.isNotEmpty()) {
                if (card.priceOld > 0) {
                    if (card.tagIds[0] == 4) {
                        foodTagTwo.setImageResource(R.drawable.ic_spicy)
                    } else if (card.tagIds[0] == 2) {
                        foodTagTwo.setImageResource(R.drawable.ic_veggy)
                    }
                    foodTagTwo.visibility = View.VISIBLE
                } else {
                    if (card.tagIds[0] == 4) {
                        foodTagOne.setImageResource(R.drawable.ic_spicy)
                    } else if (card.tagIds[0] == 2) {
                        foodTagOne.setImageResource(R.drawable.ic_veggy)
                    }
                    foodTagOne.visibility = View.VISIBLE
                }
            }

            foodButton.setOnClickListener {
                fragment.findNavController().navigate(
                    MenuFragmentDirections.actionMenuFragmentToFoodMenuDetailsFragment(
                        card.id,
                        card.name,
                        card.description,
                        card.measure,
                        card.energyPer100Grams.toFloat(),
                        card.proteinsPer100Grams.toFloat(),
                        card.fatsPer100Grams.toFloat(),
                        card.carbohydratesPer100Grams.toFloat(),
                        card.measureUnit,
                        card.priceCurrent
                    )
                )
            }

            if (sharedPref.check()) {
                minusButton.visibility = View.VISIBLE
                counter.visibility = View.VISIBLE
                plusButton.visibility = View.VISIBLE
                foodButton.visibility = View.INVISIBLE
            }

            plusButton.setOnClickListener {
                val newValue = counter.text.toString().toInt() + 1
                counter.text = newValue.toString()
                sharedPref.save(newValue.toString())
            }

            minusButton.setOnClickListener {
                if (counter.text.toString().toInt() > 0) {
                    val newValue = counter.text.toString().toInt() - 1
                    counter.text = newValue.toString()
                    sharedPref.save(newValue.toString())
                }
                if (counter.text.toString().toInt() == 0) {
                    sharedPref.clean()
                    foodButton.visibility = View.VISIBLE
                    minusButton.visibility = View.GONE
                    counter.visibility = View.GONE
                    plusButton.visibility = View.GONE
                }
            }
        }
    }
}