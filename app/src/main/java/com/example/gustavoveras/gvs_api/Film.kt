package com.example.gustavoveras.gvs_api

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Film(
        @PrimaryKey(autoGenerate = true)
        val  title: String,
        val  episode_id: Int,
        val  Characters: String)