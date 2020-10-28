package ir.saeedbahari.mobisoft.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
//            .addHeader("apikey",PrefUtils(AppClass.mContext).userToken)
//            .addHeader("apikey","4f588b70")
        .build()
        return chain.proceed(request)
    }
}