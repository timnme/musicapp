package com.sample.musicapp.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.musicapp.R
import com.sample.musicapp.di.TheApplication
import com.sample.musicapp.domain.entities.SearchResultItem
import com.sample.musicapp.mvp.MvpFragment
import com.sample.musicapp.ui.MainActivity
import com.sample.musicapp.ui.search.adapters.SearchAdapter
import com.sample.musicapp.utils.hideKeyboard
import com.sample.musicapp.utils.toast
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject


class SearchFragment : MvpFragment<SearchContract.View, SearchContract.Presenter>(),
    SearchContract.View {

    @Inject
    override lateinit var presenter: SearchContract.Presenter
    private lateinit var listener: OnAlbumSelectedListener
    private lateinit var adapter: SearchAdapter
    private var searchResults: List<SearchResultItem> = emptyList()

    interface OnAlbumSelectedListener {
        fun onAlbumSelected(albumId: Int)
    }

    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        TheApplication.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = activity as MainActivity
        } catch (e: ClassCastException) {
            throw ClassCastException(
                "$activity must implement ${OnAlbumSelectedListener::class.java.simpleName}"
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_search, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupListeners()
    }

    private fun setupAdapter() {
        adapter = SearchAdapter(
            items = searchResults,
            onItemClick = listener::onAlbumSelected
        )
        search_RecyclerView.adapter = adapter
        if (searchResults.isNotEmpty()) {
            // There are already albums loaded, hide search message
            info_TextView.visibility = View.GONE
        }
    }

    private fun setupListeners() {
        search_Button.setOnClickListener {
            val query = search_EditText.text.toString()
            // Check if the user specified search query
            if (query.isBlank()) {
                // No query, show message
                toast(getString(R.string.msg_empty_query))
            } else {
                // Search albums
                activity?.hideKeyboard()
                search_Button.isEnabled = false
                search_EditText.isEnabled = false
                searchStub_View.visibility = View.VISIBLE
                progressBar.visibility = View.VISIBLE
                presenter.search(query)
            }
        }
    }

    override fun onSearchComplete(results: List<SearchResultItem>) {
        searchResults = results

        search_Button.isEnabled = true
        search_EditText.isEnabled = true
        searchStub_View.visibility = View.GONE
        progressBar.visibility = View.GONE

        if (results.isEmpty()) {
            // No albums found. Hide the recycler view and show message
            search_RecyclerView.visibility = View.GONE
            info_TextView.visibility = View.VISIBLE
            info_TextView.text = getString(R.string.msg_no_albums)
        } else {
            adapter.onNewItems(searchResults)
            search_RecyclerView.visibility = View.VISIBLE
            info_TextView.visibility = View.GONE
        }
    }

    override fun onSearchError(errMessage: String?) {
        searchResults = emptyList()
        adapter.onNewItems(searchResults)

        search_Button.isEnabled = true
        search_EditText.isEnabled = true
        searchStub_View.visibility = View.GONE
        progressBar.visibility = View.GONE
        search_RecyclerView.visibility = View.GONE
        // Show error message
        info_TextView.visibility = View.VISIBLE
        info_TextView.text = errMessage ?: getString(R.string.error)
    }
}