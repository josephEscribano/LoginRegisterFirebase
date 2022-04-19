package com.example.tfgclienttaller.framework.loginscreen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tfgclienttaller.R
import com.example.tfgclienttaller.ui.theme.AzulSecundario
import com.example.tfgclienttaller.ui.theme.White

@Composable
fun Login(
    activity: ComponentActivity,
    viewModel: LoginScreenViewModel = hiltViewModel(),
    onNavigate: () -> Unit
) {
    LaunchedEffect(key1 = true){
        viewModel.handleEvent(LoginScreenContract.Event.sendToken)
    }
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
        ) {
            Image(
                painter = painterResource(id = R.drawable.logocompletoazul),
                contentDescription = "Imagen Login"
            )

            Spacer(modifier = Modifier.size(20.dp))

            OutlinedButton(
                modifier = Modifier
                    .width(280.dp)
                    .size(40.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 8.dp,
                    disabledElevation = 0.dp
                ), colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.White
                ),
                onClick = { viewModel.handleEvent(LoginScreenContract.Event.doLogin(activity)) }
            ) {
                Text(text = "Iniciar Sesión con Google", modifier = Modifier.align(Top), color = Color.Gray)
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Image(
                    modifier = Modifier
                        .size(30.dp)
                        .width(50.dp),
                    painter = painterResource(id = R.drawable.imagengoogle),
                    contentDescription = "Google"
                )

            }
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = White,
                ),
                placeholder = { Text(text = "Email") },
                value = viewModel.email,
                onValueChange = {
                    viewModel.handleEvent(LoginScreenContract.Event.onEmailChange(it))
                })
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = White,
                ),
                placeholder = { Text(text = "Contraseña") },
                value = viewModel.pass,
                onValueChange = {
                    viewModel.handleEvent(LoginScreenContract.Event.onPassChange(it))
                })
            Text(
                text = "Recuperar mi contraseña",
                textDecoration = TextDecoration.Underline,
                color = AzulSecundario,
                modifier = Modifier
                    .clickable { }
                    .align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .width(135.dp)
                        .size(50.dp)
                        .border(width = 1.dp, color = White, shape = RoundedCornerShape(8.dp)),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 8.dp,
                        disabledElevation = 0.dp
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = AzulSecundario
                    ),
                    onClick = { onNavigate() },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Registrarse", color = Color.White)
                }
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedButton(
                    modifier = Modifier
                        .width(135.dp)
                        .size(50.dp)
                        .border(width = 1.dp, color = White, shape = RoundedCornerShape(8.dp)),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 8.dp,
                        disabledElevation = 0.dp
                    ), colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = AzulSecundario
                    ),
                    onClick = { viewModel.handleEvent(LoginScreenContract.Event.doLogin(activity)) },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Iniciar Sesión", color = Color.White)
                }
            }

        }
    }
}