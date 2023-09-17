package com.example.supabasetestproject.presentation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.supabasetestproject.R
import com.example.supabasetestproject.data.local_data_source.LocalRepositoryImpl
import com.example.supabasetestproject.presentation.screen.main.MyContextWrapper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun attachBaseContext(newBase: Context?) {
        val repositoryImpl = LocalRepositoryImpl(newBase!!)
        super.attachBaseContext(MyContextWrapper.wrap(newBase, repositoryImpl.getLanguage()))
    }
}
