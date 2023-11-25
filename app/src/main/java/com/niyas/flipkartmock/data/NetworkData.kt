package com.niyas.flipkartmock.data


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class ResponseClass(
    @SerializedName("exception")
    @Expose
    var exception: ExceptionClass? = null,
    @SerializedName("pagination")
    @Expose
    var pagination: PaginationClass? = null,
    @SerializedName("status")
    @Expose
    var status: String? = null,
    @SerializedName("statusCode")
    @Expose
    var statusCode: Int? = null,

    @SerializedName("errorCode")
    @Expose
    var errorCode: Int? = null,
    @SerializedName("message")
    @Expose
    var message: String? = null

) {
    val isSuccessFul = statusCode in 200..209
}

data class ExceptionClass(
    @SerializedName("shortMessage")
    @Expose
    private val shortMessage: String,
    @SerializedName("detailMessage")
    @Expose
    private val detailMessage: String,
    @SerializedName("languageCode")
    @Expose
    private val languageCode: Int,
    @SerializedName("errorCode")
    @Expose
    private val errorCode: Int,
    @SerializedName("fieldErrors")
    @Expose
    private val fieldErrors: Any,
    @SerializedName("message")
    @Expose
    private val message: Int,
    @SerializedName("localizedMessage")
    @Expose
    private val localizedMessage: String

) {
    override fun toString(): String {
        return "'$shortMessage $detailMessage $languageCode $errorCode $fieldErrors $message $localizedMessage"
    }
}


data class PaginationClass(
    @SerializedName("totalElements")
    @Expose
    private var totalElements: Int = 0,
    @SerializedName("pageSize")
    @Expose
    private var pageSize: Int = 0,
    @SerializedName("pageNo")
    @Expose
    private var pageNo: Int = 0,
    @SerializedName("totalPages")
    @Expose
    private var totalPages: Int = 0
)
