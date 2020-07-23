package com.codingChallenge.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardItemEntity(@PrimaryKey(autoGenerate = true) val id:Int?, @ColumnInfo(name="cards")val cards: String)