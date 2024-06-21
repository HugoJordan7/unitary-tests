package com.example.unitarytests

import androidx.lifecycle.viewModelScope
import com.example.unitarytests.feature.main.viewModel.MainViewModel
import com.example.unitarytests.source.NumberRepository
import io.mockk.InternalPlatformDsl.toArray
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val numberRepository = mockk<NumberRepository>()
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(numberRepository)
    }

    @Test
    fun viewModelAppendSuccess() {
        val randomNumber = 4
        coEvery { numberRepository.getRandomNumber(100) } returns randomNumber
        for(i in 0 .. 2) viewModel.append()
        assertEquals(mutableListOf(4,4,4), viewModel.numberList.value)
        assertTrue(viewModel.numberList.value.contains(randomNumber))
        assertFalse(viewModel.isFailure.value)
    }

    @Test
    fun viewModelAppendFailure() {
        val randomNumber = 4
        coEvery { numberRepository.getRandomNumber(100) } returns randomNumber
        for(i in 0 .. 10) viewModel.append()
        assertTrue(viewModel.isFailure.value)
        assertEquals("Limite de 9 números atingidos", viewModel.errorMessage.value)
    }

    @Test
    fun viewModelRemoveSuccess() {
        var randomNumber = 4
        coEvery { numberRepository.getRandomNumber(100) } returns randomNumber
        for(i in 0 .. 8) viewModel.append()
        randomNumber = 0
        coEvery { numberRepository.getRandomNumber(100) } returns randomNumber
        viewModel.append()
        viewModel.remove()
        assertFalse(viewModel.numberList.value.contains(randomNumber))
        assertFalse(viewModel.isFailure.value)
    }

    @Test
    fun viewModelRemoveFailure() {
        viewModel.remove()
        assertTrue(viewModel.isFailure.value)
        assertEquals("A lista já está vazia", viewModel.errorMessage.value)
    }

}