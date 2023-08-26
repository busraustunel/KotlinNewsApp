package com.example.newsapp.ui.signUp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.data.state.UserRegisterState
import com.example.newsapp.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val viewModel:SignUpViewModel by activityViewModels()
    lateinit var binding: FragmentSignUpBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSignUpBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        observeRegisterState()
        listeners()
    }

    private fun observeRegisterState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.registerState.collect {
                    when(it) {
                        UserRegisterState.Idle -> {}
                        UserRegisterState.Checking -> {
                            binding.progressBar.isVisible = true
                            binding.btnSignUp.isVisible = false
                        }
                        UserRegisterState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.btnSignUp.isVisible = true
                            AlertDialog.Builder(requireContext())
                                .setTitle("Success")
                                .setMessage("User registered successfully")
                                .setPositiveButton("Ok") { _, _ ->
                                    findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
                                }
                                .show()
                        }
                        is UserRegisterState.Error -> {}

                    }
                }
            }
        }
    }

    private fun listeners() {
        binding.btnSignUp.setOnClickListener {
            viewModel.insert(binding.etName.text.toString().trim(),
                binding.etSurname.text.toString().trim(),
                binding.etEmail.text.toString().trim(),
                binding.etPassword.text.toString().trim(),
                binding.etPasswordAgain.text.toString().trim()
                )
        }
    }


}