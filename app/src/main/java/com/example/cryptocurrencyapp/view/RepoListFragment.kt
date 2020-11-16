package com.example.cryptocurrencyapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.data.RepoModel
import com.example.cryptocurrencyapp.viewmodel.RepoListViewModel
import kotlinx.android.synthetic.main.fragment_repo_list.*
import com.example.cryptocurrencyapp.view.RepoDetailFragment.Companion.PREFS_FILENAME
import com.example.cryptocurrencyapp.view.RepoDetailFragment.Companion.KEY_FAV_ID

class RepoListFragment: Fragment(), RepoListItemClickListener {

    private lateinit var viewModel: RepoListViewModel

    private var userNameText: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RepoListViewModel::class.java)
        observeViewModel()
        initUI()
    }

    private fun initUI() {
        userEditText.addTextChangedListener {
            if (it != null) {
                userNameText = it.toString()
            }
        }
        submitBtn.setOnClickListener {
            it?.let {
                viewModel.getRepoList(userNameText)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.apply {
            showProgress.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                it?.let {
                    progressList.visibility = it
                }
            })
            dataList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                it?.let {
                    handleData(it)
                }
            })
        }
    }

    private fun handleData(data: List<RepoModel>) {
        repoList.setHasFixedSize(true);
        val dividerItemDecoration = DividerItemDecoration(context, VERTICAL)
        repoList.addItemDecoration(dividerItemDecoration)
        repoList.layoutManager = GridLayoutManager(context, 1)
        repoList.adapter = context?.let { context -> RepoListAdapter(context, data, this@RepoListFragment) }
        repoList.adapter?.notifyItemChanged(0, data)
    }

    override fun onRepoListItemClickListener(data: RepoModel, position: Int) {
        // open Detail fragment
        var bundle = bundleOf("id" to data.id, "username" to data.name, "starCount" to data.stargazers_count, "issuesCount" to data.open_issues_count, "imageUrl" to data.owner?.avatar_url)
        view?.let { Navigation.findNavController(it).navigate(R.id.actionRepoDetail, bundle) }
    }

    override fun onDestroy() {
        super.onDestroy()
        val sharedPreferences = activity?.getSharedPreferences(PREFS_FILENAME,Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.clear()
        editor?.apply()
    }
}