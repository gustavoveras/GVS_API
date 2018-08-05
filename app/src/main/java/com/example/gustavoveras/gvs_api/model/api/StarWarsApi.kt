package com.example.gustavoveras.gvs_api.model.api
import com.example.gustavoveras.gvs_api.model.Character
import com.example.gustavoveras.gvs_api.model.Movie
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import java.util.*

class StarWarsApi {
    val service: StarWarsApiDef

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://swapi.co/api/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

        service = retrofit.create<StarWarsApiDef>(StarWarsApiDef::class.java)
    }

    fun loadMovies(): Observable<Movie>? {
        return service.listMovies()
                .flatMap { filmResults -> Observable.from(filmResults.results) }
                .map { film ->
                    Movie(film.title, film.episodeId, ArrayList<Character>())
                }
    }
}
