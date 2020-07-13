package com.example.bookofrecipes.fragments.fragment_news.news.details

import android.annotation.SuppressLint
import com.example.bookofrecipes.R
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.news_details_item.view.*

class NewsDetailsAdapter(private val newsDetailsItem: NewsDetailsItem) : Item<GroupieViewHolder>() {
    @SuppressLint("SetTextI18n")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        //title
        viewHolder.itemView.row_title_news_details_item.text = newsDetailsItem.title
        //subTitle
        viewHolder.itemView.row_subTitle_news_details_item.text = newsDetailsItem.subTitle
        //image
        if(newsDetailsItem.imgRef != "") {
            Picasso.get().load(newsDetailsItem.imgRef).into(viewHolder.itemView.row_img_news_details_item)
        }
        //detailsName
        viewHolder.itemView.row_left_aside_title_news_details_name_item.text = newsDetailsItem.detailsName
        //detailsString
        viewHolder.itemView.row_left_aside_title_news_details_string_item.text = newsDetailsItem.detailsString
        //text
        viewHolder.itemView.row_text_news_details_item.text = "      ${newsDetailsItem.text}"
    }

    override fun getLayout(): Int {
        return R.layout.news_details_item
    }
}