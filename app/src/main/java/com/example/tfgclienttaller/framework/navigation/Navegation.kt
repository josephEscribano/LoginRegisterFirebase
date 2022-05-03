package com.example.tfgclienttaller.framework.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tfgclienttaller.framework.RecoverPass.RecoverPassScreen
import com.example.tfgclienttaller.framework.loginscreen.Login
import com.example.tfgclienttaller.framework.registerScreen.DataScreen

@Composable
fun Navegation(activity: ComponentActivity) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGINSCREEN + ConstantesNavegacion.LOGINSCREENPARAMETER
    ) {
        composable(
            route = Routes.LOGINSCREEN + ConstantesNavegacion.LOGINSCREENPARAMETER,
            arguments = listOf(
                navArgument(name = "volver"){
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ){
            Login(
                volver = it.arguments?.get(ConstantesNavegacion.RECUPERARARGUMENTLOGIN) as Boolean ,
                activity = activity,
                onNavigate = {
                navController.navigate(it)
            })
        }
        composable(
            route = Routes.REGISTERSCREEN
        ){
            DataScreen(
                activity = activity,
                onNavigate = {
                    navController.navigate(Routes.LOGINSCREEN + ConstantesNavegacion.REGISTERTOLOGINPARAMETER + true)
                }
            )
        }
        composable(
            route = Routes.RECOVERPASSSCREEN
        ){
            RecoverPassScreen(activity = activity)
        }
    }
}