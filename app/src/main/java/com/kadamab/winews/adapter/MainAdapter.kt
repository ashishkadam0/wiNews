package com.kadamab.winews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kadamab.winews.R
import com.kadamab.winews.model.NewsResponse
import com.kadamab.winews.model.Rows

class MainAdapter(private val users: ArrayList<Rows>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
 /*
 *
 *  Declare DataHolder for ViewHolder implementation
 */
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tvTitle)
        var desc: TextView = itemView.findViewById(R.id.tvDescr)
        var img: ImageView = itemView.findViewById(R.id.imageViewNews)
        fun bind(user: Rows) {
            itemView.apply {

                // set news title
                title.text = user.title

                //set news description
                desc.text = user.description

                //Load image from url into imageview

                Glide.with(itemView.context)
                    .load(user.imageHref)
                    .into(img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        //bind data to view
        holder.bind(users[position])
    }

    fun addUsers(users: List<Rows>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
}