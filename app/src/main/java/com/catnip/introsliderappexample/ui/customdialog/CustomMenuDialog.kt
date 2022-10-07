package com.catnip.introsliderappexample.ui.customdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.catnip.introsliderappexample.R
import com.catnip.introsliderappexample.databinding.DialogCustomMenuBinding


class CustomMenuDialog : DialogFragment() {

    private lateinit var binding: DialogCustomMenuBinding

    private var listener: OnMenuSelectedListener? = null

    fun setOnMenuSelectedListener(listener: OnMenuSelectedListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DialogCustomMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivPlayer.setOnClickListener {
            listener?.onVsPlayerClicked(this)
        }
        binding.ivComputer.setOnClickListener {
            listener?.onVsComputerClicked(this)
        }
    }

}

interface OnMenuSelectedListener {
    fun onVsPlayerClicked(dialog : DialogFragment)
    fun onVsComputerClicked(dialog : DialogFragment)
}