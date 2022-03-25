package com.kadamab.winews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kadamab.winews.R
import com.kadamab.winews.databinding.ItemMainBinding
import com.kadamab.winews.model.Rows

class NewsListAdapter(private val news: ArrayList<Rows>) : RecyclerView.Adapter<NewsListAdapter.DataViewHolder>() {
 /*
 *
 *  Declare DataHolder for ViewHolder implementation
 */

    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class DataViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }



    class DataViewHoaalder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

  /*  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false))
*/
    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        with(holder){
            with(news[position]){
                binding.tvTitle.text = this.title
                binding.tvDescr.text = this.description
                Glide.with(itemView.context)
                    .load(this.imageHref)
                    .into(binding.imageViewNews)
            }
        }
    }

    fun addNews(users: List<Rows>) {
        this.news.apply {
            clear()
            addAll(users)
        }

    }
}