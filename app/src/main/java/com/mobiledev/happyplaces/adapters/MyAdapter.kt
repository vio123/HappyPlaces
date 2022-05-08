package com.mobiledev.happyplaces.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobiledev.happyplaces.DiffUtils.MyDiffUtil
import com.mobiledev.happyplaces.room.Place
import com.mobiledev.happyplaces.databinding.PlacesViewBinding


class MyAdapter:RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var oldPlaceList = emptyList<Place>()
    class MyViewHolder(val binding: PlacesViewBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(PlacesViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.placeImage.setImageResource(oldPlaceList[position].img)
        holder.binding.placeName.text = oldPlaceList[position].placeName
        holder.binding.location.text = oldPlaceList[position].location
    }

    override fun getItemCount(): Int {
        return oldPlaceList.size
    }
    fun setData(newPlaceList:List<Place>){
        val diffUtil = MyDiffUtil(oldPlaceList,newPlaceList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldPlaceList = newPlaceList
        diffResult.dispatchUpdatesTo(this)
    }
}