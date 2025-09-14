package com.example.techburg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.techburg.databinding.FragmentRecommended2Binding

class RecommendedFragment2 : Fragment() {
    private var _binding: FragmentRecommended2Binding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "null"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentRecommended2Binding.inflate(inflater,container,false)
            .also{
                _binding = it
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run{

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}