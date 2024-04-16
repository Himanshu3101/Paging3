package com.example.paging3.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.R
import com.example.paging3.models.ResponseList
//import com.example.paging3.models.ResponseList
import com.example.paging3.models.ResponseListItem

class DataPagingAdapter : PagingDataAdapter<ResponseListItem, DataPagingAdapter.DataViewHolder>(
    COMPARATOR) {

    //DataBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.data_item,parent,false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.dataId.text = item/*.get(position)*/.id.toString()
            holder.dataTitle.text = item/*.get(position)*/.title
        }
    }


    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val dataId = itemView.findViewById<AppCompatTextView>(R.id.data_id)
        val dataTitle = itemView.findViewById<AppCompatTextView>(R.id.data_title)
    }

    companion object{
        private val COMPARATOR = object: DiffUtil.ItemCallback<ResponseListItem>(){
            override fun areItemsTheSame(
                oldItem: ResponseListItem,
                newItem: ResponseListItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponseListItem,
                newItem: ResponseListItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}