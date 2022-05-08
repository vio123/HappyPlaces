package com.mobiledev.happyplaces.fragments.ui.home
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.mobiledev.happyplaces.room.Place
import com.mobiledev.happyplaces.R
import com.mobiledev.happyplaces.adapters.MyAdapter
import com.mobiledev.happyplaces.databinding.FragmentHomeBinding
import com.mobiledev.happyplaces.fragments.ui.dialog.AddDialogFragment
import com.mobiledev.happyplaces.room.AppDatabase


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val myAdapter by lazy { MyAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.happyPlacesList.adapter = myAdapter
        binding.floatingActionButton.setOnClickListener {
            AddDialogFragment().show(childFragmentManager,"Add Fragment")
        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}