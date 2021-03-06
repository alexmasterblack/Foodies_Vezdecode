package com.example.foodies_vezdecode.support

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.foodies_vezdecode.R
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferenceFile = File("/data/data/$packageName/shared_prefs/")
        val listFiles = sharedPreferenceFile.listFiles()
        for (file in listFiles!!) {
            file.delete()
        }
    }
}