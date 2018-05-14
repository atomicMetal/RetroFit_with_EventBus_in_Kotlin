package com.metalsack.demouno.network.apiRequest

import com.metalsack.demouno.network.apiHandlers.ApiService
import com.metalsack.demouno.network.apiResponse.ExpertListResponse
import io.reactivex.Observable



/**This class contains functions that consume callback and its called from Api client */
class RecommendedRequest (val apiService: ApiService) {
    fun getRecommendedUser(): Observable<ExpertListResponse> {
        return apiService.getRecommendedUser()
    }
}
