package com.example.excericecompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Dashboard.route, arguments = listOf(navArgument(DASHBOARD_ARGUMENT_KEY){
            type = NavType.StringType
        })) {
            val value = it.arguments?.getString(DASHBOARD_ARGUMENT_KEY)
            DashboardScreen(navController,value!!)
        }
    }
}