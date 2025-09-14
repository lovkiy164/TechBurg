package com.example.techburg

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.techburg.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment(){
    private var _binding: FragmentRegistrationBinding? = null
    private val binding
        get() = requireNotNull(_binding){
            "null"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentRegistrationBinding.inflate(inflater,container,false)
            .also{
                _binding = it
            }.root
    }

    private val dataBase: RoomUserDao by lazy{
        requireContext()
            .appDatabase
            .userDao()
    }

    companion object{
        var ID: Long = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run{
            btn.setOnClickListener {
                val name = dataBase.findByName(email.text.toString())
                if (confirmPassword.text.toString() == password.text.toString()) {
                    if (name==null) {
                        val login = email.text.toString()
                        val password = confirmPassword.text.toString()
                        Log.d("User", "$login $password")
                        dataBase
                            .insertAll(Roomuser(login = login, password = password, history = "", viewHistory = "", number = ""))
                        ID = dataBase.findByName(email.text.toString()).id
                        findNavController().navigate(
                           RegistrationFragmentDirections.actionFragmentRegistrationToFragmentMain()
                        )
                    }
                    else{
                        findNavController().navigate(
                            RegistrationFragmentDirections.actionFragmentRegistrationToFragmentDialog("This login is already used")
                        )
                    }
                }
                else{
                    findNavController().navigate(
                        RegistrationFragmentDirections.actionFragmentRegistrationToFragmentDialog("Passwords aren't same")
                    )
                }
            }
            textview3.setOnClickListener {
                Log.d("TextView","textview clicked")
                findNavController().navigate(
                    RegistrationFragmentDirections.actionFragmentRegistrationToFragmentLogin()
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}