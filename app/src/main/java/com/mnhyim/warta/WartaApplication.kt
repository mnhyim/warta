package com.mnhyim.warta

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class WartaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        /* TODO: can moves to utils modules i guess? */
        Timber.plant(Timber.DebugTree())
    }
}