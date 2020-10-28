package ir.saeedbahari.amoozeshgah.utils.networkMonitoring.core

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import com.utechia.depotchecklist.utils.networkMonitoring.Event
import com.utechia.depotchecklist.utils.networkMonitoring.NetworkEvents
import com.utechia.depotchecklist.utils.networkMonitoring.NetworkState

/**
 * This is a static implementation of NetworkState, it holds the network states and is editable but it's only usable from this file.
 */
internal class NetworkStateImp : NetworkState {

    override var isConnected: Boolean = false
        set(value) {
            field = value
            NetworkEvents.notify(
                Event.ConnectivityEvent(
                    this
                )
            )
        }

    override var network: Network? = null

    override var linkProperties: LinkProperties? = null
        set(value) {
            val old = field
            field = value
            NetworkEvents.notify(
                Event.LinkPropertyChangeEvent(
                    this,
                    old
                )
            )
        }

    override var networkCapabilities: NetworkCapabilities? = null
        set(value) {
            val old = field
            field = value
            NetworkEvents.notify(
                Event.NetworkCapabilityEvent(
                    this,
                    old
                )
            )

        }
}