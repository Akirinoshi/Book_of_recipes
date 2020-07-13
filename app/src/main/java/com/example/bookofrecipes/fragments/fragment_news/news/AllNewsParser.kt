package com.example.bookofrecipes.fragments.fragment_news.news

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bookofrecipes.fragments.fragment_news.news.details.NewsDetails
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.io.IOException

class AllNewsParser {
    companion object {
        fun fetchNews(recycler_view_news: RecyclerView) {


            GlobalScope.launch {
                try {
                    val url = "https://eda.ru"
                    val document = Jsoup.connect(url).get()
                    val adapter = GroupAdapter<GroupieViewHolder>()
                    val elementMain =
                        document.select("div[class=journal-page__2x2-widget js-click-link]")
                    val element2x1 = document.select("div[class=journal-page__left-side]")
                    val element2x2 =
                        document.getElementsByClass("widget-item with-bg js-click-link ")


                    for (i in 0 until elementMain.size) {
                        val title = elementMain.select("div[class=widget_text-block]")
                            .select("h2")
                            .eq(i)
                            .text()

                        val subTitle = elementMain.select("div[class=widget_text-block]")
                            .select("p")
                            .eq(i)
                            .text()

                        val imgRef =
                            elementMain.select("div[class=cover-img lazy-load-container js-lazy-loading]")
                                .eq(i)
                                .attr("data-src")

                        val refUrl =
                            elementMain.select("div[class=journal-page__2x2-widget js-click-link]")
                                .eq(i)
                                .attr("data-href")

                        adapter.add(NewsAdapter(NewsItem(title, imgRef, subTitle, "white", refUrl)))
                    }

                    for (i in 0 until element2x1.size) {
                        val title = element2x1.select("div[class=widget-item_text-block]")
                            .select("h3")
                            .eq(i)
                            .text()

                        val subTitle = element2x1.select("p[class=lead]")
                            .eq(i)
                            .text()

                        val refUrl =
                            element2x1.select("div[class=widget-item js-click-link js-hover]")
                                .eq(i)
                                .attr("data-href")

                        adapter.add(NewsAdapter(NewsItem(title, "", subTitle, "black", refUrl)))
                    }

                    for (i in 0 until element2x1.size) {
                        val imgRef =
                            element2x1.select("div[class=cover-img lazy-load-container js-lazy-loading]")
                                .eq(i)
                                .attr("data-src-set")

                        val refUrl =
                            element2x1.select("div[class=widget-item js-click-link js-hover]")
                                .eq(i)
                                .attr("data-href")

                        adapter.add(NewsAdapter(NewsItem("", imgRef, "", "black", refUrl)))
                    }

                    for (i in 0 until element2x2.size) {
                        val title = element2x2[i]
                            .select("h3")
                            .text()

                        val subTitle = element2x2[i]
                            .select("p")
                            .text()

                        val imgRef = element2x2[i]
                            .select("div[class=cover-img lazy-load-container js-lazy-loading]")
                            .attr("data-src-set")

                        val refUrl = element2x2[i]
                            .attr("data-href")

                        adapter.add(NewsAdapter(NewsItem(title, imgRef, subTitle, "white", refUrl)))
                    }

                    adapter.setOnItemClickListener { item: Item<GroupieViewHolder>, view ->
                        val newsAdapter = item as NewsAdapter

                        val intent = Intent(view.context, NewsDetails::class.java)
                        intent.putExtra("refUrl", newsAdapter.getRef())
                        startActivity(view.context, intent, intent.extras)
                    }

                    GlobalScope.launch(Dispatchers.Main) {
                        recycler_view_news.adapter = adapter
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}