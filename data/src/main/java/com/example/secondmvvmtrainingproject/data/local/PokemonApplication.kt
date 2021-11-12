package com.example.secondmvvmtrainingproject.data.local

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class PokemonApplication : Application() {
    companion object {
        lateinit var database: PokemonDatabase
    }

    override fun onCreate() {
        super.onCreate()

        val MIGRATION_2_3 = object: Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE PokemonEntity ADD COLUMN type TEXT NOT NULL DEFAULT ''")
            }
        }

        database = Room.databaseBuilder(this, PokemonDatabase::class.java, "PokemonDatabase").addMigrations(MIGRATION_2_3).build()
    }
}