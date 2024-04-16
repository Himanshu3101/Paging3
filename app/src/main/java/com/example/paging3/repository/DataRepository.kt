package com.example.paging3.repository

import android.graphics.pdf.PdfDocument.Page
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.paging3.models.ResponseListItem
import com.example.paging3.paging.DataPagingSource
import com.example.paging3.retrofit.PagingAPI
import javax.inject.Inject

class DataRepository @Inject constructor(val api: PagingAPI) {

    fun getData(): LiveData<PagingData<ResponseListItem>> = Pager(
        config = PagingConfig(pageSize = 10,  prefetchDistance = 5, enablePlaceholders = false, maxSize = 50),
        pagingSourceFactory = { DataPagingSource(api) }
    ).liveData

}