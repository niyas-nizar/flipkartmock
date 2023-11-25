package com.niyas.flipkartmock.data

data class UIModel<out T>(
    val status: EStatus,
    val data: T?,
    val message: String?,
    var type: Any? = null
) {

    companion object {
        fun <T> success(data: T?, type: Any? = null): UIModel<T> {
            return UIModel(EStatus.SUCCESS, data, null, type)
        }

        fun <T> error(msg: String?, data: T?): UIModel<T> {
            return UIModel(EStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): UIModel<T> {
            return UIModel(EStatus.LOADING, data, null)
        }

        fun <T> logOut(): UIModel<T> {
            return UIModel(EStatus.LOGOUT_USER, null, null)
        }
    }
}

enum class EStatus {
    SUCCESS,
    ERROR,
    LOADING,
    LOGOUT_USER
}