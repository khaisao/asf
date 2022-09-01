package com.shoes.demorxjava

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shoes.demorxjava.base.BaseFragment
import com.shoes.demorxjava.databinding.FragmentBlankBinding


class BlankFragment : BaseFragment<FragmentBlankBinding>(FragmentBlankBinding::inflate){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

}