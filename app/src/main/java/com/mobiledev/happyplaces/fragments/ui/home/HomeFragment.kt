package com.mobiledev.happyplaces.fragments.ui.home
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobiledev.happyplaces.adapters.MyAdapter
import com.mobiledev.happyplaces.databinding.FragmentHomeBinding
import com.mobiledev.happyplaces.fragments.ui.dialog.AddDialogFragment


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
            ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.happyPlacesList.adapter = myAdapter
        homeViewModel.allPlaces.observe(viewLifecycleOwner, Observer {list->
            list?.let {
                myAdapter.setData(it)
            }
        })
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