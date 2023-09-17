package com.example.supabasetestproject.presentation.screen.sign_in

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.supabasetestproject.R
import com.example.supabasetestproject.data.remote_data_source.RemoteRepositoryImpl
import com.example.supabasetestproject.databinding.FragmentSignInBinding
import kotlinx.coroutines.launch
import java.security.MessageDigest

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

        val spannableTextForgotPassword = SpannableStringBuilder("Забыли пароль ?")
        val clickableSpanForgotPassword = object : ClickableSpan() {
            override fun onClick(p0: View) {
                findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
            }
        }
        spannableTextForgotPassword.setSpan(
            clickableSpanForgotPassword,
            0,
            "Забыли пароль ?".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE,
        )
        binding.tvForgotPassword.setText(spannableTextForgotPassword, TextView.BufferType.SPANNABLE)
        binding.tvForgotPassword.movementMethod = LinkMovementMethod.getInstance()

        binding.btnSignIn.setOnClickListener {
            val repositoryImpl = RemoteRepositoryImpl()
            lifecycleScope.launch {
                repositoryImpl.signIn(
                    binding.TIETEmail.text.toString(),
                    binding.TIETPassword.text.toString(),
                )
                val bundle = Bundle()
                bundle.putString("email", binding.TIETEmail.text.toString())
                findNavController().navigate(R.id.action_signInFragment_to_mainFragment, bundle)
            }

            val message = binding.TIETPassword.text.toString()
            val digest = MessageDigest.getInstance("SHA-256")
            val hash = digest.digest(message.toByteArray())
            Log.e("SignIn", hash.toString())
            val stringHash = with(StringBuilder()) {
                hash.forEach { b ->
                    append(String.format("%02X", b))
                }
            }
            Log.e("SignIn", stringHash.toString().toLowerCase())
        }
    }
}
