package com.example.techburg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.techburg.databinding.FragmentStartBinding

class StartFragment : Fragment(){
    private var _binding: FragmentStartBinding? = null
    private val binding
        get() = requireNotNull(_binding){
            "null"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentStartBinding.inflate(inflater,container,false)
            .also{
                _binding = it
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run{
            btn1.setOnClickListener {
                findNavController().navigate(
                    StartFragmentDirections.actionFragmentStartToFragmentLogin()
                )
            }
            btn2.setOnClickListener {
                findNavController().navigate(
                    StartFragmentDirections.actionFragmentStartToFragmentRegistration()
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}