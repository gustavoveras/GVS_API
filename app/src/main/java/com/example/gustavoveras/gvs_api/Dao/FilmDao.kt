package com.example.gustavoveras.gvs_api.Dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.gustavoveras.gvs_api.Film

@Dao
interface FilmDao{

    @Query("SELECT * FROM film")
    fun all(): List<Film>

    @Insert
    fun  add(vararg film: Film)
}