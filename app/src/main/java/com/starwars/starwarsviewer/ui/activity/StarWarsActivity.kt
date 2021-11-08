package com.starwars.starwarsviewer.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.starwars.starwarsviewer.R

class StarWarsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}