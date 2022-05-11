package com.mobiledev.happyplaces.fragments.ui.share

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobiledev.happyplaces.R

class ShareFragment : Fragment() {

    companion object {
        fun newInstance() = ShareFragment()
    }

    private lateinit var viewModel: ShareViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.share_fragment, container, false)
    }
}