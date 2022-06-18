package com.example.foodies_vezdecode.model

data class FoodCard(
    val id: Int,
    val name: String,
    val weight: Int,
    val price: Int,
    val tag: TagType
)