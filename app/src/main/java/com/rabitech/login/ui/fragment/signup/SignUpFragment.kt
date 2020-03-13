package com.rabitech.login.ui.fragment.signup


import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.rabitech.login.R
import com.rabitech.login.databinding.FragmentSignUpBinding
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.text_email
import kotlinx.android.synthetic.main.fragment_sign_up.*

/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSignUpBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sign_up, container, false
        )

        mAuth = FirebaseAuth.getInstance()

        binding.buttonRegister.setOnClickListener {
            validateInputs()
        }
        binding.textLoginSignup.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_signUpFragment_to_loginFragment)
        )

        return binding.root
    }

    private fun validateInputs() {
        val userEmail = text_email.text.toString()
        val userPassword = text_password.text.toString()

        if (userEmail.isEmpty()) {
            text_email.error = " Please enter email address"
            text_email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            text_email.error = "pleaese eneter valid email address"
            text_email.requestFocus()
            return
        }

        if (userPassword.isEmpty()) {
            text_password.error = " Please enter password"
            text_password.requestFocus()
            return
        }

        mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener {
                if (it.isSuccessful) {

                    Toast.makeText(activity, "User successfully created", Toast.LENGTH_SHORT).show()

                    findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)

                } else {
                    Toast.makeText(activity, "User creation failed", Toast.LENGTH_SHORT).show()

                }
            }.addOnFailureListener {
                Toast.makeText(activity, "Login failed" + it.message, Toast.LENGTH_LONG).show()
            }
    }




}
