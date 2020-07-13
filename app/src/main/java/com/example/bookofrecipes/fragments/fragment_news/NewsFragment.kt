package com.example.bookofrecipes.fragments.fragment_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookofrecipes.R
import com.example.bookofrecipes.fragments.fragment_news.news.AllNewsParser
import com.example.bookofrecipes.fragments.fragment_news.news.NewsItem
import com.example.bookofrecipes.fragments.fragment_news.news.NewsAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    companion object {

        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = GroupAdapter<GroupieViewHolder>()

        adapter.add(NewsAdapter(NewsItem("", "", "", "white", "")))

        recycler_view_news.adapter = adapter

        AllNewsParser.fetchNews(recycler_view_news)


    }
}