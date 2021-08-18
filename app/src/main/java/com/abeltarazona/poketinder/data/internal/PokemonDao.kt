package com.abeltarazona.poketinder.data.internal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by AbelTarazona on 17/08/2021
 */

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: Pokemon)

    @Query("SELECT * FROM pokemon")
    suspend fun getAllPokemons() : List<Pokemon>

}