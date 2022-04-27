package com.benten.moviewatchlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.benten.moviewatchlist.databinding.ActivityMainBinding
import com.benten.moviewatchlist.detailsScreen.MovieDetailsFragment
import com.benten.moviewatchlist.listScreen.MovieListFragment
import com.facebook.drawee.backends.pipeline.Fresco

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Fresco.initialize(this)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flContent, MovieListFragment())
            addToBackStack(MovieListFragment::javaClass.name)
            commit()
        }

    }
}

