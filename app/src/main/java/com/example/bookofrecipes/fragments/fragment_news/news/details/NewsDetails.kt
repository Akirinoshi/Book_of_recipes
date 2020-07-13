package com.example.bookofrecipes.fragments.fragment_news.news.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookofrecipes.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_activity_news_details.*


class NewsDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_activity_news_details)
        var refUrl: String? = intent.extras?.getString("refUrl")
        intent.extras?.clear()

        val adapter = GroupAdapter<GroupieViewHolder>()

        adapter.add(NewsDetailsAdapter(NewsDetailsItem("", "", "", "", "", "", "", "")))

        recycler_view_news_details.adapter = adapter

        NewsDetailsParser.fetchData(refUrl, recycler_view_news_details)
    }
}