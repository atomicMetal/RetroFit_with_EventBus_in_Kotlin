package com.metalsack.demouno.network.apiResponse


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable


/**
 * The abstract base class for all possible API responses. It contains all elements which are common
 * to all API responses.
 */
open class AbstractApiResponse : Serializable {

    @SerializedName("success")
    @Expose
    var isSuccess: Boolean = false

    @SerializedName("active")
    @Expose
    var isStatus: Boolean = false

    @SerializedName("message")
    @Expose
    var msg: String? = null
}
