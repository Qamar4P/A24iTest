package dev.qamar.a24itest

import dev.qamar.a24itest.data.DataManager
import dev.qamar.a24itest.data.MovieService
import dev.qamar.a24itest.di.RetrofitModule
import dev.qamar.a24itest.ui.MoviesViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

/**
 * Created by Qamar4P on 18/4/2019.
 * Islamabad, Pakistan.
 */
class MoviesApiTest {

    private lateinit var service: MovieService
    private lateinit var dataManger: DataManager

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        service = RetrofitModule.provideBooksService()
        dataManger = DataManager(service);
    }

    @Test
    fun getMovies_pageOne() {

        service.movies(1)
        .test().awaitCount(1).assertValueCount(1).assertNoErrors();
    }
}
