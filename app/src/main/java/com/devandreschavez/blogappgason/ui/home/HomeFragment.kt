package com.devandreschavez.blogappgason.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.devandreschavez.blogappgason.R
import com.devandreschavez.blogappgason.core.Resource
import com.devandreschavez.blogappgason.data.model.Post
import com.devandreschavez.blogappgason.data.remote.home.HomeDataSource
import com.devandreschavez.blogappgason.databinding.FragmentHomeBinding
import com.devandreschavez.blogappgason.domain.home.HomeRepositoryImpl
import com.devandreschavez.blogappgason.ui.adapter.HomeAdapter
import com.devandreschavez.blogappgason.viewModel.home.FactoryHomeViewModel
import com.devandreschavez.blogappgason.viewModel.home.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels {
        FactoryHomeViewModel(
            HomeRepositoryImpl(
                HomeDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        viewModel.fetchPost().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressContainer.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressContainer.visibility = View.GONE
                    binding.rvHome.adapter = HomeAdapter(result.data)
                }
                is Resource.Failure -> {
                    binding.progressContainer.visibility = View.GONE
                    Toast.makeText(requireContext(), "¡Ocurrió un error!", Toast.LENGTH_SHORT).show()
                    Log.d("Home", "Ocurrió un error ${result.exception}")
                }
            }
        })

    }
}