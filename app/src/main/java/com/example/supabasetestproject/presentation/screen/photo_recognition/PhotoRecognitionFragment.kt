package com.example.supabasetestproject.presentation.screen.photo_recognition

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.supabasetestproject.data.local_data_source.LocalRepositoryImpl
import com.example.supabasetestproject.databinding.FragmentPhotoRecognitionBinding
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class PhotoRecognitionFragment : Fragment() {
    private lateinit var binding: FragmentPhotoRecognitionBinding
    private lateinit var photoPickerLauncher: ActivityResultLauncher<Void?>

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // That's for capture image from camera
//        photoPickerLauncher =
//            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
//                binding.imgRecognition.setImageBitmap(it)
//            }

        // That's for capture image from gallery
//        photoPickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
//        }
//        photoPickerLauncher.launch("image/*")

        photoPickerLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
                binding.imgRecognition.setImageBitmap(it)

                val inputImage = it?.let {
                    InputImage.fromBitmap(it, 0)
                }
                val textRecognizer =
                    TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

                if (inputImage != null) {
                    textRecognizer.process(inputImage)
                        .addOnSuccessListener { text ->
                            val languageIndentifier = LanguageIdentification.getClient()
                            languageIndentifier.identifyLanguage(text.text)
                                .addOnSuccessListener { languageCode ->
                                    if (languageCode == "en") {
                                        val repositoryImpl = LocalRepositoryImpl(requireContext())

                                        Log.e("Language", TranslateLanguage.RUSSIAN)

                                        val translatorOptions = TranslatorOptions.Builder()
                                            .setSourceLanguage(TranslateLanguage.ENGLISH)
                                            .setTargetLanguage(repositoryImpl.getLanguage())
                                            .build()

                                        val translatorClient =
                                            Translation.getClient(translatorOptions)
                                        translatorClient.downloadModelIfNeeded()
                                            .addOnSuccessListener {
                                                translatorClient.translate(text.text)
                                                    .addOnSuccessListener {
                                                        binding.tvRecognizedText.text = it
                                                    }
                                                    .addOnFailureListener {
                                                        Log.e(
                                                            "Translate",
                                                            "Error on translation $it",
                                                        )
                                                    }
                                            }
                                            .addOnFailureListener { }
                                    } else {
                                    }
                                }
                                .addOnFailureListener {
                                    Log.e(
                                        "Language identifier",
                                        "Error on language indent $it",
                                    )
                                }
                        }
                        .addOnFailureListener {
                            Log.e(
                                "Photo recognition",
                                "Error on photo recognition $it",
                            )
                        }
                }
            }
        binding = FragmentPhotoRecognitionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgRecognition.setOnClickListener {
            photoPickerLauncher.launch()
        }
    }
}
