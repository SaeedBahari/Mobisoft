package com.utechia.depotchecklist.utils.networkMonitoring

import androidx.lifecycle.LiveData

/**
 * This liveData enabling network connectivity monitoring
 * @see NetworkStateHolder to get current connection state
 */
object NetworkEvents : LiveData<Event>() {

    internal fun notify(event: Event) {
        postValue(event)
    }

}