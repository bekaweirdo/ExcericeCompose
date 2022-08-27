package com.example.excericecompose

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.excericecompose.coldplay.DASHBOARD_ARGUMENT_KEY
import com.example.excericecompose.coldplay.Screen
import com.example.excericecompose.coldplay.vm.ConversionVm

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    val viewModel: ConversionVm = viewModel()
    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(viewModel,navController)
        }
        composable(Screen.Dashboard.route, arguments = listOf(navArgument(DASHBOARD_ARGUMENT_KEY) {
            type = NavType.IntType
        })) {
            val value = it.arguments?.getInt(DASHBOARD_ARGUMENT_KEY)
            DashboardScreen(navController, viewModel, value!!)
        }
    }
}