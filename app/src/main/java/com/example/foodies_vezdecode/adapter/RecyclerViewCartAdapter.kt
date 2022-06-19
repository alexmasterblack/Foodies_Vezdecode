package com.example.foodies_vezdecode.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.foodies_vezdecode.R
import com.example.foodies_vezdecode.model.Product
import com.example.foodies_vezdecode.storage.SharedPref
import com.google.android.material.button.MaterialButton

class RecyclerViewCartAdapter(private val fragment: Fragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CartViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cart_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CartViewHolder).bind(data[position])
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

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodName = itemView.findViewById<TextView>(R.id.foodName)
        private val currentPrice = itemView.findViewById<TextView>(R.id.currentPrice)
        private val oldPrice = itemView.findViewById<TextView>(R.id.oldPrice)

        private val minusButton = itemView.findViewById<MaterialButton>(R.id.minusButton)
        private val counter = itemView.findViewById<TextView>(R.id.counter)
        private val plusButton = itemView.findViewById<MaterialButton>(R.id.plusButton)

        fun bind(card: Product) {
            val sharedPref = SharedPref(fragment.requireContext(), card.id.toString())

            foodName.text = card.name
            currentPrice.text = card.priceCurrent.toString().plus(" ₽")

            val priceOld = card.priceOld.toString().plus(" ₽")

            val span = SpannableString(priceOld)
            val spanStartLength = span.length - priceOld.length

            span.setSpan(
                StrikethroughSpan(),
                spanStartLength,
                span.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            if (card.priceOld == 0) {
                oldPrice.visibility = View.INVISIBLE
            } else {
                oldPrice.text = span
            }
            if (card.priceOld  == card.priceCurrent) {
                oldPrice.visibility = View.INVISIBLE
                currentPrice.text = card.priceCurrent.toString().plus(" ₽")
            }

            counter.text = sharedPref.get()

            plusButton.setOnClickListener {
                val newValue = counter.text.toString().toInt() + 1
                counter.text = newValue.toString()
                sharedPref.save(newValue.toString())
            }

            minusButton.setOnClickListener {
                if (counter.text.toString().toInt() > 1) {
                    val newValue = counter.text.toString().toInt() - 1
                    counter.text = newValue.toString()
                    sharedPref.save(newValue.toString())
                } else {
                    sharedPref.clean()
                    data.remove(card)
                    notifyItemRemoved(adapterPosition)
                    notifyItemRangeChanged(adapterPosition, data.size)
                }
            }

        }
    }
}