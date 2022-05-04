package com.benten.moviewatchlist.detailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.benten.moviewatchlist.databinding.FragmentMovieDetailsBinding
import com.benten.moviewatchlist.helpers.RetrofitHelper
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception

class MovieDetailsFragment() : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = requireArguments().getInt(KEY_MOVIE_NAME)

        CoroutineScope(IO).launch {
            try {
                val response = RetrofitHelper.popularsApi.getMovieDetails(
                    movieId = movieId,
                    "843c612d1207fdec63f0e6a5fd426d68"
                )
                withContext(Main) {
                    binding.tvMovieName.text = response.originalTitle
                    binding.ivLargePoster.setImageURI("https://image.tmdb.org/t/p/w500${response.backdropPath}")
                    binding.tvMovieDescription.text = response.overview
                }
            } catch (e:Exception){

            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_MOVIE_NAME = "KEY_MOVIE_NAME"
        fun newInstance(movieId: Int): MovieDetailsFragment {
            return MovieDetailsFragment().apply {
                arguments = bundleOf(KEY_MOVIE_NAME to movieId)
            }
        }
    }
}