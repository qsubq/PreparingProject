package com.example.supabasetestproject.presentation.screen.email_code

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.supabasetestproject.R
import com.example.supabasetestproject.data.remote_data_source.RemoteRepositoryImpl
import com.example.supabasetestproject.databinding.FragmentEmailCodeBinding
import kotlinx.coroutines.launch

class EmailCodeFragment : Fragment() {
    private lateinit var binding: FragmentEmailCodeBinding
    private val email by lazy {
        arguments?.getString("email")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEmailCodeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.setOnClickListener {
            val repositoryImpl = RemoteRepositoryImpl()
            lifecycleScope.launch {
                email?.let { it1 ->
                    repositoryImpl.verifyOTP(
                        it1,
                        binding.TIETPassword.text.toString(),
                    )
                }
                findNavController().navigate(R.id.action_emailCodeFragment_to_changePasswordFragment)
            }
        }
    }
}
