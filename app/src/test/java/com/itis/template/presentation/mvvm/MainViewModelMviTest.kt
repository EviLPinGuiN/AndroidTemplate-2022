package com.itis.template.presentation.mvvm

import com.itis.template.domain.weather.GetWeatherUseCase
import com.itis.template.domain.weather.WeatherInfo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class MainViewModelMviTest {

    @MockK
    lateinit var useCase: GetWeatherUseCase

    private lateinit var viewModel: MainViewModelMvi

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MainViewModelMvi(useCase)
    }

    @Test
    fun whenReducerExpectedEventOnQueryChange() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        // arrange
        val query = "Kazan"
        val mockWeather: WeatherInfo = mockk()
        coEvery { useCase.invoke(query) } returns mockWeather

        // act
        viewModel.reducer(MainViewModelMvi.ScreenEvent.OnQueryChange(query))

        // assert

        val list = viewModel.stateData.toList()

        assertEquals(
            MainViewModelMvi.ScreenStateData(
                isLoading = true
            ),
            list[0]
        )
        assertEquals(
            MainViewModelMvi.ScreenStateData(
                isLoading = false
            ),
            list[2]
        )
    }

    fun whenUserClickToSnackar() {

        viewModel.onClickSnacbar()

        verify {
            action = ShowSnackbar
        }
    }
}