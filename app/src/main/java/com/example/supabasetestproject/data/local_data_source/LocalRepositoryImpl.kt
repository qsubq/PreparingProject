package com.example.supabasetestproject.data.local_data_source

import android.content.Context

class LocalRepositoryImpl(context: Context) {
    val sharedPref = context.getSharedPreferences("isAlready", Context.MODE_PRIVATE)
}
