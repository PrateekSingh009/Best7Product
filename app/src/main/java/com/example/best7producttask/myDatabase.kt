package com.example.best7producttask

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Entity::class],version=2)
abstract class myDatabase : RoomDatabase() {
    abstract fun dao():DAO
}