package dev.qamar.a24itest.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import dev.qamar.a24itest.ui.MoviesViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun applicationContext(applicationContext: Context): Builder
        fun build(): ApplicationComponent
    }

    fun moviesViewModelFactory(): ViewModelFactory<MoviesViewModel>

}
