package com.example.gustavoveras.gvs_api

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.gustavoveras.gvs_api.Database.AppDataBase
import com.example.gustavoveras.gvs_api.model.api.StarWarsApi
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var listView : ListView
    lateinit var movieAdapter : ArrayAdapter<String>
    var movies = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listView = ListView(this)
        setContentView(listView)
        movieAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, movies)
        listView.adapter = movieAdapter

        val api = StarWarsApi()
        api.loadMovies()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe ({ movie ->
            movies.add("${movie.title} -- ${movie.episodeId}")
        }, { e ->
            e.printStackTrace()
        },{
            movieAdapter.notifyDataSetChanged()
        })

        val database = Room.databaseBuilder(this, AppDataBase::class.java, "meubanco").allowMainThreadQueries().build()

        var filmDao = database.filmDao()

        filmDao.add(Film(title = "Teste Filme" , episode_id = 2, Characters = "Luke"))

        filmDao.all()
    }


}
