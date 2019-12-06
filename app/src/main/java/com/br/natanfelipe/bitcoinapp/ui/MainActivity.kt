package com.br.natanfelipe.bitcoinapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.br.natanfelipe.bitcoinapp.R
import com.br.natanfelipe.bitcoinapp.ViewModelFactory
import com.br.natanfelipe.bitcoinapp.databinding.ActivityMainBinding
import com.br.natanfelipe.bitcoinapp.di.component.DaggerApiServiceComponent
import com.br.natanfelipe.bitcoinapp.utils.Utils
import com.br.natanfelipe.bitcoinapp.viewmodel.BlockChainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var blockChainViewModel: BlockChainViewModel

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var utils: Utils

    init {
        DaggerApiServiceComponent
            .create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        blockChainViewModel = ViewModelProvider(this, ViewModelFactory(this))
            .get(BlockChainViewModel::class.java)

        observeViewModel()

        refresh()
    }

    private fun refresh() {
        swipeRefreshLayout.setOnRefreshListener {
            observeViewModel()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        val isConnected = this.let { utils.isConnectedToInternet(it) }

        blockChainViewModel.loadData(isConnected).observe(this, Observer { bitcoin ->
            bitcoin.let {
                cardsVisibility(true)
                binding.bitcoin = it
            }
        })

        blockChainViewModel.isLoading.observe(this, Observer { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            if (isLoading) {
                cardsVisibility(false)
            }
        })

        blockChainViewModel.message.observe(this, Observer { message ->
            binding.errorMessage.text = getString(message)
        })

        blockChainViewModel.isToShowMessage.observe(this, Observer { isToShow ->
            if(isToShow){
                cardsVisibility(false)
                binding.errorMessage.visibility = View.VISIBLE
            } else {
                cardsVisibility(true)
                binding.errorMessage.visibility = View.GONE
            }
        })
    }

    private fun cardsVisibility(isVisible: Boolean){
        if(isVisible){
            binding.contentCard.todayPriceCard.visibility = View.VISIBLE
            binding.contentCard.statsCard.visibility = View.VISIBLE
        } else {
            binding.contentCard.todayPriceCard.visibility = View.GONE
            binding.contentCard.statsCard.visibility = View.GONE
        }
    }
}

