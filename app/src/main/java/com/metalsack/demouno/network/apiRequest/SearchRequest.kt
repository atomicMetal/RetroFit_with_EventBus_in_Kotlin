package com.metalsack.demouno.network.apiRequest

import com.metalsack.demouno.network.apiHandlers.ApiService
import com.metalsack.demouno.network.models.Result
import io.reactivex.Observable

class SearchRequest (val apiService: ApiService) {
    fun search(location: String, language: String): Observable<Result> {
        return apiService.search(query = "location:$location language:$language")
    }

    fun searchUsers(location: String, language: String): Observable<Result> {
        return apiService.searchUser(location,language)
    }

}
