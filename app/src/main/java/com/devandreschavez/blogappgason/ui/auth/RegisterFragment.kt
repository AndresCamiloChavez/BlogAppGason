package com.devandreschavez.blogappgason.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.devandreschavez.blogappgason.R
import com.devandreschavez.blogappgason.core.Result
import com.devandreschavez.blogappgason.data.remote.auth.AuthDataSource
import com.devandreschavez.blogappgason.data.remote.home.HomeDataSource
import com.devandreschavez.blogappgason.databinding.FragmentRegisterBinding
import com.devandreschavez.blogappgason.domain.auth.AuthRepoimpl
import com.devandreschavez.blogappgason.domain.home.HomeRepositoryImpl
import com.devandreschavez.blogappgason.viewModel.auth.AuthViewModel
import com.devandreschavez.blogappgason.viewModel.auth.FactoryAuthViewModel

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: AuthViewModel by viewModels {
        FactoryAuthViewModel(
            AuthRepoimpl(
                AuthDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        getSignUpInfo()
    }

    private fun getSignUpInfo() {
        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etPassword.text.toString()
            val username = binding.etUsername.text.toString()
            if (validateUserData(
                    password,
                    confirmPassword,
                    username,
                    email
                )
            ) return@setOnClickListener

            createUser(email, password, username)
            Log.d("signUpData", "data: ${username} ${password} $confirmPassword $email")
        }
    }

    private fun createUser(email: String, password: String, username: String) {
        viewModel.signUp(email, password, username).observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Result.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                    binding.btnSignUp.isEnabled = false
                }
                is Result.Success -> {
                    binding.progressbar.visibility = View.GONE
                    findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
                }
                is Result.Failure -> {
                    binding.progressbar.visibility = View.GONE
                    binding.btnSignUp.isEnabled = true
                    Toast.makeText(requireContext(), "Ocurrió un error ${result.exception}", Toast.LENGTH_SHORT).show()

                }
            }
        })
    }

    private fun validateUserData(
        password: String,
        confirmPassword: String,
        username: String,
        email: String
    ): Boolean {
        if (password != confirmPassword) {
            binding.etPassword.error = "Password does not match"
            binding.etConfirmPassword.error = "Password does not match"
            return true
        }
        if (username.isEmpty()) {
            binding.etUsername.error = "Email is empty"
        }
        if (email.isEmpty()) {
            binding.etEmail.error = "Email is empty"
        }
        if (password.isEmpty()) {
            binding.etPassword.error = "Password is empty"
            return true
        }
        if (confirmPassword.isEmpty()) {
            binding.etConfirmPassword.error = "Password is empty"
        }
        return false
    }
}
