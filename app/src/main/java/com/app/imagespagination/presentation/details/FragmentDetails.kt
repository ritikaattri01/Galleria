package com.app.imagespagination.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.app.imagespagination.R
import com.app.imagespagination.databinding.FragmentDetailsBinding
import com.bumptech.glide.Glide

class FragmentDetails : Fragment() {

    val args: FragmentDetailsArgs by navArgs()

    private var _binding: FragmentDetailsBinding? = null

    private val binding: FragmentDetailsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        if (arguments != null) {
            binding.name.text = args.data?.author
            Glide.with(this).load(args.data?.downloadUrl).placeholder(R.drawable.placeholder)
                .into(binding.imageView)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}