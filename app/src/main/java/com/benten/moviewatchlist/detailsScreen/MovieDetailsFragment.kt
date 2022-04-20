package com.benten.moviewatchlist.detailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.benten.moviewatchlist.databinding.FragmentMovieDetailsBinding
import com.bumptech.glide.Glide

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
        val movieName = requireArguments().getString(KEY_MOVIE_NAME)
        binding.tvMovieName.text = movieName
        val movieUrl = requireArguments().getString(KEY_MOVIE_URL)
        Glide.with(requireContext()).load(movieUrl).into(binding.ivLargePoster )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_MOVIE_NAME = "KEY_MOVIE_NAME"
        const val KEY_MOVIE_URL = "KEY_MOVIE_URL"
        fun newInstance(movieName: String, movieUrl: String): MovieDetailsFragment {
            return MovieDetailsFragment().apply {
                arguments = bundleOf(KEY_MOVIE_NAME to movieName, KEY_MOVIE_URL to movieUrl)
            }
        }
    }
}