package com.example.supabasetestproject.presentation.screen.photo_recognition

import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.supabasetestproject.databinding.FragmentPhotoRecognitionBinding
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class PhotoRecognitionFragment : Fragment() {
    private lateinit var binding: FragmentPhotoRecognitionBinding
    private lateinit var photoPickerLauncher: ActivityResultLauncher<PickVisualMediaRequest>

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        photoPickerLauncher =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
                val source = it?.let {
                    ImageDecoder.createSource(requireActivity().contentResolver, it)
                }
                val drawable = source?.let { it1 ->
                    ImageDecoder.decodeDrawable(it1)
                }

                binding.imgRecognition.background = drawable

                val bitmap = source?.let { it1 ->
                    ImageDecoder.decodeBitmap(it1)
                }
                val image = bitmap?.let { it1 ->
                    InputImage.fromBitmap(it1, 0)
                }

                val textAnalyzer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
                if (image != null) {
                    textAnalyzer.process(image)
                        .addOnSuccessListener { }
                        .addOnFailureListener { }
                }
            }
        binding = FragmentPhotoRecognitionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgRecognition.setOnClickListener {
            photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
    }
}
