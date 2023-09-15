package com.example.supabasetestproject.presentation.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.supabasetestproject.data.local_data_source.LocalRepositoryImpl
import com.example.supabasetestproject.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        val repositoryImpl = LocalRepositoryImpl(requireContext())
        binding.imgChina.setOnClickListener {
            repositoryImpl.setLanguage("sg")
            requireActivity().recreate()
        }
        binding.imgRus.setOnClickListener {
            repositoryImpl.setLanguage("ru")
            requireActivity().recreate()
        }
        binding.imgUsa.setOnClickListener {
            repositoryImpl.setLanguage("en")
            requireActivity().recreate()
        }
    }
}
