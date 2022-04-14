package com.ozaltun.marvel.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ozaltun.marvel.R
import com.ozaltun.marvel.adapter.CharactersDetailAdapter
import com.ozaltun.marvel.databinding.FragmentCharacterDetailsBinding
import com.ozaltun.marvel.model.comics.Comic
import com.ozaltun.marvel.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsFragment : Fragment() {

    val args: CharacterDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentCharacterDetailsBinding
    private val viewModel: CharacterDetailsViewModel by viewModel()
    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val(uri,description,name) = getDetailsWithSafeArgs()
        setupViews(uri,description,name)

        viewModel.comics.observe(viewLifecycleOwner){ comicList ->
            setRecyclerView(comicList)
        }
        viewModel.getComicsByCharacterId(args.character.id)
    }
    private fun getDetailsWithSafeArgs() : Triple<String,String,String> {
        val uri = args.character.thumbnail.path + "." + args.character.thumbnail.extension
        val description = args.character.description
        val name = args.character.name
        return  Triple(uri,description,name)

    }
    private fun setupViews(uri:String,description:String,name:String){
        binding.apply {
            Glide.with(this@CharacterDetailsFragment).load(uri).into(detailsCharacterImageView)
            detailsCharacterNameTxt.text = name
            if (description.isEmpty()) {
                detailsCharacterDescriptionTxtView.text = getString(R.string.character_unknown,name)
            }else {
                detailsCharacterDescriptionTxtView.text = description
            }
        }
    }
    private fun setRecyclerView(comicList: List<Comic>){
        binding.detailRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = CharactersDetailAdapter(comicList)
        }
    }

}