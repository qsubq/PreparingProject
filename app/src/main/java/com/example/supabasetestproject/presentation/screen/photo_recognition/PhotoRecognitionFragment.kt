package com.example.supabasetestproject.presentation.screen.photo_recognition

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.supabasetestproject.databinding.FragmentPhotoRecognitionBinding

class PhotoRecognitionFragment : Fragment() {
    private lateinit var binding: FragmentPhotoRecognitionBinding
    private lateinit var photoPickerLauncher: ActivityResultLauncher<String>

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

        binding = FragmentPhotoRecognitionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgRecognition.setOnClickListener {
            photoPickerLauncher.launch("image/*")
        }
    }
}
