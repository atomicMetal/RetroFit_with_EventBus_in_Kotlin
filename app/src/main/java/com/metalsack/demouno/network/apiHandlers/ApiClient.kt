package com.metalsack.demouno.network.apiHandlers

import com.metalsack.demouno.network.apiRequest.RecommendedRequest
import com.metalsack.demouno.network.apiRequest.SearchRequest

/**
 * This class contains wrapper functions that return request for particular network call
 */
object ApiClient {

    public val apiService= ApiService.create()
    fun search(): SearchRequest {
        return SearchRequest(apiService)
    }
    fun getRecommendedUsers(): RecommendedRequest {
        return RecommendedRequest(apiService)
    }
}