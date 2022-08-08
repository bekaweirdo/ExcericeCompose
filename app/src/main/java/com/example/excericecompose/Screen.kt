package com.example.excericecompose

const val DASHBOARD_ARGUMENT_KEY = "id"

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Dashboard: Screen("dashboard/{$DASHBOARD_ARGUMENT_KEY}"){
        fun passArgument(id: String): String {
            return "dashboard/$id"
        }
    }
}