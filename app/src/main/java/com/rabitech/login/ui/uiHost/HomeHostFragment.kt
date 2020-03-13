package com.rabitech.login.ui.uiHost


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.rabitech.login.R
import com.rabitech.login.databinding.FragmentHomeHostBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeHostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home_host, container, false)
        val binding : FragmentHomeHostBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_host, container, false
        )
        return binding.root
    }


}
