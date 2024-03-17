
package com.example.group3project1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//binding Candidate data to RecyclerView
class MyAdapter(private val candidatelist : ArrayList<Candidate>): RecyclerView.Adapter <MyAdapter.MyViewHolder>(){

    // Oncreate View Holder Called to display the data at a specific position
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.candidate_rowlayout,parent,false)
    return MyViewHolder(itemView)
    }
  //display the data at a specific position
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
              val item = candidatelist[position]

   //  use Glide library to Load candidate image
        Glide.with(holder.itemView.context)
            .load(item.Image)
            .into(holder.Image)

        // Set candidate information to the ViewHolder
        holder.Name.text =item.Name
        holder.Title.text=item.Title
        holder.Company.text=item.Company
        holder.Address.text=item.Address
    }

    // ViewHolder class to hold references to the views in each item
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // add all the details from row layout here : for the fields needed
        val Name:TextView = itemView.findViewById(R.id.Name)
        val Title:TextView = itemView.findViewById(R.id.JobTitle)
        val Company:TextView = itemView.findViewById(R.id.Companyname)
        val Image: ImageView = itemView.findViewById(R.id.UserImage)
        val Address:TextView =itemView.findViewById(R.id.Address)

    }

    override fun getItemCount(): Int {
        return candidatelist.size
    }

}