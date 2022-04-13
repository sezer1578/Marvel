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
import com.bumptech.glide.Glide
import com.ozaltun.marvel.R
import com.ozaltun.marvel.databinding.FragmentComicsBinding

class ComicsFragment : Fragment() {

    val args: ComicsFragmentArgs by navArgs()
    private lateinit var binding: FragmentComicsBinding
    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComicsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navController.popBackStack(R.id.characterDetailsFragment, false)
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uri = args.character.thumbnail.path+"."+args.character.thumbnail.extension
        Glide.with(binding.imageView).load(uri).into(binding.imageView)
    }
}