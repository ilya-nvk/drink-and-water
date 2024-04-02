package com.ilyanvk.drinkwater.data.datasource.plants

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilyanvk.drinkwater.domain.model.Plant
import kotlinx.coroutines.flow.Flow

@Dao
interface GalleryDao {
    @Query("SELECT * FROM plant")
    fun getGrownPlants(): Flow<List<Plant>>

    @Query("SELECT * FROM plant WHERE id = :id")
    suspend fun getGrownPlantById(id: String): Plant?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrownPlant(plant: Plant)

    @Delete
    suspend fun deleteGrownPlant(plant: Plant)

    @Query("DELETE FROM plant")
    fun clear()
}
