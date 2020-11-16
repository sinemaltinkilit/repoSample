package com.example.cryptocurrencyapp.view

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryptocurrencyapp.R
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.cryptocurrencyapp.di.ImageDownloader
import com.example.cryptocurrencyapp.viewmodel.RepoListViewModel
import kotlinx.android.synthetic.main.fragment_repo_detail.*

class RepoDetailFragment: Fragment() {

    companion object {
        val PREFS_FILENAME = "prefs"
        val KEY_FAV = "FAV"
        val KEY_FAV_ID = "FAVID"
    }

    private lateinit var viewModel: RepoListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.example.cryptocurrencyapp.R.layout.fragment_repo_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RepoListViewModel::class.java)
        val id = arguments?.getInt("id", 0)
        val repoName = arguments?.getString("username")
        val starCount = arguments?.getInt("starCount", 0)
        val issuesCount = arguments?.getInt("issuesCount", 0)
        val imageUrl = arguments?.getString("imageUrl")
        initUI(id, repoName, starCount, issuesCount, imageUrl)
    }

    private fun initUI(id: Int?, repoName: String?, starCount: Int?, issuesCount: Int?, imageUrl: String?) {
        repodetailname.text = repoName
        starCountText.text = "Star count: " + starCount.toString()
        openIssuesCountText.text = "Open issues: " + issuesCount.toString()
        imageUrl?.let { getImageAsync(it) }
        handleFav(id)
    }

    private fun handleFav(id: Int?) {
        val sharedPreferences = activity?.getSharedPreferences(PREFS_FILENAME,Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        favButton.setOnClickListener {
            favButton.setImageResource(R.drawable.star2)
            id?.let { editor?.putInt(KEY_FAV_ID, it) }
            editor?.putBoolean(KEY_FAV, true)
            editor?.apply()
            editor?.commit()
        }
    }

    private fun getImageAsync(url: String) {
        progress.visibility = View.VISIBLE
        val task = ImageDownloader(url, listener = object : ImageDownloader.DownloadListener {
            override fun onDownloadComplete(download: Boolean, bmp: Bitmap) {
                if (download) {
                    progress.visibility = View.GONE
                    userAvatar.setImageBitmap(bmp)
                }
            }

        })
        task.execute()
    }
}