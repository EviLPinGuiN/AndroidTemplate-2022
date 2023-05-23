package com.itis.template.data.weather

import com.itis.template.data.weather.datasource.remote.WeatherApi
import com.itis.template.data.weather.datasource.remote.response.WeatherResponse
import com.itis.template.domain.weather.WeatherInfo
import com.itis.template.domain.weather.WeatherRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

class WeatherRepositoryImplTest {

    @MockK
    lateinit var api: WeatherApi

    private lateinit var repository: WeatherRepository

    private val testDispatcher: CoroutineDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = WeatherRepositoryImpl(api, testDispatcher)
    }

    private val mockData = mockk<WeatherResponse> {
        every { main } returns mockk {
            every { temp } returns 10.0
            every { humidity } returns 3
        }
        every { wind } returns mockk {
            every { speed } returns 10.0
            every { deg } returns 2
        }
        every { weather } returns listOf(
            mockk {
                every { icon } returns "test"
            }
        )
    }

    @Test
    fun whenCallGetWeatherExpectedSuccess() = runTest(testDispatcher) {
        val expectedQuery = "asd"
        val expectedResult = WeatherInfo(
            temperature = 10.0,
            windSpeed = 10.0,
            windDeg = 2,
            humidity = 3,
            icon = "test"
        )
        // arrange
        coEvery {
            api.getWeather(expectedQuery)
        } returns mockData
        // act
        val result = repository.getWeather(expectedQuery)
        // assert
        assertEquals(expectedResult, result)
    }

    @Test
    fun whenCallGetWeatherExpectedError() = runTest(testDispatcher) {
        val expectedQuery = "asd"
        // arrange
        coEvery {
            api.getWeather(expectedQuery)
        } throws Throwable("test")
        // act
        // assert
        assertFailsWith<Throwable> {
            repository.getWeather(expectedQuery)
        }
    }
}