package com.example.foodies_vezdecode.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodies_vezdecode.R
import com.example.foodies_vezdecode.model.FoodCard
import com.example.foodies_vezdecode.model.TagType
import com.google.android.material.button.MaterialButton


class RecyclerViewAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<FoodCard> = mutableListOf(
        FoodCard(1, "Название блюда", 500, 720, TagType.DISCOUNT),
        FoodCard(2, "Название блюда", 500, 480, TagType.FREE_MEAT),
        FoodCard(3, "Название блюда", 500, 480, TagType.NONE),
        FoodCard(4, "Название блюда", 500, 480, TagType.NONE),
        FoodCard(5, "Название блюда", 500, 480, TagType.NONE)
    )

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

    inner class FoodCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodTag = itemView.findViewById<ImageView>(R.id.foodTag)
        private val foodName = itemView.findViewById<TextView>(R.id.foodName)
        private val foodWeight = itemView.findViewById<TextView>(R.id.foodWeight)
        private val foodButton = itemView.findViewById<MaterialButton>(R.id.foodButton)

        private val minusButton = itemView.findViewById<MaterialButton>(R.id.minusButton)
        private val counter = itemView.findViewById<TextView>(R.id.counter)
        private val plusButton = itemView.findViewById<MaterialButton>(R.id.plusButton)

        fun bind(card: FoodCard) {
            foodName.text = card.name
            foodWeight.text = card.weight.toString().plus(" г")
            foodButton.text = card.price.toString().plus(" ₽")

            if (card.tag == TagType.DISCOUNT) {
                val span =
                    SpannableString(card.price.toString().plus(" ₽ 800 ₽"))

                span.setSpan(
                    StrikethroughSpan(),
                    span.length - 5,
                    span.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                span.setSpan(
                    ForegroundColorSpan(Color.GRAY),
                    span.length - 5,
                    span.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                span.setSpan(
                    AbsoluteSizeSpan(14, true),
                    span.length - 5,
                    span.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                foodButton.text = span
            } else {
                foodTag.visibility = View.GONE
            }

            // чтобы кнопки показать
            if (card.tag == TagType.FREE_MEAT) {
                foodButton.visibility = View.GONE

            } else {
                minusButton.visibility = View.GONE
                counter.visibility = View.GONE
                plusButton.visibility = View.GONE
            }
        }
    }
}