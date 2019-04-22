package dev.qamar.a24itest

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import dev.qamar.a24itest.di.ApplicationComponent
import dev.qamar.a24itest.di.DaggerApplicationComponent
import dev.qamar.a24itest.di.DaggerComponentProvider

/**
 * Created by Qamar4P on 22/4/2019.
 * Islamabad, Pakistan.
 */
public class App:Application(), DaggerComponentProvider {

    override val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationContext(applicationContext)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

}