package com.winwang.moviehtml.ui.hot

import com.winwang.moviehtml.http.ApiService

class HomeRepository(private val apiService: ApiService) {

    suspend fun getTestData() {
        apiService.movieHome()
    }


}