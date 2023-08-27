package com.example.newsapp.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.newsapp.Constants.REMEMBER_ME_KEY
import com.example.newsapp.Constants.SHARED_PREF_NAME
import com.example.newsapp.R
import com.example.newsapp.data.state.MessageState
import com.example.newsapp.data.state.UserLoginState
import com.example.newsapp.databinding.FragmentLoginBinding
import com.example.newsapp.showAlert
import com.example.newsapp.showToast
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by activityViewModels()
    private val sharedPreferences: SharedPreferences by lazy {
        requireContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentLoginBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        val rememberMe = sharedPreferences.getBoolean(REMEMBER_ME_KEY, false)

        if (rememberMe) {
            findNavController().navigate(R.id.action_loginFragment_to_categoryFragment)
        }


        setupListeners()
        observeUserLoginState()
        observeMessageState()


    }

    private fun observeMessageState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.messageState.collect {
                    when (it) {
                        MessageState.Idle -> {}
                        MessageState.Success -> {
                            findNavController().navigate(R.id.action_loginFragment_to_categoryFragment)
                            requireContext().showToast("Success") }
                        MessageState.UserNotFound -> {
                            requireContext().showAlert("Error", "User not found")
                        }
                        MessageState.Error -> {
                            requireContext().showAlert("Error", "Error")
                        }
                    }
                }
            }
        }
    }


    private fun observeUserLoginState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.userLoginState.collect {
                    when (it) {
                        is UserLoginState.Idle -> {}
                        is UserLoginState.Checking -> {
                            binding.progressBar.isVisible = true
                            binding.btnLogin.isVisible = false
                        }

                        is UserLoginState.UserNotFound -> {
                            binding.progressBar.isVisible = false
                            binding.btnLogin.isVisible = true
                        }

                        is UserLoginState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.btnLogin.isVisible = true
                        }

                        is UserLoginState.Error -> {
                            binding.progressBar.isVisible = false
                            binding.btnLogin.isVisible = true
                        }
                    }
                }
            }
        }
    }

    private fun setupListeners() {

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.login(email, password)

            if (binding.cbRememberMe.isChecked) {
                sharedPreferences.edit().putBoolean(REMEMBER_ME_KEY, true).apply()
            }

        }

        binding.textViewRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

}