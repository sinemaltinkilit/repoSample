package com.example.cryptocurrencyapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.data.ExchangeRatesModel
import com.example.cryptocurrencyapp.view.CoinListAdapter
import com.example.cryptocurrencyapp.view.ItemClickListener
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import dagger.android.*
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var navController: NavController

    @Inject
    lateinit var coinListViewModel: CoinListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        navController = Navigation.findNavController(this, R.id.fragment)
        toolbar.setTitle("HOME")
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}

