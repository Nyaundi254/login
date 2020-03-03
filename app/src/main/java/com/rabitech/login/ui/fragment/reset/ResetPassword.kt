package com.rabitech.login.ui.fragment.reset


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.rabitech.login.R
import com.rabitech.login.databinding.FragmentResetPasswordBinding

/**
 * A simple [Fragment] subclass.
 */
class ResetPassword : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_reset_password, container, false)
        val binding: FragmentResetPasswordBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_reset_password, container, false
        )
        return binding.root
    }


}
