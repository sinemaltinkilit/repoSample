package com.example.cryptocurrencyapp.data

data class RepoModel(
    var id: Int? = 0,
    var node_id: String? = null,
    var name: String? = null,
    var full_name: String? = null,
    var private: Boolean? = false,
    var owner: RepoOwnerModel? = null,
    var description: String? = null,
    var open_issues_count: Int? = 0,
    var stargazers_count: Int? = 0
)