package com.graviton.beerwithbros2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.graviton.beerwithbros2.R
import com.graviton.beerwithbros2.databinding.FragmentBeerListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class BeerFragment : Fragment(R.layout.fragment_beer_list) {

    private var columnCount = 1
    private val viewModel by viewModels<BeerViewModel>()
    private var binding: FragmentBeerListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBeerListBinding.bind(view)
        val beerAdapter = BeerRecyclerViewAdapter()
        binding?.apply {
            list.apply {
                adapter= beerAdapter
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.beerStateFlow.collectLatest {
                    beerAdapter.submitList(it)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onRefresh()
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            BeerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}