package com.example.bookofrecipes.fragments.fragment_news.news.details

import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.io.IOException

class NewsDetailsParser {
    companion object {
        fun fetchData(refUrl: String?, recycler_view_news_details: RecyclerView) {
            GlobalScope.launch {
                try {
                    val document = Jsoup.connect(refUrl).get()
                    val adapter = GroupAdapter<GroupieViewHolder>()
                    val elementHeader = document.select("div[class=article-page__header]")
                    val elementPagePoster = document.select("div[class=article-page__poster]")
                    val elementLeftAside = document.getElementsByClass("staff__item")
                    var elementContent1 = document.select("div[class=introduction]")
                    elementContent1 = elementContent1.select("p")
                    val elementContent2 = document.select("div[class=article-page__article layout__content-col]")

                    println("ELEMENT 1 : $elementContent1")
                    //println("ELEMENT 2 : $elementContent2")

                    for(i in 0 until elementHeader.size) {
                        val title = elementHeader
                            .select("h1")
                            .eq(i)
                            .text()

                        val subTitle = elementHeader
                            .select("p")
                            .eq(i)
                            .text()

                        adapter.add(NewsDetailsAdapter(NewsDetailsItem(title, "", "", "", "", "", "", "")))
                        adapter.add(NewsDetailsAdapter(NewsDetailsItem("", "", subTitle, "", "", "", "", "")))
                    }

                    for(i in 0 until elementPagePoster.size) {
                        var imgRef = elementPagePoster
                            .select("img")
                            .attr("srcset")

                        imgRef = imgRef.dropLast(3)

                        adapter.add(NewsDetailsAdapter(NewsDetailsItem("", imgRef, "", "", "", "", "", "")))
                    }

                    for(i in 0 until elementLeftAside.size) {
                        var detailsName = elementLeftAside[i]
                            .select("span[class=position uppercase]")
                            .text()


                        var detailsString = elementLeftAside[i]
                            .select("span")
                            .not("span[class=position uppercase]")
                            .text()

                        adapter.add(NewsDetailsAdapter(NewsDetailsItem("", "", "", "", "", detailsName, "", "")))
                        adapter.add(NewsDetailsAdapter(NewsDetailsItem("", "", "", "", "", "", detailsString, "")))
                    }

                    for(i in 0 until elementContent1.size) {
                        val text = elementContent1
                            .select("p")
                            .eq(i)
                            .text()

                        adapter.add(NewsDetailsAdapter(NewsDetailsItem("", "", "", text, "", "", "", "")))
                    }


                    GlobalScope.launch(Dispatchers.Main) {
                        recycler_view_news_details.adapter = adapter
                    }
                } catch (e : IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}