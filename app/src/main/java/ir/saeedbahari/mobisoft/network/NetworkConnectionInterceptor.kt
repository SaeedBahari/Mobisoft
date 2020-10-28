package ir.saeedbahari.mobisoft.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*


class NetworkConnectionInterceptor(val context: Context) :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            throw NoConnectivityException()
            // Throwing our custom exception 'NoConnectivityException'
        }

        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    fun isConnected(): Boolean { // چک کردن اتصال دستگاه ب اینترنت
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        if (vpn())
            result=true

        return result
    }

    fun vpn(): Boolean { // چک کردن اتصال دستگاه به vpn
        var iface = ""
        try {
            for (networkInterface in Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp()) iface = networkInterface.getName()
                Log.d("DEBUG", "IFACE NAME: $iface")
                if (iface.contains("tun") || iface.contains("ppp") || iface.contains("pptp")) {
                    return true
                }
            }
        } catch (e1: SocketException) {
            e1.printStackTrace()
        }
        return false
    }
}