package com.abeltarazona.poketinder.data.internal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by AbelTarazona on 17/08/2021
 */

@Database(entities = [Pokemon::class], exportSchema = false, version = 2)
abstract class PokemonDB : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {
        var instance: PokemonDB? = null

        fun provideDB(context: Context): PokemonDB {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        PokemonDB::class.java,
                        "pokemon_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance!!
        }
    }

}