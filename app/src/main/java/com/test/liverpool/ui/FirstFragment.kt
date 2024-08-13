package com.test.liverpool.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.liverpool.R
import com.test.liverpool.data.Api
import com.test.liverpool.data.RetrofitInstance
import com.test.liverpool.data.repositories.ProductRepository
import com.test.liverpool.databinding.FragmentFirstBinding
import retrofit2.Retrofit

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var viewModel: ProductViewModel
    private lateinit var repository: ProductRepository
    private lateinit var api: Api
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        api = RetrofitInstance.api

        // Inicializa el repositorio usando la instancia de api proporcionada por RetrofitInstance
        repository = ProductRepository(api)

        // Usa un factory para pasar el repositorio al ViewModel
        viewModel = ViewModelProvider(this, ProductViewModelFactory(repository)).get(ProductViewModel::class.java)


        adapter = ProductAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.products.observe(viewLifecycleOwner) { products ->
            adapter.submitList(products)
        }

        viewModel.fetchProducts("smartwatch", 1)
    }



}