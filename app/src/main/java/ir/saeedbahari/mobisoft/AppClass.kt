package ir.saeedbahari.mobisoft

import android.app.Application
import android.content.Context
import ir.saeedbahari.amoozeshgah.utils.networkMonitoring.NetworkStateHolder.registerConnectivityBroadcaster


class AppClass : Application() {
    companion object {
        private var INSTANCE: AppClass? = null
        lateinit var context: Context
        private var singleton: AppClass? = null
        fun getInstance(): AppClass {
            if (singleton == null)
                singleton = AppClass()

            return singleton as AppClass
        }

    }


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        context = applicationContext
        registerConnectivityBroadcaster()
    }




}