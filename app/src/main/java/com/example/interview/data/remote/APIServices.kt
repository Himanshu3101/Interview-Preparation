package com.example.interview.data.remote

import com.example.interview.data.local.SATScore
import com.example.interview.data.local.HighSchoolList
import retrofit2.http.GET

interface APIServices {

    @GET("s3k6-pzi2.json")
    suspend fun getHighSchool():List<HighSchoolList>

    @GET("f9bf-2cp4.json")
    suspend fun getSATScore():List<SATScore>?
}