package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.example.fragments.databinding.FragmentRepositoryDetailBinding

class RepositoryDetailFragment : Fragment() {
    private var _binding: FragmentRepositoryDetailBinding? = null
    private val binding
        get() = _binding!!


    private val args: RepositoryDetailFragmentArgs by navArgs()
    private var firstname: String? = null
    private var lastname: String? = null
    private var date: String? = null
    private var age: Int?= null
    private var country : String? = null
    private var pic: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.let {
            firstname = it.name
            lastname = it.lastName
            date = it.birth
            pic = it.pic
            age = it.age
            country= it.country
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvNameDyn.text = firstname
        binding.tvBirthDyn.text = date
        binding.tvAgeDyn.text = age.toString()
        binding.tvCountryDyn.text = country

        Picasso.get().load(pic).into(binding.image)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}