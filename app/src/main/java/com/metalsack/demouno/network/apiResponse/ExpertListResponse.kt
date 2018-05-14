package com.metalsack.demouno.network.apiResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.metalsack.demouno.network.models.ExpertInfo

import java.util.ArrayList

class ExpertListResponse : AbstractApiResponse() {

    @SerializedName("info")
    @Expose
    lateinit var expertInfo: ArrayList<ExpertInfo>
}
