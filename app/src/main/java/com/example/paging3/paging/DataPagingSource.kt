package com.example.paging3.paging

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.*
import androidx.paging.PagingState
import com.example.paging3.models.ResponseList
import com.example.paging3.models.ResponseListItem
import com.example.paging3.retrofit.PagingAPI
import com.google.gson.JsonParser
import org.json.JSONArray
import java.security.interfaces.ECKey


class DataPagingSource(val pagingAPI: PagingAPI) : PagingSource<Int, ResponseListItem>() {

    private val INITIAL_LOAD_SIZE = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseListItem> {
            val startPosition = params.key ?: INITIAL_LOAD_SIZE
            val response = pagingAPI.getData(start = startPosition, limit = 10)
            val endPosition = startPosition + response.size
        return try{
            Page(
                data = response,
                prevKey = if (startPosition == 0) null else startPosition - params.loadSize,
                nextKey = if (response.isEmpty()) null else endPosition
            )
        }catch (e:Exception){
            return Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, ResponseListItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}