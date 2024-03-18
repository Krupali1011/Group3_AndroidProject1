package com.example.group3project1

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
class FeedAdapter(private val feedList: ArrayList<Feed>):RecyclerView.Adapter<FeedAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_feed,parent,false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = feedList[position]
        Glide.with(holder.itemView.context)
            .load(item.PostImage)
            .into(holder.feedImg)
        Glide.with(holder.itemView.context)
            .load(item.Image)
            .into(holder.candidateProfile)
        holder.name.text = item.Name
        holder.title.text = item.Title
        holder.description.text = item.Description

        holder.btnlike.setOnClickListener{

           holder.btnlike.setBackgroundColor(Color.GREEN)
            holder.btnlike.text="Liked"
        }

    }
    class MyViewHolder(itemView: android.view.View) :RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.name)
        val title: TextView = itemView.findViewById(R.id.title)
        val candidateProfile: ImageView = itemView.findViewById(R.id.candidate_profile)
        val feedImg: ImageView = itemView.findViewById(R.id.feed_img)
        val description: TextView = itemView.findViewById(R.id.description)
        val btnlike:Button=itemView.findViewById(R.id.LikeBtn)


    }
    override fun getItemCount(): Int {
        return feedList.size
    }

}