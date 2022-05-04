package com.benten.moviewatchlist.presentation.listScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.benten.moviewatchlist.R
import com.benten.moviewatchlist.databinding.FragmentMovieListBinding
import com.benten.moviewatchlist.presentation.detailsScreen.MovieDetailsFragment
import com.benten.moviewatchlist.helpers.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private lateinit var moviesAdapter: MoviesAdapter

    private val viewModel by viewModels<ListScreenViewModel>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        viewModel.getMoviesLiveData().observe(viewLifecycleOwner) {
            moviesAdapter.updateList(it)
        }

    }

    private fun setUpRecyclerView() {
        moviesAdapter = MoviesAdapter(
            mutableListOf()
        ).apply {
            setOnItemCLickListener { movieItem, i ->
                parentFragmentManager.beginTransaction().apply {
                    val detailsFragment = MovieDetailsFragment.newInstance(movieItem.id)
                    addToBackStack("")
                    replace(R.id.flContent, detailsFragment)
                    commit()
                }
            }
        }
        binding.rvMovies.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvMovies.adapter = moviesAdapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}