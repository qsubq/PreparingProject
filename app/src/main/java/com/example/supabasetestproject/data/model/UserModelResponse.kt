package com.example.supabasetestproject.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserModelResponse(
    val id: Int,
    val created_at: String,
    val name: String,
    val phone: String,
    val email: String,
    val balance: String,
    val transactions: Array<String>?,
    val rider: Boolean,
    val uid: String,
    val unread_count: Int,
    val avatar: String,
)
