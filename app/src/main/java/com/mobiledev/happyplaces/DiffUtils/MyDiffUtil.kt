package com.mobiledev.happyplaces.DiffUtils

import androidx.recyclerview.widget.DiffUtil
import com.mobiledev.happyplaces.model.Place

class MyDiffUtil(
    private val oldList:List<Place>,
    private val newList:List<Place>
):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
       return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].placeName == newList[newItemPosition].placeName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return when{
           oldList[oldItemPosition].placeName != newList[newItemPosition].placeName -> {
               false
           }
           oldList[oldItemPosition].img != newList[newItemPosition].img ->{
               false
           }
           oldList[oldItemPosition].dateTime != newList[newItemPosition].dateTime -> {
               false
           }
           oldList[oldItemPosition].activityType != newList[newItemPosition].activityType -> {
               false
           }
           oldList[oldItemPosition].placeType != newList[newItemPosition].placeType -> {
               false
           }
           oldList[oldItemPosition].note != newList[newItemPosition].note -> {
               false
           }
           else ->
               true
       }
    }
}