package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class RepositoryListFragment : Fragment() {

    private lateinit var clickMe: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickMe = view.findViewById(R.id.tv_clickMe)

        clickMe.setOnClickListener {
            val action = RepositoryListFragmentDirections.listToDetail("123")
            findNavController().navigate(action)
        }

    }
}