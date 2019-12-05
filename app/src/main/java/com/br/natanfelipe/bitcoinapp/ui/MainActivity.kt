package com.br.natanfelipe.bitcoinapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.RoomDatabase
import com.br.natanfelipe.bitcoinapp.BitcoinViewModelFactory
import com.br.natanfelipe.bitcoinapp.R
import com.br.natanfelipe.bitcoinapp.dao.BitcoinDatabase
import com.br.natanfelipe.bitcoinapp.databinding.ActivityMainBinding
import com.br.natanfelipe.bitcoinapp.di.component.DaggerAppComponent
import com.br.natanfelipe.bitcoinapp.repository.BlockChainRepository
import com.br.natanfelipe.bitcoinapp.utils.Utils
import com.br.natanfelipe.bitcoinapp.viewmodel.BlockChainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var blockChainViewModel: BlockChainViewModel

    private lateinit var binding: ActivityMainBinding

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    @Inject
    lateinit var utils: Utils

    /*@Inject
    lateinit var roomDatabase: RoomDatabase*/

    private lateinit var roomDatabase: BitcoinDatabase

    init {
        DaggerAppComponent
            .create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        roomDatabase = BitcoinDatabase.getDatabase(applicationContext, this)

        blockChainViewModel = ViewModelProvider(this, BitcoinViewModelFactory(roomDatabase))
            .get(BlockChainViewModel::class.java)

        job.cancel()

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
                binding.bitcoin = it
            }
        })

        blockChainViewModel.isLoading.observe(this, Observer { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            if (isLoading) {
                //binding.contentCard.statsCard.visibility = View.GONE
                binding.errorMessage.visibility = View.GONE
            }
        })

        /*blockChainViewModel.message.observe(this, Observer { message ->
            binding.errorMessage.text = getString(message)
            if(binding.errorMessage.text.isEmpty()){
                binding.contentCard.statsCard.visibility = View.VISIBLE
                binding.errorMessage.visibility = View.GONE
            }  else {
                binding.contentCard.statsCard.visibility = View.GONE
                binding.errorMessage.visibility = View.VISIBLE
            }
        })*/
    }
}

