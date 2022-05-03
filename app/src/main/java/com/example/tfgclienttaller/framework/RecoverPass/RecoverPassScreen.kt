package com.example.tfgclienttaller.framework.RecoverPass

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tfgclienttaller.R
import com.example.tfgclienttaller.framework.loginscreen.LoginScreenContract
import com.example.tfgclienttaller.ui.theme.AzulSecundario
import com.example.tfgclienttaller.ui.theme.White
import kotlinx.coroutines.launch


@Composable
fun RecoverPassScreen(
    viewModel: RecoverPassScreenViewModel = hiltViewModel(),
    activity: ComponentActivity
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = R.drawable.logocompletoazul),
                contentDescription = "Imagen Logo"
            )

            Spacer(modifier = Modifier.size(20.dp))

            OutlinedTextField(
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = White,
                ),
                placeholder = { Text(text = "Email") },
                value = viewModel.email,
                onValueChange = {
                    viewModel.handleEvent(RecoverPassContract.Event.onEmailChange(it))
                })

            Spacer(modifier = Modifier.size(20.dp))

            OutlinedButton(
                modifier = Modifier
                    .width(260.dp)
                    .size(50.dp)
                    .border(width = 1.dp, color = White, shape = RoundedCornerShape(12.dp)),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 8.dp,
                    disabledElevation = 0.dp
                ), colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = AzulSecundario
                ),
                onClick = {
                    viewModel.handleEvent(RecoverPassContract.Event.recoverPass(activity))

                },
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Recuperar contraseña", color = Color.White)
            }
        }

    }

}