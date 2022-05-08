package com.mobiledev.happyplaces.fragments.ui.dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.mobiledev.happyplaces.databinding.FragmentAddDialogBinding


class AddDialogFragment:DialogFragment() {
    private var _binding: FragmentAddDialogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddDialogBinding.inflate(inflater, container, false)
        binding.addBtn.setOnClickListener {
            Toast.makeText(context,binding.namePlaces.text,Toast.LENGTH_SHORT).show()
        }
         return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}