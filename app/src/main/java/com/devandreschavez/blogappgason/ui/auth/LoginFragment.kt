package com.devandreschavez.blogappgason.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.devandreschavez.blogappgason.R
import com.devandreschavez.blogappgason.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        isuserloggedIn()
        doLogin()

    }
    private fun isuserloggedIn(){
        firebaseAuth.currentUser?.let {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }
    private fun doLogin(){
        binding.signIn.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            validateCredentials(email, password)
            signIn(email, password)
        }
    }
    private fun validateCredentials(email: String , password: String){
        if(email.isEmpty()){
            binding.etEmail.error = "El campo está vació"
            return
        }
        if(password.isEmpty()){
            binding.etPassword.error = "El campo está vació"
            return
        }
    }
    private fun signIn(email: String, password: String){

    }
}