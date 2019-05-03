package dev.qamar.a24itest

import android.arch.core.executor.testing.InstantTaskExecutorRule
import dev.qamar.a24itest.data.DataManager
import dev.qamar.a24itest.data.MovieService
import dev.qamar.a24itest.data.model.Movie
import dev.qamar.a24itest.di.RetrofitModule
import dev.qamar.a24itest.ui.MoviesViewModel
import io.reactivex.internal.util.BlockingHelper
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch
import org.junit.rules.TestRule
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert


/**
 * Created by Qamar4P on 18/4/2019.
 * Islamabad, Pakistan.
 */
@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var service: MovieService
    private lateinit var dataManger: DataManager
    private lateinit var moviesViewModel: MoviesViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.reset();
        RxJavaPlugins.reset();
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
        service = RetrofitModule.provideBooksService()
        dataManger = DataManager(service);
        moviesViewModel = MoviesViewModel(dataManger)
    }

    @Test
    fun getMovies() {
        moviesViewModel.loadMovies(1)
        Thread.sleep(5000)

        Assert.assertTrue(moviesViewModel.loading.value!! == false)
        Assert.assertTrue(moviesViewModel.movies.value?.size!! > 0)
    }
}
