package com.app.imagespagination.presentation.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.imagespagination.databinding.FragmentImageListingBinding
import com.app.imagespagination.presentation.adapter.ImagesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentImageListing : Fragment() {

    private val viewModel: ListingViewModel by viewModels()

    private val imagesAdapter: ImagesAdapter by lazy {
        ImagesAdapter()
    }

    private var _binding: FragmentImageListingBinding? = null

    private val binding: FragmentImageListingBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageListingBinding.inflate(inflater, container, false)
        observe()
        setRecyclerView()
        return binding.root
    }

    private fun setRecyclerView() {
        binding.recyclerView.apply {
            layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = imagesAdapter
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.getListData().collectLatest {
                imagesAdapter.submitData(it)
            }
        }
    }

    private fun navigateTo(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}