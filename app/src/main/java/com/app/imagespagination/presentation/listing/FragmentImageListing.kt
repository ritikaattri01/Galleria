package com.app.imagespagination.presentation.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.imagespagination.data.local.ImageEntity
import com.app.imagespagination.databinding.FragmentImageListingBinding
import com.app.imagespagination.presentation.adapter.ImagesAdapter
import com.app.imagespagination.presentation.recyclerview.OnLoadMoreListener
import com.app.imagespagination.presentation.recyclerview.RecyclerViewLoadMoreScroll
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentImageListing : Fragment() {

    private val viewModel: ListingViewModel by viewModels()

    private val imagesAdapter: ImagesAdapter by lazy {
        ImagesAdapter(onImageClicked = {
            navigateTo(
                FragmentImageListingDirections.actionListingFragmentToDetailsFragment(
                    it
                )
            )
        })
    }
    private lateinit var scrollListener: RecyclerViewLoadMoreScroll

    private var _binding: FragmentImageListingBinding? = null

    private val binding: FragmentImageListingBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageListingBinding.inflate(inflater, container, false)
        setRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun setRecyclerView() {
        val mLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        scrollListener = RecyclerViewLoadMoreScroll(mLayoutManager)
        scrollListener.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                observe()
            }
        })

        binding.recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = imagesAdapter
            addOnScrollListener(scrollListener)
        }
    }

    private var lastObservedLiveData: LiveData<List<ImageEntity>>? = null
    private fun observe() {
        lifecycleScope.launch {
            lastObservedLiveData?.removeObservers(viewLifecycleOwner)
            lastObservedLiveData = viewModel.fetchList().apply {
                observe(viewLifecycleOwner) {
                    imagesAdapter.addItems(it)
                    scrollListener.setLoaded()
                }
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