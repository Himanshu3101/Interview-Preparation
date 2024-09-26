package com.example.interview.data.repository

import com.example.interview.data.remote.APIServices
import javax.inject.Inject

class HighSchoolRepository @Inject constructor(private val apiServices: APIServices){

    suspend fun getHighSchool() = apiServices.getHighSchool()

    suspend fun getSATScore() = apiServices.getSATScore()


}