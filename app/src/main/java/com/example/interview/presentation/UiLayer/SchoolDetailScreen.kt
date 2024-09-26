package com.example.interview.presentation.UiLayer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.interview.presentation.viewmodel.SchoolListViewModel

@Composable
fun SchoolDetailScreen (
    dbn : String,
    viewModel: SchoolListViewModel = hiltViewModel()
) {

    val school = viewModel.highSchool.collectAsState().value.firstOrNull() {it.dbn == dbn}
    val score = viewModel.satScores.collectAsState().value[dbn]

    Scaffold {contentPadding ->

        Column (
            modifier = Modifier
                .padding(contentPadding)
                .padding(16.dp)
        ) {
            school.let { 
                Text(text = "School : ${school?.school_name}")
                Text(text = "Location : ${school?.location}")
                Text(text = "Phone : ${school?.phone_number}")
            }

            Spacer(modifier = Modifier.padding(16.dp))

            score.let {
                Text(text = "SAT MATH : ${it?.sat_math_avg_score}")
                Text(text = "SAT READING : ${it?.sat_critical_reading_avg_score}")
                Text(text = "SAT Writing : ${it?.sat_writing_avg_score}")
            }
        }
    }
}