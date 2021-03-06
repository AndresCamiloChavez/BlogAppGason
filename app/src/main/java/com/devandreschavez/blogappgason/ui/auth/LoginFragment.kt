package com.devandreschavez.blogappgason.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.devandreschavez.blogappgason.R
import com.devandreschavez.blogappgason.core.Result
import com.devandreschavez.blogappgason.data.remote.auth.AuthDataSource
import com.devandreschavez.blogappgason.databinding.FragmentLoginBinding
import com.devandreschavez.blogappgason.domain.auth.AuthRepoimpl
import com.devandreschavez.blogappgason.viewModel.auth.AuthViewModel
import com.devandreschavez.blogappgason.viewModel.auth.FactoryAuthViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val viewModel: AuthViewModel by viewModels {
        FactoryAuthViewModel(
            AuthRepoimpl(
                AuthDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        isUserloggedIn()
        doLogin()
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    private fun isUserloggedIn() {
        firebaseAuth.currentUser?.let {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

    private fun doLogin() {
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            validateCredentials(email, password)
            signIn(email, password)
        }
    }

    private fun validateCredentials(email: String, password: String) {
        if (email.isEmpty()) {
            binding.etEmail.error = "El campo est?? vaci??"
            return
        }
        if (password.isEmpty()) {
            binding.etPassword.error = "El campo est?? vaci??"
            return
        }
    }

    private fun signIn(email: String, password: String) {
        viewModel.signIn(email, password).observe(viewLifecycleOwner, Observer {
            when(it){
                is Result.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                    binding.btnSignIn.isEnabled = false
                }
                is Result.Success -> {
                    binding.progressbar.visibility = View.GONE
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
                is Result.Failure -> {
                    binding.progressbar.visibility = View.GONE
                    binding.btnSignIn.isEnabled = true
                    Toast.makeText(requireContext(), "??Ocurri?? un error!", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}