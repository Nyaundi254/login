package com.rabitech.login.ui.fragment.login


import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rabitech.login.R
import com.rabitech.login.databinding.FragmentLoginBinding
import com.rabitech.login.ui.uiHost.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.text_email
import kotlinx.android.synthetic.main.fragment_sign_up.*


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var mAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        mAuth = FirebaseAuth.getInstance()
        checkUserSession()

        binding.btnLogin.setOnClickListener {
            validateInputs()
        }
        binding.textCreateAccount.setOnClickListener { view: View ->
            findNavController()
                .navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.textForgotPassword.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_resetPassword)
        }
        return binding.root
    }


    private fun checkUserSession() {
        if (mAuth.currentUser != null) {
            findNavController().navigate(R.id.action_loginFragment_to_homeHostFragment)
        }
    }

    private fun validateInputs() {
        val userEmail = text_email.text.toString()
        val userPassword = text_pass.text.toString()

        if (userEmail.isEmpty()) {
            text_email.error = " Please enter email address"
            text_email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            text_email.error = "Pleaese eneter valid email address"
            text_email.requestFocus()
            return
        }

        if (userPassword.isEmpty()) {
            text_pass.error = "Please enter password"
            text_pass.requestFocus()
            return
        }

        mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(activity, "Login Success", Toast.LENGTH_LONG).show()

                findNavController().navigate(R.id.action_loginFragment_to_homeHostFragment)

            } else {
                Toast.makeText(activity, "Login Failed", Toast.LENGTH_LONG).show()

            }

        }.addOnFailureListener {
            Toast.makeText(
                activity,
                "Login failed, check network connection" + it.message,
                Toast.LENGTH_LONG
            ).show()

        }

    }


}
