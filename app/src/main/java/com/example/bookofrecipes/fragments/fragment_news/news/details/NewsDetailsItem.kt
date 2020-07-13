package com.example.bookofrecipes.fragments.fragment_news.news.details

class NewsDetailsItem(val title: String, var imgRef: String,
                      val subTitle: String, val text: String,
                      val videoRef: String, val detailsName: String,
                      val detailsString: String, val titleText : String) {
    constructor() : this("","","","","", "","","")
}