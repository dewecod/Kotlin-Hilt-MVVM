package com.dewecod.mvvmhilt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dewecod.mvvmhilt.databinding.FragmentHomeBinding
import com.dewecod.mvvmhilt.model.Pokemon
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PokemonViewModel
    private lateinit var adapter: PokemonAdapter
    private lateinit var pokemonList: ArrayList<Pokemon>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        initRecyclerView()
        observeData()
        setUpItemTouchHelper()
        viewModel.getPokemonList()
    }

    private fun setUpItemTouchHelper() {
        val simpleCallback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    @NonNull recyclerView: RecyclerView,
                    @NonNull viewHolder: RecyclerView.ViewHolder,
                    @NonNull target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(
                    @NonNull viewHolder: RecyclerView.ViewHolder,
                    direction: Int
                ) {
                    val swipedPokemonPosition = viewHolder.adapterPosition
                    val pokemon: Pokemon = adapter.getPokemonAt(swipedPokemonPosition)
                    viewModel.insertPokemon(pokemon)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(context, "Pokemon added to favorites.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.recycler)
    }


    private fun observeData() {
        viewModel.getPokemonList().observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })
    }

    private fun initRecyclerView() {
        binding.recycler.layoutManager = LinearLayoutManager(context)
        adapter = PokemonAdapter(requireContext(), pokemonList)
        binding.recycler.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}