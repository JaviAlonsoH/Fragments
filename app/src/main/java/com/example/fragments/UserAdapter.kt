package com.example.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fragments.databinding.UserBinding
import com.example.fragments.model.User
import com.squareup.picasso.Picasso


interface OnUserListener {
    fun onClick(user: User)
}

class UserAdapter(private val onUserClicked: (User) -> Unit) :
    ListAdapter<User, UserAdapter.ViewHolder>(UserItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UserBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.binding.tvName.text = user.name.first + " " + user.name.last
        holder.binding.tvCountry.text = user.nat
        Picasso.get().load(user.picture.large).into(holder.binding.ivAvatar)




        holder.binding.root.setOnClickListener {
            onUserClicked(user)
        }

    }

    inner class ViewHolder(val binding: UserBinding) : RecyclerView.ViewHolder(binding.root)
}

class UserItemCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem.id == newItem.id

}