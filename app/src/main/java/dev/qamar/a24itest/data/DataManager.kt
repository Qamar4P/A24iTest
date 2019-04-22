package dev.qamar.a24itest.data

import dagger.Reusable
import dev.qamar.a24itest.data.model.ApiResponse
import dev.qamar.a24itest.data.model.Movie
import dev.qamar.a24itest.data.model.MovieRef
import io.reactivex.Flowable
import io.reactivex.internal.operators.flowable.FlowableSingle
import javax.inject.Inject

/**
 * Created by Qamar4P on 22/4/2019.
 * Islamabad, Pakistan.
 */
@Reusable
public class DataManager @Inject constructor(private val movieService: MovieService){

    //fixme this can be improved to better UX
    fun movies(page: Int): Flowable<Movie>{
        return movieService.movies(page)
            .map { it.results }
            .flatMapIterable { it }
            .filter{!it.adult!!}
            .map { movieService.movie(it.id!!).blockingFirst() }
    }

}