package com.example.cryptocurrencyapp.view

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.data.CoinItemModel
import com.example.cryptocurrencyapp.data.RepoModel
import com.example.cryptocurrencyapp.databinding.CoinListItemBinding
import com.example.cryptocurrencyapp.databinding.RepoListItemBinding
import com.example.cryptocurrencyapp.view.RepoDetailFragment.Companion.KEY_FAV
import com.example.cryptocurrencyapp.view.RepoDetailFragment.Companion.KEY_FAV_ID
import kotlinx.android.synthetic.main.repo_list_item.*

class RepoListAdapter(
    private val context: Context,
    private val repoList: List<RepoModel>,
    private val onRepoListItemClickListener: RepoListItemClickListener
): RecyclerView.Adapter<RepoListAdapter.RepoListViewHolder>() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RepoListItemBinding>(inflater,
            R.layout.repo_list_item, parent, false)
        return RepoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        val listData = repoList[position]
        holder.view.repoName.text = listData.name
        holder.view.itemView.setOnClickListener {
            onRepoListItemClickListener.onRepoListItemClickListener(listData, position)
        }
        val sharedPreferences = context?.getSharedPreferences(RepoDetailFragment.PREFS_FILENAME,Context.MODE_PRIVATE)
        //val editor = sharedPreferences?.edit()
        val isFav = sharedPreferences.getBoolean(KEY_FAV, false)
        val favId = sharedPreferences.getInt(KEY_FAV_ID, -1)

        if (isFav && listData.id == favId) {
            holder.view.favIcon.setImageResource(R.drawable.star)
            holder.view.favIcon.visibility = View.VISIBLE
        }
    }

    class RepoListViewHolder(var view: RepoListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun getItemCount() = repoList.size
}