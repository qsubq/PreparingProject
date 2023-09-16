package com.example.supabasetestproject.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val name: String,
    val phone: String,
    val email: String,
    val balance: String,
    val transactions: Array<String>?,
    val rider: Boolean,
    val unread_count: Int,
    val avatar: String,
)
