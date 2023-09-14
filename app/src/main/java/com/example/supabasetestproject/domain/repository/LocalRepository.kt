package com.example.supabasetestproject.domain.repository

interface LocalRepository {
    fun setIsAlreadySeenOnBoarding()
    fun isAlreadySeenOnBoarding(): Boolean
}