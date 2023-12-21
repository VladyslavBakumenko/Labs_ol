package com.example.labs_ol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewFragment
import androidx.fragment.app.Fragment
import com.example.labs_ol.databinding.FirstFragmentBinding
import com.example.labs_ol.databinding.SecondFragmentBinding

class SecondFragment: Fragment() {

    private var binding: SecondFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SecondFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.button?.setOnClickListener {
            onClickInterface?.onClick()
        }
        binding?.vebViewButton?.setOnClickListener {
            onClickInterface?.onClickWebView()
        }
    }

    interface OnClick {
        fun onClick()
        fun onClickWebView()
    }

    companion object {
        var onClickInterface: OnClick? = null
    }
}