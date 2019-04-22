package dev.qamar.a24itest.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.VisibleForTesting
import android.util.Log
import dev.qamar.a24itest.data.DataManager
import dev.qamar.a24itest.data.model.Movie
import dev.qamar.a24itest.utils.plusAssign

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesViewModel  @Inject constructor(private val dataManager: DataManager): ViewModel() {

    private val disposable = CompositeDisposable()

    @get:VisibleForTesting
    val movies = MutableLiveData<ArrayList<Movie>>()
    val loading = MutableLiveData<Boolean>()

    @VisibleForTesting
    fun loadMovies(page: Int) {
        movies.value = arrayListOf()
        loading.value = true
        disposable.add(dataManager.movies(page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .buffer(4)
            .subscribe({
                loading.value = false
                this.movies += it
            },{ error ->
                loading.value = false
                error.printStackTrace()
                Log.e(TAG, "Unable to get movies", error)
            }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    companion object {
        private val TAG = MoviesViewModel::class.java.simpleName
    }
}
