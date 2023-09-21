package com.example.supabasetestproject.presentation.screen.object_recognition

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.supabasetestproject.databinding.FragmentObjectRecognitionBinding
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

class ObjectRecognitionFragment : Fragment() {
    private lateinit var binding: FragmentObjectRecognitionBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentObjectRecognitionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load("https://images.dog.ceo//breeds//shihtzu//n02086240_11959.jpg")
            .into(binding.imgObjectRecognition)

//        val options = ObjectDetectorOptions.Builder()
//            .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
//            .enableMultipleObjects()
//            .enableClassification()
//            .build()

        val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
        binding.imgObjectRecognition.setOnClickListener {
            val image = binding.imgObjectRecognition.drawable
            val bitmap = image?.toBitmap()
            val inputImage = bitmap?.let { it1 -> InputImage.fromBitmap(it1, 0) }

            if (inputImage != null) {
                labeler.process(inputImage)
                    .addOnSuccessListener {
                        Log.e("Object recognition", it.first().text)
                    }
                    .addOnFailureListener { }

//                objectDetector.process(inputImage)
//                    .addOnSuccessListener {
//                        Log.e(
//                            "Object detection",
//                            "list: $it, size: ${it.size}, label: ${it[0].labels}",
//                        )
//                        for (detectedObject in it) {
//                            val boundingBox = detectedObject.boundingBox
//                            val trackingId = detectedObject.trackingId
//                            for (label in detectedObject.labels) {
//                                val text = label.text
//                                Log.e(
//                                    "Object detection",
//                                    "text: $text",
//                                )
//                                val index = label.index
//                                Log.e(
//                                    "Object detection",
//                                    "text: $index",
//                                )
//                                val confidence = label.confidence
//                                Log.e(
//                                    "Object detection",
//                                    "text: $confidence",
//                                )
//                            }
//                        }
//                    }
//                    .addOnFailureListener {
//                    }
            }
        }
    }
}
