package com.example.androidplaceholder.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplaceholder.data.models.ProfileInfo
import com.example.androidplaceholder.databinding.FragmentProfileInfoCardBinding
import com.example.androidplaceholder.databinding.FragmentProfilePostCardBinding


class ProfileInfoAdapter: ListAdapter<ProfileInfo, RecyclerView.ViewHolder>(ProfileInfoDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ProfileInfo.ProfileInfoType.ProfileInfoContacts.ordinal ->{
                val bind = FragmentProfileInfoCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return  ProfileInfoContactsItem(bind)
            }
            ProfileInfo.ProfileInfoType.ProfileInfoPost.ordinal -> {
                val bind = FragmentProfilePostCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return  ProfileInfoPostItem(bind)
            }

            else -> {
                val bind = FragmentProfileInfoCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return  ProfileInfoContactsItem(bind)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ProfileInfo.ProfileInfoType.ProfileInfoContacts.ordinal ->
                (holder as ProfileInfoContactsItem).bind(getItem(position) as ProfileInfo.ProfileInfoContacts)
            ProfileInfo.ProfileInfoType.ProfileInfoPost.ordinal ->
                (holder as ProfileInfoPostItem).bind(getItem(position) as ProfileInfo.ProfileInfoPost)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is ProfileInfo.ProfileInfoContacts -> ProfileInfo.ProfileInfoType.ProfileInfoContacts.ordinal
            is ProfileInfo.ProfileInfoPost -> ProfileInfo.ProfileInfoType.ProfileInfoPost.ordinal
            else -> {
                -1
            }
        }
    }

    class ProfileInfoContactsItem(val bind: FragmentProfileInfoCardBinding): RecyclerView.ViewHolder(bind.root){
        fun bind(profileInfo: ProfileInfo.ProfileInfoContacts) = with(bind){
            bind.infoLabel.text = profileInfo.label
            bind.infoValue.text = profileInfo.value
        }
    }

    class ProfileInfoPostItem(val bind: FragmentProfilePostCardBinding) : RecyclerView.ViewHolder(bind.root){
        fun bind(profileInfo: ProfileInfo.ProfileInfoPost) = with(bind){
            bind.postTitle.text = profileInfo.title
            bind.postBody.text = profileInfo.body
        }
    }

    class ProfileInfoDiffUtil : DiffUtil.ItemCallback<ProfileInfo>(){
        override fun areItemsTheSame(oldItem: ProfileInfo, newItem: ProfileInfo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProfileInfo, newItem: ProfileInfo): Boolean {
            return oldItem == newItem
        }
    }
}