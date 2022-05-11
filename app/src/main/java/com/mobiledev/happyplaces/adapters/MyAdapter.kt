package com.mobiledev.happyplaces.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobiledev.happyplaces.DiffUtils.MyDiffUtil
import com.mobiledev.happyplaces.EditActivity
import com.mobiledev.happyplaces.databinding.PlacesViewBinding
import com.mobiledev.happyplaces.model.Place


class MyAdapter:RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var oldPlaceList = emptyList<Place>()
    private lateinit var context:Context
    class MyViewHolder(val binding: PlacesViewBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder(PlacesViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.placeImage.setImageBitmap(oldPlaceList[position].img)
        holder.binding.placeName.text = oldPlaceList[position].placeName
        holder.binding.location.text = oldPlaceList[position].adress
        if(!oldPlaceList[position].fav){
            holder.binding.favourite.setColorFilter(Color.GRAY)
        }else{
            holder.binding.favourite.setColorFilter(Color.RED)
        }
        holder.binding.root.setOnLongClickListener{
            val intent = Intent(context,EditActivity::class.java)
            intent.putExtra("list",oldPlaceList[position] as Parcelable)
            return@setOnLongClickListener true
        }
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