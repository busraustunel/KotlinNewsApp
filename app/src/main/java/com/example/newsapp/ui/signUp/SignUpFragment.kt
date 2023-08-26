package com.example.newsapp.ui.signUp

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import kotlinx.coroutines.launch

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val viewModel: SignUpViewModel by activityViewModels()
    lateinit var binding: FragmentSignUpBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSignUpBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        observeRegisterState()
        setupListeners()
    }

    private fun observeRegisterState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.registerState.collect {
                    when (it) {
                        UserRegisterState.Idle -> {}
                        UserRegisterState.Checking -> showLoadingState()
                        is UserRegisterState.Success -> showSuccessState()
                        UserRegisterState.InputError -> showInputError()
                        is UserRegisterState.Error -> showErrorState()
                    }
                }
            }
        }
    }

    private fun setupListeners() {
        binding.btnSignUp.setOnClickListener {
            viewModel.insertUser(
                binding.etName.text.toString().trim(),
                binding.etSurname.text.toString().trim(),
                binding.etEmail.text.toString().trim(),
                binding.etPassword.text.toString().trim(),
                binding.etPasswordAgain.text.toString().trim()
            )
        }

        binding.btnGoLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun showLoadingState() {
        binding.progressBar.isVisible = true
        binding.btnSignUp.isVisible = false
    }

    private fun showSuccessState() {
        binding.progressBar.isVisible = false
        binding.btnSignUp.isVisible = true

        //TODO: Bu mesaj neden iki kere gösteriliyor her gidip geldiğimde?
        Toast.makeText(requireContext(), "Kayıt başarılı", Toast.LENGTH_SHORT).show()
    }

    private fun showInputError() {
        if (binding.etName.text.isEmpty() || binding.etSurname.text.isEmpty() || binding.etEmail.text.isEmpty() || binding.etPassword.text.isEmpty() || binding.etPasswordAgain.text.isEmpty()) Toast.makeText(
            requireContext(),
            "Lütfen tüm alanları doldurun",
            Toast.LENGTH_SHORT
        ).show()
        else if (binding.etPassword.text.toString() != binding.etPasswordAgain.text.toString()) Toast.makeText(
            requireContext(),
            "Şifreler eşleşmiyor",
            Toast.LENGTH_SHORT
        ).show()
        else if (binding.etPassword.text.length < 6) Toast.makeText(
            requireContext(),
            "Şifre en az 6 karakter olmalı",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showErrorState() {
        binding.progressBar.isVisible = false
        binding.btnSignUp.isVisible = true
        Toast.makeText(requireContext(), "Bir hata oluştu", Toast.LENGTH_SHORT).show()
    }
}