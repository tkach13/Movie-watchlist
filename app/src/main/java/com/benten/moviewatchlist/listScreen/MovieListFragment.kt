package com.benten.moviewatchlist.listScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.benten.moviewatchlist.MoviesAdapter
import com.benten.moviewatchlist.R
import com.benten.moviewatchlist.databinding.FragmentMovieListBinding
import com.benten.moviewatchlist.detailsScreen.MovieDetailsFragment
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



        CoroutineScope(IO).launch {
            try {
                val response =
                    RetrofitHelper.popularsApi.getPopularMovies("843c612ddsadsadasdadads1207fdec63f0e6a5fd426d68")
                withContext(Main) {
                    moviesAdapter.updateList(response.results)
                }
            } catch (e:HttpException){
                withContext(Main) {
                    Toast.makeText(requireContext(),"error haa been occured, ${e.code()}", Toast.LENGTH_LONG).show()
                }
            }


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