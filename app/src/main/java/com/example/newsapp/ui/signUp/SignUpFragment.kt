package com.example.newsapp.ui.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    lateinit var binding: FragmentSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSignUpBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

}