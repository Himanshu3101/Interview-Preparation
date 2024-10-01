package com.example.interview.navigation

import android.util.Log
import androidx.compose.runtime.Composable


import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.interview.navigation.Destination.AddschoolList
import com.example.interview.navigation.Destination.schoolDetails


import com.example.interview.navigation.Destination.schoolList
import com.example.interview.presentation.UiLayer.AddSchoolScreen
import com.example.interview.presentation.UiLayer.DashboardScreen
import com.example.interview.presentation.UiLayer.SchoolDetailScreen

object Destination {
    const val schoolList = "school_List"
    const val AddschoolList = "school_List_Add"
    const val schoolDetails = "school_List/{dbn}"

}

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(navController, startDestination = schoolList) {
        composable(schoolList) {
            DashboardScreen(navController = navController, onSchoolSelect =  {it->
                navController.navigate("school_List/$it")
            })
        }

        composable(AddschoolList){
            AddSchoolScreen()
        }

        composable(schoolDetails) { navBackStack ->
            val dbn = navBackStack.arguments?.getString("dbn")
            if (dbn != null) {
                SchoolDetailScreen(dbn = dbn.toString())
            }
        }
    }
}