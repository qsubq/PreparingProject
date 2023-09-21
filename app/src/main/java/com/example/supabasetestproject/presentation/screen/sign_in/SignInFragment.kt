package com.example.supabasetestproject.presentation.screen.sign_in

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.supabasetestproject.R
import com.example.supabasetestproject.databinding.FragmentSignInBinding
import java.security.MessageDigest

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private val viewModel by viewModels<SignInViewModel>()

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

        val spannableStringForgerPassword = SpannableStringBuilder("Forget password ?")
        val clickableSpanForgetPassword = object : ClickableSpan() {
            override fun onClick(p0: View) {
                findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
            }
        }
        spannableStringForgerPassword.setSpan(
            clickableSpanForgetPassword,
            0,
            "Forget password ?".length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
        )
        binding.tvForgotPassword.setText(
            spannableStringForgerPassword,
            TextView.BufferType.SPANNABLE,
        )
        binding.tvForgotPassword.movementMethod = LinkMovementMethod.getInstance()

        viewModel.signInLiveData.observe(viewLifecycleOwner) {
            val bundle = Bundle()
            bundle.putString("email", binding.TIETEmail.text.toString())
            findNavController().navigate(R.id.action_signInFragment_to_mainFragment, bundle)
        }

        binding.btnSignIn.setOnClickListener {
            viewModel.signIn(
                email = binding.TIETEmail.text.toString(),
                password = binding.TIETPassword.text.toString(),
            )
            val digest = MessageDigest.getInstance("SHA-256")
            val message = binding.TIETPassword.text.toString()
            val hash = digest.digest(message.toByteArray())
            Log.e("SignIn", hash.toString())

            val stringHash = with(StringBuilder()) {
                hash.forEach { b ->
                    append(String.format("%02X", b))
                }
            }
            Log.e("SignIn", stringHash.toString().lowercase())
        }
    }
}
