package com.example.techburg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.techburg.databinding.FragmentChangeBinding

class ChangeFragment : Fragment() {
    private var str = ""
    private var _binding: FragmentChangeBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "null"
        }
    private val args by navArgs<ChangeFragmentArgs>()
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
        return FragmentChangeBinding.inflate(inflater,container,false)
            .also{
                _binding = it
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run{
            error.isVisible = false
            val user = dataBaseUser.findById(RegistrationFragment.ID)
            back.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            when(args.account){
                "email" -> {
                    str = "email"
                    text.text = "Email"
                    actualText.text = "Actual email address"
                    actual.text = user.login
                    newText.text = "New email address"
                    edit1.hint = "Email"
                    confirmText.text = "Confirm email address"
                    edit2.hint = "Email"
                }
                "password" -> {
                    text.text = "Password"
                    str = "password"
                    actualText.text = "Actual password"
                    actual.text = user.password
                    newText.text = "New password"
                    edit1.hint = "Password"
                    confirmText.text = "Confirm password"
                    edit2.hint = "Password"
                }
                "number" -> {
                    text.text = "Phone number"
                    str = "number"
                    actualText.text = "Actual phone number"
                    actual.text = "${user.number}"
                    newText.text = "New phone number"
                    edit1.hint = "Phone number"
                    confirmText.text = "Confirm phone number"
                    edit2.hint = "Phone number"
                }
            }
            btn.setOnClickListener {
                if (edit1.text.toString() == edit2.text.toString()) {
                    when (str) {
                        "email" -> dataBaseUser.updateLogin(
                            edit1.text.toString(),
                            RegistrationFragment.ID
                        )

                        "password" -> dataBaseUser.updatePassword(
                            edit1.text.toString(),
                            RegistrationFragment.ID
                        )

                        "number" -> dataBaseUser.updateNumber(
                            edit1.text.toString(),
                            RegistrationFragment.ID
                        )
                    }
                    btn.setBackgroundColor(R.drawable.selected)
                    btn.isVisible = false
                    btnInactive.isVisible = true
                    error.isVisible = false
                }
                else{
                    error.isVisible = true
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}