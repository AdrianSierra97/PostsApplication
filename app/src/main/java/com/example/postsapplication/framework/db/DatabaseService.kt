package com.example.postsapplication.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PostEntity::class], version = 1, exportSchema = false)
abstract class DatabaseService: RoomDatabase() {
    abstract fun postDao(): PostDao

    companion object {

        @Volatile
        private var INSTANCE: DatabaseService? = null

        fun getDataBaseService(context: Context): DatabaseService {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseService::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}