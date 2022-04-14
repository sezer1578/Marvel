package com.ozaltun.marvel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ozaltun.marvel.databinding.ItemCharacterDetailBinding
import com.ozaltun.marvel.model.comics.Comic

class CharactersDetailAdapter(private val characterList: List<Comic>): RecyclerView.Adapter<CharactersDetailAdapter.CharacterDetailViewHolder>() {

    inner class CharacterDetailViewHolder(val binding: ItemCharacterDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersDetailAdapter.CharacterDetailViewHolder {
       val viewBinding = ItemCharacterDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  CharacterDetailViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: CharactersDetailAdapter.CharacterDetailViewHolder, position: Int) {
       val characterDetail = characterList[position]
        with(holder.binding){
            val uri = characterDetail.thumbnail.path+"."+characterDetail.thumbnail.extension
            Glide.with(comicimageView).load(uri).into(comicimageView)
            
            comicTxtView.text = characterDetail.title
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

}