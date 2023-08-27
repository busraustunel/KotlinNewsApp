package com.example.newsapp.ui.signUp

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.data.state.MessageState
import com.example.newsapp.data.state.UserRegisterState
import com.example.newsapp.databinding.FragmentSignUpBinding
import com.example.newsapp.showToast
import kotlinx.coroutines.launch

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val viewModel: SignUpViewModel by activityViewModels()
    lateinit var binding: FragmentSignUpBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSignUpBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        observeRegisterState()
        observeMessageState()
        setupListeners()
    }

    private fun observeMessageState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.messageState.collect {
                    when (it) {
                        MessageState.Idle -> {}
                        MessageState.UserNotFound -> {
                            requireContext().showToast("User not found")}
                        MessageState.Success -> {
                            requireContext().showToast("Success")
                        }
                        MessageState.Error -> {
                            requireContext().showToast("Error")
                        }
                    }
                }
            }
        }
    }

    private fun observeRegisterState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.registerState.collect {
                    when (it) {
                        UserRegisterState.Idle -> {}
                        UserRegisterState.Checking -> showLoadingState()
                        is UserRegisterState.Success -> showSuccessState()
                        UserRegisterState.InputError -> {
                            TODO("Show input error")
                        }
                        is UserRegisterState.Error -> showErrorState()
                    }
                }
            }
        }
    }

    private fun setupListeners() {
        binding.btnSignUpRegister.setOnClickListener {
            viewModel.insertUser(
                binding.etName.text.toString().trim(),
                binding.etSurname.text.toString().trim(),
                binding.etEmail.text.toString().trim(),
                binding.etPassword.text.toString().trim(),
                binding.etConfirmPassword.text.toString().trim()
            )
        }

        binding.textViewLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun showLoadingState() {
        binding.progressBar.isVisible = true
        binding.btnSignUpRegister.isVisible = false
    }

    private fun showSuccessState() {
        binding.progressBar.isVisible = false
        binding.btnSignUpRegister.isVisible = true
    }


    private fun showErrorState() {
        binding.progressBar.isVisible = false
        binding.btnSignUpRegister.isVisible = true
    }
}