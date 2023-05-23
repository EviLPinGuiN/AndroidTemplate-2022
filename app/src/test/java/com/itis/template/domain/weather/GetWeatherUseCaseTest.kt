package com.itis.template.domain.weather

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class GetWeatherUseCaseTest {

    @MockK
    lateinit var weatherRepository: WeatherRepository

    private lateinit var useCase: GetWeatherUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetWeatherUseCase(weatherRepository = weatherRepository)
    }

    @Test
    fun whenGetWeatherUseCaseExpectedSuccess() {
        // arrange
        val requestQuery = "Asd"
        val expectedTemp = 123.0
        val expectedData: WeatherInfo = mockk {
            every { temperature } returns expectedTemp
            every { humidity } returns 500
        }
        coEvery {
            weatherRepository.getWeather(requestQuery)
        } returns expectedData

        // act
        runTest {
            val result = useCase.invoke(query = requestQuery)

            // assert
            assertEquals(expectedData, result)
            assertEquals(expectedTemp, result.temperature)
        }
    }

    @Test
    fun `Когда мы вызываем GetWeatherUseCase ожидаем получить ошибку`() {
        // arrange
        val requestQuery = "Asd"
        coEvery {
            weatherRepository.getWeather(requestQuery)
        } throws IllegalStateException("Test")

        // act
        runTest {
            // assert
            assertFailsWith<IllegalStateException> {
                useCase.invoke(query = requestQuery)
            }
        }
    }
}