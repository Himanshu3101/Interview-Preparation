package com.example.interview.navigation

import androidx.compose.runtime.Composable


import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.interview.navigation.Destination.schoolDetails


import com.example.interview.navigation.Destination.schoolList
import com.example.interview.presentation.UiLayer.HighSchoolListScreen
import com.example.interview.presentation.UiLayer.SchoolDetailScreen

object Destination{
    const val schoolList = "school_List"
    const val schoolDetails = "school_List/{dbn}"

}

@Composable
fun AppNavigation(navController: NavHostController){

    NavHost(navController, startDestination = schoolList) {
        composable(schoolList) {
            HighSchoolListScreen {
                navController.navigate("school_List/$it")
            }
        }

        composable(schoolDetails) {navBackStack->
            val dbn = navBackStack.arguments?.getString("dbn")
            SchoolDetailScreen(dbn = dbn.toString())
        }
    }
}