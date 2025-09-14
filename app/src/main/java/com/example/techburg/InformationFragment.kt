package com.example.techburg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.techburg.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {
    private var _binding: FragmentInformationBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "null"
        }

    private val dataBaseUser: RoomUserDao by lazy{
        requireContext()
            .appDatabase
            .userDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentInformationBinding.inflate(inflater,container,false)
            .also{
                _binding = it
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run{
            val user = dataBaseUser.findById(RegistrationFragment.ID)
            email.text = user.login
            var passwordSymbols = ""
            for(i in 0 ..user.password.length-1){
                passwordSymbols += "*"
            }
            password.text = passwordSymbols
            number.text = user.number.toString()
            emailChange.setOnClickListener {
                findNavController().navigate(
                    InformationFragmentDirections.actionFragmentInformationToFragmentChange("email")
                )
            }
            passwordChange.setOnClickListener {
                findNavController().navigate(
                    InformationFragmentDirections.actionFragmentInformationToFragmentChange("password")
                )
            }
            numberChange.setOnClickListener {
                findNavController().navigate(
                    InformationFragmentDirections.actionFragmentInformationToFragmentChange("number")
                )
            }
            back.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}