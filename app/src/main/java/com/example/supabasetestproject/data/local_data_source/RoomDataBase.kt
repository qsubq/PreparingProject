package com.example.supabasetestproject.data.local_data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UsersEntities::class], version = 1)
abstract class RoomDataBas : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {
        var dataBase: RoomDataBas? = null

        @Synchronized
        fun getInstance(context: Context): RoomDataBas {
            return if (dataBase == null) {
                dataBase = Room.databaseBuilder(context, RoomDataBas::class.java, "db").build()
                dataBase as RoomDataBas
            } else {
                dataBase as RoomDataBas
            }
        }
    }
}
