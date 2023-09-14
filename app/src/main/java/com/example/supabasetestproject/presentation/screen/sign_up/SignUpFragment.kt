package com.example.supabasetestproject.presentation.screen.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.supabasetestproject.R
import com.example.supabasetestproject.data.remote_data_source.RemoteRepositoryImpl
import com.example.supabasetestproject.databinding.FragmentSignUpBinding
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvToSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.btnSignIn.setOnClickListener {
            val repositoryImpl = RemoteRepositoryImpl()

            lifecycleScope.launch {
                repositoryImpl.signUp(
                    binding.TIETEmail.text.toString(),
                    binding.TIETPassword.text.toString(),
                )
            }
            val bundle = Bundle()
            bundle.putString("email", binding.TIETEmail.text.toString())
            this.findNavController().navigate(
                R.id.action_signUpFragment_to_signInFragment,
                bundle,
            )
        }
    }
}
