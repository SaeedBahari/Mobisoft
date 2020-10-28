package com.utechia.depotchecklist.network

enum class CurrentStatus {
    LOADING, ERROR, DONE,DONE_EMPTY,DONE_LOADED,SERVER_ERROR,SERVER_ERROR_400,SERVER_ERROR_401,SERVER_ERROR_404,SERVER_ERROR_500,INTERNET_ERROR,TIME_OUT_ERROR
}
enum class DatabaseStatus {
    LOADING, ERROR, DONE
}