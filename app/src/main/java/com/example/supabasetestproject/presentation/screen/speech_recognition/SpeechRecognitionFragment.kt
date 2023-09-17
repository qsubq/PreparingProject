package com.example.supabasetestproject.presentation.screen.speech_recognition

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.supabasetestproject.databinding.FragmentSpeechRecognitionBinding

class SpeechRecognitionFragment : Fragment() {
    private lateinit var binding: FragmentSpeechRecognitionBinding

    //    private lateinit var speechRecognitionLauncher: ActivityResultLauncher<Intent>
    private lateinit var speechRecognitionLauncher: ActivityResultLauncher<Intent>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
//        speechRecognitionLauncher =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//                if (it.resultCode == Activity.RESULT_OK) {
//                    val res = it.data?.getStringArrayExtra(RecognizerIntent.EXTRA_RESULTS)
//
//                    res?.joinToString()
//                }
//            }

        speechRecognitionLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            }
        binding = FragmentSpeechRecognitionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.RECORD_AUDIO), 21)
        binding.imgRecordAudio.setOnClickListener {
            val intent = Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION)
            speechRecognitionLauncher.launch(intent)
        }

//        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
//        intent.putExtra(
//            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM,
//        )
    }
}
