package dev.qamar.a24itest.data

import dev.qamar.a24itest.data.model.ApiResponse
import dev.qamar.a24itest.data.model.Movie
import dev.qamar.a24itest.data.model.MovieRef
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/changes")
    fun movies(@Query("page") page: Int): Flowable<ApiResponse<List<MovieRef>>>

    @GET("movie/{movie_id}")
    fun movie(@Path("movie_id") movieId: Int): Flowable<Movie>
}