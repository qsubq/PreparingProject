package com.example.supabasetestproject.presentation.screen.container

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.supabasetestproject.presentation.screen.main.MainFragment
import com.example.supabasetestproject.presentation.screen.photo_recognition.PhotoRecognitionFragment
import com.example.supabasetestproject.presentation.screen.speech_recognition.SpeechRecognitionFragment

class ContainerViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainFragment()
            1 -> SpeechRecognitionFragment()
            else -> PhotoRecognitionFragment()
        }
    }
}
