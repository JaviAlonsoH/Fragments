package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fragments.API.ApiCall
import com.example.fragments.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.fragments.databinding.FragmentRepositoryListBinding
import com.example.fragments.UserAdapter

class RepositoryListFragment : Fragment() {

    private var _binding: FragmentRepositoryListBinding? = null
    private val binding
        get() = _binding!!
    private val adapter: UserAdapter = UserAdapter {
        var ageCover: Int? = it.dob.age
        var agePassed: String = ageCover.toString()
        var birth:String = it.dob.date.substring(0,10)

        val action =
            RepositoryListFragmentDirections.listToDetail(
                it.name.first,
                it.name.last,
                birth,
                it.picture.large,
                it.dob.age,
                it.location.country

            )
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvUsers.layoutManager = GridLayoutManager(context, 2)
        binding.rvUsers.adapter = adapter

        requestData()

    }

    private fun requestData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: ApiCall = retrofit.create(ApiCall::class.java)
        service.getUsers().enqueue(object : Callback<UserResponse> {

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    adapter.submitList(response.body()?.users)
                } else {
                    Toast.makeText(context, "400", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(context, "Error al cargar datos", Toast.LENGTH_SHORT).show()
            }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}