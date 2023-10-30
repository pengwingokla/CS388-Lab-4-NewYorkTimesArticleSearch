package com.example.lab_4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback

class ArticleAdapter(private val context: Context, private val articles: List<ArticleItem>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(article: ArticleItem)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]

        holder.articleHeading.text = article.heading
        holder.date.text = article.date
        holder.subheading.text = article.subheading

    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val articleHeading: TextView = itemView.findViewById(R.id.article_heading)
        val date: TextView = itemView.findViewById(R.id.date)
        val subheading: TextView = itemView.findViewById(R.id.subheading)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(articles[position])
                }
            }
        }
    }
}
