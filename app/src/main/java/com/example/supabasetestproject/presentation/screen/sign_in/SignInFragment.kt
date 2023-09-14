package com.example.supabasetestproject.presentation.screen.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.supabasetestproject.data.remote_data_source.RemoteRepositoryImpl
import com.example.supabasetestproject.databinding.FragmentSignInBinding
import kotlinx.coroutines.launch

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private val email by lazy {
        arguments?.getString("email")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.setOnClickListener {
            val repositoryImpl = RemoteRepositoryImpl()

            lifecycleScope.launch {
                repositoryImpl.signIn(
                    binding.TIETEmail.text.toString(),
                    binding.TIETPassword.text.toString(),
                )
            }
        }
    }
}
