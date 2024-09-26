package com.example.interview.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interview.data.local.HighSchoolList
import com.example.interview.data.local.SATScore
import com.example.interview.data.repository.HighSchoolRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolListViewModel @Inject constructor(private val schoolListRepository: HighSchoolRepository) : ViewModel(){

    private val _highSchool = MutableStateFlow<List<HighSchoolList>>(emptyList())
    val highSchool : StateFlow<List<HighSchoolList>> = _highSchool

    private val _satScores = MutableStateFlow<Map<String, SATScore>>(emptyMap())
    val satScores : StateFlow<Map<String, SATScore>> = _satScores


    init{
        fetchHighSchoolList()
        fetchSATScores()
    }

    fun fetchHighSchoolList(){
        viewModelScope.launch {
            val highSchool = schoolListRepository.getHighSchool()
            _highSchool.value = highSchool
        }
    }

    fun fetchSATScores(){
        viewModelScope.launch {
            val scores = schoolListRepository.getSATScore() ?: emptyList()
            _satScores.value = scores.associateBy { it.dbn }
        }
    }
}