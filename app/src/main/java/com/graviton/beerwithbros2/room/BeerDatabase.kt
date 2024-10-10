package com.graviton.beerwithbros2.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BeerEntity::class], version = 2)
abstract class BeerDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDAO
}