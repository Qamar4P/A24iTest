package dev.qamar.a24itest.ui

import android.app.AlertDialog
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import dev.qamar.a24itest.R

class MainActivity : AppCompatActivity() {
    private var handler: Handler  = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragContainer)
        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragContainer, MovieGridFragment())
                    .commit()
        }


//        handler.postDelayed(welcomeRunnable, 5000)
    }

    var welcomeRunnable = Runnable {
        AlertDialog.Builder(this)
                .setTitle("Welcome to Test App")
                .setView(R.layout.dialog_welcome)
                .setPositiveButton("OK", null).show()
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(welcomeRunnable)
    }
}
