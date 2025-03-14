package com.example.cloudfirestorekotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cloudfirestorekotlin.databinding.ItemUserDetailsBinding

class UserListAdapter(private val userList: ArrayList<User>) :
    RecyclerView.Adapter<UserListAdapter.UserListVieHolder>() {


    inner class UserListVieHolder(val binding: ItemUserDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: User) {
            binding.tvName.text=model.name
            binding.tvLastName.text=model.lastName
            binding.tvBirthDate.text=model.birthDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListVieHolder {
        val binding =
            ItemUserDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListVieHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserListVieHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }


}