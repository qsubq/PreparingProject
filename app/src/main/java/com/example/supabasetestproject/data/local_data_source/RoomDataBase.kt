package com.example.supabasetestproject.data.local_data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UsersEntities::class], version = 1)
abstract class RoomDataBase : RoomDatabase() {
    abstract fun getDao(): com.example.supabasetestproject.data.local_data_source.Dao

    companion object {
        var dataBase: RoomDataBase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDataBase {
            if (dataBase == null) {
                dataBase = Room.databaseBuilder(context, RoomDataBase::class.java, "db").build()
                return dataBase as RoomDataBase
            } else {
                return dataBase as RoomDataBase
            }
        }
    }
}
