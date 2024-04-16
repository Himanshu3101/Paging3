package com.example.paging3.retrofit

import com.example.paging3.models.ResponseList
import com.example.paging3.models.ResponseListItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface PagingAPI {

    @GET("/posts")
    suspend fun getData( @Query("_start") start: Int,
                         @Query("_limit") limit: Int) : ResponseList
}