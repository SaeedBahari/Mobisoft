package ir.saeedbahari.mobisoft.network

import ir.saeedbahari.mobisoft.AppClass
import ir.saeedbahari.mobisoft.R
import java.io.IOException

class NoConnectivityException :IOException(){
    override fun getLocalizedMessage(): String? {
        return AppClass.getInstance().getString(R.string.error_device_not_connected_to_internet)
    }
}