package com.example.supabasetestproject.data.local_data_source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsersEntities(
    @PrimaryKey
    val id: Int,

    @ColumnInfo
    val name: String,
)
