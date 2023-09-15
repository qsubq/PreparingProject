package com.example.supabasetestproject.presentation.screen.translate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.supabasetestproject.databinding.FragmentTranslateBinding
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class TranslateFragment : Fragment() {
    private lateinit var binding: FragmentTranslateBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTranslateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.RUSSIAN)
            .setTargetLanguage(TranslateLanguage.CHINESE)
            .build()

        val translator = Translation.getClient(options)
        translator.downloadModelIfNeeded()
            .addOnSuccessListener { binding.btnTranslate.visibility = View.VISIBLE }
            .addOnFailureListener { }

        binding.btnTranslate.setOnClickListener {
            translator.translate("Просто текст на русском")
                .addOnSuccessListener {
                    Log.e("Translate", "Translated text: $it")
                    binding.tvText2.text = it
                }
                .addOnFailureListener { }
        }
    }
}
