package com.example.foodies_vezdecode.support

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.foodies_vezdecode.R
import com.example.foodies_vezdecode.adapter.RecyclerViewAdapter
import com.example.foodies_vezdecode.adapter.ViewPagerAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}