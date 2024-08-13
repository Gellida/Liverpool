package com.test.liverpool.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.liverpool.data.source.Api
import com.test.liverpool.data.source.RetrofitInstance
import com.test.liverpool.data.repositories.ProductRepository
import com.test.liverpool.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var viewModel: ProductViewModel
    private lateinit var repository: ProductRepository
    private lateinit var api: Api

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
    }

}