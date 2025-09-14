package com.example.techburg

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.techburg.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "null"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentLoginBinding.inflate(inflater, container, false)
            .also {
                _binding = it
            }.root
    }

    private val dataBase: RoomUserDao by lazy {
        requireContext()
            .appDatabase
            .userDao()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run {
            btn.setOnClickListener {
                val user =
                    dataBase.checkUser(email.text.toString(), password.text.toString())
                if (user == null) {
                    findNavController().navigate(
                        LoginFragmentDirections.actionFragmentLoginToFragmentDialog("Incorrect data")
                    )
                } else {
                    if (user.login == email.text.toString() && user.password == password.text.toString()) {
                        RegistrationFragment.ID =
                            dataBase.findByName(email.text.toString()).id
                        findNavController().navigate(
                            LoginFragmentDirections.actionFragmentLoginToFragmentMain()
                        )
                    } else {
                        findNavController().navigate(
                            LoginFragmentDirections.actionFragmentLoginToFragmentDialog("Incorrect data")
                        )
                    }
                }
            }
            textview3.setOnClickListener {
                Log.d("TextView", "textview clicked")
                findNavController().navigate(
                    LoginFragmentDirections.actionFragmentLoginToFragmentRegistration()
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}