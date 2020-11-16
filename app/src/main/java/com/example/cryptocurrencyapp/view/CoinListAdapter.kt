package com.example.cryptocurrencyapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.MainActivity
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.data.CoinItemModel
import com.example.cryptocurrencyapp.ListFragment
import com.example.cryptocurrencyapp.databinding.CoinListItemBinding

import kotlinx.android.synthetic.main.coin_list_item.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class CoinListAdapter(var coinList: ArrayList<CoinItemModel>): RecyclerView.Adapter<CoinListAdapter.CoinListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CoinListItemBinding>(inflater,R.layout.coin_list_item, parent, false)
        return CoinListHolder(view)
    }

    override fun onBindViewHolder(holder: CoinListHolder, position: Int) {
        holder.view.coin = coinList[position].item
        //holder.view.coinName = coinList[position].item.name
        holder.view.coinItemLayout.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actionDetail)
        }
        /*
        holder.itemView.apply {
            coinName.text = coinList.item.name
            //coinName.text = coinList[0].item.name
            coinValue.text = coinList.get(0).item.id
            //coinPrice.text = coinList[0].rates[position].value
        }*/
    }

    fun getData(viewData: CoinItemModel) {
        //for (coins in viewData) {
            coinList.set(viewData.item.score, viewData)
        //}
    }

    fun refreshCoinData(newDataList: List<CoinItemModel>) {
        coinList.clear()
        coinList.addAll(newDataList)
        //coinList.addAll(newDataList) //.get(0).coins. = newDataList
        notifyDataSetChanged()

    }

    class CoinListHolder (var view: CoinListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun getItemCount() = coinList.size
}
