package com.example.roomdatabase.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DAO {
    @Insert
    suspend fun insert(entity:Entity)

    @Update
    suspend fun update(entity:Entity)

    @Delete
    suspend fun delete(entity:Entity)

    @Query("SELECT * FROM Note_App ORDER BY id DESC")
    suspend fun getAll():List<Entity>

    @Insert //ADD MULTIPLE VALUES AT ONCES
    suspend fun insertMultipleNoted(vararg entity: Entity)
}