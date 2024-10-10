package com.graviton.beerwithbros2.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDAO {
    @Query("SELECT * FROM beers")
    fun getAllBeers(): Flow<List<BeerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBeers(beers: List<BeerEntity>)

}