package com.br.natanfelipe.bitcoinapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.br.natanfelipe.bitcoinapp.connection.ApiService
import com.br.natanfelipe.bitcoinapp.model.Bitcoin
import com.br.natanfelipe.bitcoinapp.model.Values
import com.github.testcoroutinesrule.TestCoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when`
import retrofit2.Response
import java.util.*

class BlockChainViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var apiService: ApiService

    @InjectMocks
    var viewModel = BlockChainViewModel()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `Load stats data successfuly`() = testCoroutineRule.runBlockingTest() {
        //MainScope().launch {
            val calendar = Calendar.getInstance()
            val value1 = Values(calendar.timeInMillis, 10.0)
            val value2 = Values(calendar.timeInMillis, 40.0)
            val stats = arrayListOf(value1, value2)
            val bitcoin = Bitcoin("Test", "$", "7days", "test", stats)

            val response = Response.success(bitcoin)

            val oi2 = `when`(apiService.getStats()).thenReturn(response)

            val oi = viewModel.loadData(isConnected)

            assertEquals(2, viewModel.stats.value?.values?.size)


            assertEquals(false, viewModel.isLoading)
        //}
    }

    /*@Test
    fun `Failed to load stats data`() {

    }*/

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}