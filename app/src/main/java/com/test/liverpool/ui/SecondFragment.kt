package com.test.liverpool.ui

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.liverpool.R
import com.test.liverpool.data.Api
import com.test.liverpool.data.RetrofitConfig
import com.test.liverpool.data.RetrofitInstance
import com.test.liverpool.data.repositories.ProductRepository
import com.test.liverpool.databinding.FragmentFirstBinding
import com.test.liverpool.databinding.FragmentSecond2Binding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecond2Binding
    private lateinit var viewModel: ProductViewModel
    private lateinit var productGridAdapter: ProductGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecond2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitConfig = RetrofitConfig()

        viewModel = ViewModelProvider(this, ProductViewModelFactory(retrofitConfig.repository)).get(ProductViewModel::class.java)

        setupGrid()
        setupObservers()
        setupListeners()

        viewModel.fetchProducts("", 1)
    }


    private fun setupGrid() {
        productGridAdapter = ProductGridAdapter(emptyList()) { product ->
            // Lógica para manejar el clic en un producto
        }

        val gridProductLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        binding.rvGrid.layoutManager = gridProductLayoutManager
        binding.rvGrid.adapter = productGridAdapter
    }

    private fun setupObservers() {
        viewModel.products.observe(viewLifecycleOwner) { products ->
            productGridAdapter.updateList(products)
            binding.rvGrid.scrollToPosition(0) // Desplazar al inicio
        }
    }

    private fun setupListeners() {
        binding.ivSearch.setOnClickListener {
            toggleSearchVisibility()
        }

        binding.etSearch.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val query = binding.etSearch.text.toString()
                viewModel.fetchProducts(query, 1)
            }
        }

        binding.btnFilter.setOnClickListener {
            showFilterMenu()
        }
    }

    private fun toggleSearchVisibility() {
        if (binding.etSearch.visibility == View.VISIBLE) {
            binding.etSearch.visibility = View.GONE
            binding.tvBusqueda.visibility = View.VISIBLE
        } else {
            binding.etSearch.visibility = View.VISIBLE
            binding.tvBusqueda.visibility = View.GONE
        }
    }

    private fun showFilterMenu() {
        val popupMenu = PopupMenu(requireContext(), binding.btnFilter)
        popupMenu.menuInflater.inflate(R.menu.filter_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            val query = binding.etSearch.text.toString()
            when (item.itemId) {
                R.id.filter_relevance -> {
                    viewModel.fetchProducts(query, 1)
                    true
                }
                R.id.filter_lowest_price -> {
                    viewModel.getProductSortedByLowestPrice(query, 1)
                    true
                }
                R.id.filter_highest_price -> {
                    viewModel.getProductSortedByHighestPrice(query, 1)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}