package android.sllamas.speedrunrecords

import android.app.Application

class SpeedrunRecordsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}