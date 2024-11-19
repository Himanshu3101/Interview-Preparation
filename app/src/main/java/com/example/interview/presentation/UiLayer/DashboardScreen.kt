package com.example.interview.presentation.UiLayer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.interview.data.local.HighSchoolList
import com.example.interview.navigation.Destination.AddschoolList
import com.example.interview.presentation.viewmodel.SchoolListViewModel

@Composable
fun DashboardScreen(
    navController:NavController,
    onSchoolSelect: (String) -> Unit
) {
    val  viewModel: SchoolListViewModel = hiltViewModel()
    val highSchools by viewModel.highSchool.collectAsState()


    Scaffold/*(
        modifier = Modifier.padding(6.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  navController.navigate(AddschoolList)},
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }

        }
    ) */{ padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.padding(top = 30.dp)
        ) {
            items(highSchools) { school ->
                 CardItem(school, onSchoolSelected = onSchoolSelect/*{it->
                     navController.navigate("school_List/$it")
                 }*/)
            }
        }
    }
}

@Composable
fun CardItem(school: HighSchoolList, onSchoolSelected: (String) -> Unit) {
    Card (modifier = Modifier
        .fillMaxWidth()
        .clickable { onSchoolSelected(school.dbn) }
        .padding(5.dp))
    {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(
                text = school.state_code, style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = school.school_name, style = MaterialTheme.typography.bodySmall
            )

        }
    }
}
