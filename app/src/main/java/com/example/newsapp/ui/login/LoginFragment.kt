package com.example.newsapp.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.newsapp.Constants.REMEMBER_ME_KEY
import com.example.newsapp.Constants.SHARED_PREF_NAME
import com.example.newsapp.R
import com.example.newsapp.data.state.UserLoginState
import com.example.newsapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    lateinit var binding: FragmentLoginBinding
    private val viewModel:LoginViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentLoginBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val rememberMe = sharedPreferences.getBoolean(REMEMBER_ME_KEY, false)

        if (rememberMe) {
            findNavController().navigate(R.id.action_loginFragment_to_categoryFragment)
        }


        setupListeners()
        observeUserLoginState()


    }






    private fun observeUserLoginState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.userLoginState.collect {
                    when(it) {
                        is UserLoginState.Idle -> {
                            binding.progressBar.visibility = View.GONE
                        }
                        is UserLoginState.Checking -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is UserLoginState.UserNotFound -> {
                            binding.progressBar.visibility = View.GONE
                            AlertDialog.Builder(requireContext())
                                .setTitle("Error")
                                .setMessage("User not found")
                                .setPositiveButton("Ok") { dialog, _ ->
                                    dialog.dismiss()
                                }
                                .create()
                                .show()
                        }
                        is UserLoginState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            AlertDialog.Builder(requireContext())
                                .setTitle("Success")
                                .setMessage("Giriş başarılı")
                                .setPositiveButton("Ok") { dialog, _ ->
                                    dialog.dismiss()
                                    findNavController().navigate(R.id.action_loginFragment_to_categoryFragment)
                                }
                                .create()
                                .show()
                        }
                        is UserLoginState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            AlertDialog.Builder(requireContext())
                                .setTitle("Error")
                                .setMessage(it.throwable?.localizedMessage)
                                .setPositiveButton("Ok") { dialog, _ ->
                                    dialog.dismiss()
                                }
                                .create()
                                .show()
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

            val sharedPreferences = requireContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            if (binding.cbRememberMe.isChecked) {
                sharedPreferences.edit().putBoolean(REMEMBER_ME_KEY, true).apply()
            }

        }


        binding.btnGoSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

}