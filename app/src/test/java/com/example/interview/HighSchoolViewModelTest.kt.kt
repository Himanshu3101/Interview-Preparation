package com.example.interview

import com.example.interview.data.local.HighSchoolList
import com.example.interview.data.local.SATScore
import com.example.interview.data.repository.HighSchoolRepository
import com.example.interview.presentation.viewmodel.SchoolListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HighSchoolViewModelTest {

    @Mock
    private lateinit var repository: HighSchoolRepository
    private lateinit var viewModel: SchoolListViewModel


    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        repository = mock ()
        viewModel = SchoolListViewModel(repository)
    }

    @After
    fun tearDown() {
        // Reset Main dispatcher after the test
        Dispatchers.resetMain()
    }

    @Test
    fun `test fetchHighSchool updates state correctly`() = runTest {
        // Arrange
        val mockHighSchools = listOf(
            HighSchoolList("01M292", "Henry Street School", "New York", "null", "123456789", "henrystreet.edu","Henry Street School", "New York","Henry Street School", "New York"
            ,"Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York"
            ,"Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York"
            ,"Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York"
            ,"Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School"),
            HighSchoolList("02M307", "Art and Design", "New York", "null", "987654321", "artdesign.edu" ,"Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York"
                ,"Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York"
                ,"Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York"
                ,"Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School", "New York","Henry Street School","Henry Street School", "New York","Henry Street School", "New York",)
        )
        whenever(repository.getHighSchool()).thenReturn(mockHighSchools)

        // Act
        viewModel.fetchHighSchoolList()
        advanceUntilIdle()

        // Assert
        assertEquals(mockHighSchools, viewModel.highSchool.first())
    }

    @Test
    fun `test fetchSATScores updates state correctly`() = runTest {

        val mockSATScore = listOf(
            SATScore("01M292", "1000", "2000", "3000","ef","df"),
            SATScore("02M307", "4000", "5000", "6000","ef","fe")
        )
        whenever(repository.getSATScore()).thenReturn(mockSATScore)

        viewModel.fetchSATScores()
        advanceUntilIdle()

        val expcted = mockSATScore.associateBy { it.dbn }
        assertEquals(expcted, viewModel.satScores.first())

    }

    @Test
    fun `test fetchSATScores handles null response`()= runTest {
        whenever(repository.getSATScore()).thenReturn(null)
        viewModel.fetchSATScores()
        advanceUntilIdle()
        assertEquals(emptyMap<String, SATScore>(), viewModel.satScores.first())
    }

}