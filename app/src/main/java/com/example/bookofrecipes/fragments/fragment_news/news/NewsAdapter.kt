package com.example.bookofrecipes.fragments.fragment_news.news

import android.annotation.SuppressLint
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.example.bookofrecipes.R
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(private val newsItem: NewsItem) : Item<GroupieViewHolder>() {
    @SuppressLint("ResourceAsColor")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.row_title_news_item.text = newsItem.title
        viewHolder.itemView.row_subTitle_news_item.text = newsItem.subTitle
        //image
        if(newsItem.imgRef != "") {
            Picasso.get().load(newsItem.imgRef).into(viewHolder.itemView.row_img_news_item)
        }

        when (newsItem.colorText) {
            "white" -> {
                viewHolder.itemView.row_title_news_item.setTextColor(getColorStateList(viewHolder.itemView.context, R.color.white))
                viewHolder.itemView.row_subTitle_news_item.setTextColor(getColorStateList(viewHolder.itemView.context, R.color.white))
            }

            "black" -> {
                viewHolder.itemView.row_title_news_item.setTextColor(getColorStateList(viewHolder.itemView.context, R.color.black))
                viewHolder.itemView.row_subTitle_news_item.setTextColor(getColorStateList(viewHolder.itemView.context, R.color.black))
            }

            else -> println("Error in adapter initialization , text color not set....")
        }
    }

    fun getRef(): String {
        return newsItem.refUrl
    }

    override fun getLayout(): Int {
        return R.layout.news_item
    }

}