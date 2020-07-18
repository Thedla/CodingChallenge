package com.codingChallenge.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingChallenge.models.CardsItem

@Dao
interface CardItemDao {

    @Query("SELECT * FROM cardsitem")
    suspend fun getAllCardItems(): List<CardsItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun InsertCardItems(cards:List<CardsItem>)
}