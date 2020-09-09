package com.mustafayosri.gadsleaderboard.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mustafayosri.gadsleaderboard.R
import com.mustafayosri.gadsleaderboard.adapter.LeaderBoardGeneralAdapter.ViewHolder
import com.mustafayosri.gadsleaderboard.pojo.LearnerResponse
import com.squareup.picasso.Picasso

class LeaderBoardGeneralAdapter(val context: Fragment) : RecyclerView.Adapter<ViewHolder>() {

    lateinit var response: List<LearnerResponse>
    lateinit var type : String

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context.activity).inflate(
                R.layout.cardview_item_learner_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return response.size
    }

    fun renewItems(
        response: List<LearnerResponse>,
        type:String) {
        this.response = response
        this.type = type
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load(response[position].badgeUrl).into(holder.imageView)
        holder.tvName.text = response[position].name

        if (type == "hours"){

            holder.tvDescription.text = response[position].number.toString() +" "+ context.getString(R.string.hours) +", " + response[position].country
        } else{
            holder.tvDescription.text = response[position].number.toString() +" "+ context.getString(R.string.score) +"," + response[position].country

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }

}