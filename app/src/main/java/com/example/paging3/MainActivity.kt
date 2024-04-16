package com.example.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.models.ResponseListItem
import com.example.paging3.paging.DataPagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var adapter: DataPagingAdapter
    lateinit var dataViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //DataBinding - implement after the work done
        recyclerView = findViewById(R.id.dataList)
        adapter = DataPagingAdapter()
        dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        dataViewModel.list.observe(this, Observer {


            adapter.submitData(lifecycle, it)



        })
    }

}