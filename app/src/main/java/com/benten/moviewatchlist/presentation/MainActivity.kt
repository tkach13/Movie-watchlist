package com.benten.moviewatchlist.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.benten.moviewatchlist.R
import com.benten.moviewatchlist.databinding.ActivityMainBinding
import com.benten.moviewatchlist.presentation.listScreen.MovieListFragment
import com.facebook.drawee.backends.pipeline.Fresco

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkForSharedText()
        Fresco.initialize(this)
        if (supportFragmentManager.findFragmentById(R.id.flContent) == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flContent, MovieListFragment())
                addToBackStack(MovieListFragment::javaClass.name)
                commit()
            }
        }


    }

    private fun checkForSharedText() {
        if (intent.action == Intent.ACTION_SEND) {
            Toast.makeText(this, intent.extras?.getString(Intent.EXTRA_TEXT), Toast.LENGTH_LONG)
                .show()
        }
    }
}

