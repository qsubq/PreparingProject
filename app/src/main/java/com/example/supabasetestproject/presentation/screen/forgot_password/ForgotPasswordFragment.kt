package com.example.supabasetestproject.presentation.screen.forgot_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.supabasetestproject.R
import com.example.supabasetestproject.data.remote_data_source.RemoteRepositoryImpl
import com.example.supabasetestproject.databinding.FragmentForgotPasswordBinding
import kotlinx.coroutines.launch

class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSendOTP.setOnClickListener {
            val repository = RemoteRepositoryImpl()

            lifecycleScope.launch {
                repository.otpSignIn(binding.TIETEmail.text.toString())
            }
            val bundle = Bundle()
            bundle.putString("email", binding.TIETEmail.text.toString())
            findNavController().navigate(
                R.id.action_forgotPasswordFragment_to_emailCodeFragment,
                bundle,
            )
        }
    }
}
