package com.example.interview.presentation.UiLayer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.interview.presentation.viewmodel.SchoolListViewModel

@Composable
fun HighSchoolListScreen(
    viewModel: SchoolListViewModel = hiltViewModel(),
    onSchoolSelected:(String) -> Unit
){
    val highSchool by viewModel.highSchool.collectAsState()

    Scaffold (modifier = Modifier.padding(16.dp)
    ) { padding->
        LazyColumn (
            contentPadding = padding,
            modifier = Modifier.padding(top = 30.dp)
        ){
            items(highSchool){school ->
                Text(text = school.school_name, modifier = Modifier
                    .fillMaxWidth()
                    .clickable{ onSchoolSelected(school.dbn)}
                    .padding(16.dp))

            }
        }
    }
}