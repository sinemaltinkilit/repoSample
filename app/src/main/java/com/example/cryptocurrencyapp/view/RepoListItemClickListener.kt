package com.example.cryptocurrencyapp.view

import com.example.cryptocurrencyapp.data.RepoModel

interface RepoListItemClickListener {
    fun onRepoListItemClickListener(data: RepoModel, position: Int)
}