package com.devandreschavez.blogappgason.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devandreschavez.blogappgason.R
import com.devandreschavez.blogappgason.data.remote.auth.AuthDataSource
import com.devandreschavez.blogappgason.databinding.FragmentSetupProfileBinding
import com.devandreschavez.blogappgason.domain.auth.AuthRepoimpl
import com.devandreschavez.blogappgason.viewModel.auth.AuthViewModel
import com.devandreschavez.blogappgason.viewModel.auth.FactoryAuthViewModel

class SetupProfileFragment : Fragment(R.layout.fragment_setup_profile) {
    private lateinit var binding: FragmentSetupProfileBinding
    private val viewModel: AuthViewModel by viewModels {
        FactoryAuthViewModel(
            AuthRepoimpl(
                AuthDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSetupProfileBinding.bind(view)

    }

}