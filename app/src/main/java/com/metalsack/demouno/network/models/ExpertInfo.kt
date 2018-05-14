package com.metalsack.demouno.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by android2 on 21/4/17.
 */

class ExpertInfo {

    @SerializedName("audio")
    @Expose
    var audio: String? = null
    @SerializedName("video")
    @Expose
    var video: String? = null
    @SerializedName("chat")
    @Expose
    var chat: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("quickblox_id")
    @Expose
    var quickbloxId: String? = null
    @SerializedName("owner_id")
    @Expose
    var ownerId: String? = null
    @SerializedName("login")
    @Expose
    var login: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("user_id")
    @Expose
    var userId: String? = null
    @SerializedName("full_name")
    @Expose
    var fullName: String? = null
    @SerializedName("profile_pic")
    @Expose
    var profilePic: String? = null
    @SerializedName("age")
    @Expose
    var age: String? = null
    @SerializedName("gender")
    @Expose
    var gender: String? = null
    @SerializedName("city")
    @Expose
    var city: String? = null
    @SerializedName("state")
    @Expose
    var state: String? = null
    @SerializedName("mob_no")
    @Expose
    var mobNo: String? = null

    var unreadMsgCount: Int = 0
}
