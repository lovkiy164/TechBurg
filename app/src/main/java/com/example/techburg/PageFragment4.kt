package com.example.techburg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.techburg.databinding.FragmentPage4Binding

class PageFragment4 : Fragment() {
    private var _binding: FragmentPage4Binding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "null"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentPage4Binding.inflate(inflater,container,false)
            .also{
                _binding = it
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run{
            accountInformation.setOnClickListener {
                findNavController().navigate(
                    PageFragment4Directions.actionFragment4ToFragmentInformation()
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}