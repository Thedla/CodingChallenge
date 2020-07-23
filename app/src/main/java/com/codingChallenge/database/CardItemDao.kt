package com.codingChallenge.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CardItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCard(cards:CardItemEntity)

    @Query("SELECT cards FROM CardItemEntity limit 1")
    fun getAll(): LiveData<String>

    @Query("Delete FROM CardItemEntity ")
    suspend fun deleteAll()

    @Transaction
    suspend fun insertData(cards: CardItemEntity){
        deleteAll()
        insertCard(cards)
    }
}