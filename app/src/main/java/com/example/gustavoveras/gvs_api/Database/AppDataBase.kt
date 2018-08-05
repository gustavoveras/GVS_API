package com.example.gustavoveras.gvs_api.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.gustavoveras.gvs_api.Dao.FilmDao
import com.example.gustavoveras.gvs_api.Film

@Database(entities = arrayOf(Film::class), version = 1, exportSchema = false)

abstract class AppDataBase : RoomDatabase(){

    abstract fun filmDao(): FilmDao

}