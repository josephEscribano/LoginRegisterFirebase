package com.example.tfgclienttaller.framework.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tfgclienttaller.framework.loginscreen.Login
import com.example.tfgclienttaller.framework.registerScreen.DataScreen

@Composable
fun Navegation(activity: ComponentActivity) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGINSCREEN
    ) {
        composable(
            route = Routes.LOGINSCREEN
        ){
            Login(
                activity = activity,
                onNavigate = {
                navController.navigate(Routes.REGISTERSCREEN)
            })
        }
        composable(
            route = Routes.REGISTERSCREEN
        ){
            DataScreen()
        }
    }
}