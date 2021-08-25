package com.abeltarazona.poketinder.data.internal

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by AbelTarazona on 17/08/2021
 */

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idPokemon: String,
    val name: String,
    val image: String,
    val isLegendary: Boolean = false,
    val position: Int = -1
)
