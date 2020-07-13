package com.example.bookofrecipes.fragments.fragment_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookofrecipes.R

class SearchFragment : Fragment() {

    companion object {

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
}