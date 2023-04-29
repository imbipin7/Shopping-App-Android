package com.bipin.shopy.views.newPassword

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bipin.shopy.R
import com.bipin.shopy.databinding.FragmentNewPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewPasswordFragment : Fragment(R.layout.fragment_new_password) {


    private val viewModel: NewPasswordVM by viewModels()
    private var binding: FragmentNewPasswordBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewPasswordBinding.bind(view)
        binding?.vm = viewModel
    }

}