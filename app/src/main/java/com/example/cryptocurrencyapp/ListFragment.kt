package com.example.cryptocurrencyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_list.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencyapp.data.CoinItemModel
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import com.example.cryptocurrencyapp.view.CoinListAdapter
import com.example.cryptocurrencyapp.data.ExchangeRatesModel
import com.example.cryptocurrencyapp.data.CoinsDataModel
import com.example.cryptocurrencyapp.view.ItemClickListener
import kotlinx.android.synthetic.main.coin_list_item.*

class ListFragment : Fragment(), ItemClickListener {

    private lateinit var viewModel: CoinListViewModel
    private val listAdapter = CoinListAdapter(arrayListOf())
    private val coinListDataObserver = Observer<CoinsDataModel> { list ->
        list?.let {
            listAdapter.refreshCoinData(it.coins)
        }
    }

    private val isErrorObserver = Observer<Boolean> { isError ->
        if(isError) {
            errorText.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CoinListViewModel::class.java)
        viewModel.coinList.observe(viewLifecycleOwner, coinListDataObserver)
        viewModel.isError.observe(viewLifecycleOwner, isErrorObserver)
        //viewModel.coinName.observe(viewLifecycleOwner, coinName)
        viewModel.refresh()

        coinList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }

    override fun onItemClick(holder: CoinListAdapter.CoinListHolder, position: Int) {
        Navigation.findNavController(holder.itemView).navigate(R.id.actionDetail)
    }
}