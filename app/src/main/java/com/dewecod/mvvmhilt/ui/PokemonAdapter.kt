package com.dewecod.mvvmhilt.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dewecod.mvvmhilt.databinding.ListItemBinding
import com.dewecod.mvvmhilt.model.Pokemon


class PokemonAdapter(context: Context, list: ArrayList<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private var mContext: Context = context
    private var mList: ArrayList<Pokemon> = list

    private var _binding: ListItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(mContext)
        _binding = ListItemBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(@NonNull holder: PokemonViewHolder, position: Int) {
        holder.itemBinding.pokemonName.text = mList[position].name
        Glide.with(mContext)
            .load(mList[position].url)
            .into(holder.itemBinding.pokemonImage)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class PokemonViewHolder(val itemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }

    fun updateList(updatedList: ArrayList<Pokemon>) {
        mList = updatedList
        notifyDataSetChanged()
    }

    fun getPokemonAt(position: Int): Pokemon {
        return mList[position]
    }
}