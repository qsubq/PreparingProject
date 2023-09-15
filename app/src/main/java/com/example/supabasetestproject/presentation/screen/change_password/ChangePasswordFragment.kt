package com.example.supabasetestproject.presentation.screen.change_password

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.supabasetestproject.R
import com.example.supabasetestproject.data.remote_data_source.RemoteRepositoryImpl
import com.example.supabasetestproject.databinding.FragmentChangePasswordBinding
import kotlinx.coroutines.launch

class ChangePasswordFragment : Fragment() {

    private lateinit var binding: FragmentChangePasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChangePasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnChangePassword.isEnabled = false
        binding.btnChangePassword.setOnClickListener {
            val repositoryImpl = RemoteRepositoryImpl()
            lifecycleScope.launch {
                repositoryImpl.changePassword(binding.TIETPassword.text.toString())
                findNavController().navigate(R.id.action_changePasswordFragment_to_mainFragment)
            }
        }

        binding.TIETPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.TIETPassword.text.isNullOrEmpty() || binding.TIETPasswordConfirm.text.isNullOrEmpty()) {
                    binding.btnChangePassword.isEnabled = false
                } else {
                    if (binding.TIETPassword.text.toString() == (binding.TIETPasswordConfirm.text.toString())
                    ) {
                        binding.btnChangePassword.isEnabled = true
                    } else {
                        binding.btnChangePassword.isEnabled = false
                    }
                }
            }
        })

        binding.TIETPasswordConfirm.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.TIETPassword.text.isNullOrEmpty() && binding.TIETPasswordConfirm.text.isNullOrEmpty()) {
                    binding.btnChangePassword.isEnabled = false
                } else {
                    if (binding.TIETPassword.text.toString() == binding.TIETPasswordConfirm.text.toString()
                    ) {
                        binding.btnChangePassword.isEnabled = true
                    } else {
                        binding.btnChangePassword.isEnabled = false
                    }
                }
            }
        })
    }
}
