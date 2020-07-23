package com.codingChallenge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CardItemEntity::class),exportSchema = false,version = 7)
abstract class AppDataBase : RoomDatabase() {

    abstract fun cardItemDao(): CardItemDao

    companion object{

        @Volatile
        var AppDataBaseInstance: AppDataBase ?= null

        /**Get the data base
         * @param context
         * @return AppDataBase instance
         * **/
        fun getAppDataBase(context: Context):AppDataBase{
            synchronized(this) {
                var instance = AppDataBaseInstance
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDataBase::class.java, "database-name"
                    ).fallbackToDestructiveMigration().build()
                    AppDataBaseInstance = instance
                }
                return instance
            }
        }
    }
}