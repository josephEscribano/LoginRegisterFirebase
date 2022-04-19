package com.example.tfgclienttaller.framework.registerScreen

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
import com.example.tfgclienttaller.ui.theme.AzulSecundario
import com.example.tfgclienttaller.ui.theme.White


@Composable
fun DataScreen(
    activity: ComponentActivity,
    viewModel: RegisterScreenViewModel = hiltViewModel(),
    onNavigate: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logocompletoazul),
                contentDescription = "Imagen Login"
            )
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = White,
                ),
                placeholder = { Text(text = "Email") },
                value = viewModel.email,
                onValueChange = {
                    viewModel.handleEvent(RegisterScreenContract.Event.onEmailChange(it))
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
                    viewModel.handleEvent(RegisterScreenContract.Event.onPassChange(it))
                })
            Spacer(modifier = Modifier.size(10.dp))

            OutlinedTextField(
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = White,
                ),
                placeholder = { Text(text = "Nombre") },
                value = viewModel.name,
                onValueChange = {
                    viewModel.handleEvent(RegisterScreenContract.Event.onNameChange(it))
                })

            Spacer(modifier = Modifier.size(10.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = White,
                ),
                placeholder = { Text(text = "Apellido") },
                value = viewModel.surname,
                onValueChange = {
                    viewModel.handleEvent(RegisterScreenContract.Event.onSurnameChange(it))
                })

            Spacer(modifier = Modifier.size(10.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = White,
                ),
                placeholder = { Text(text = "Phone") },
                value = viewModel.phone,
                onValueChange = {
                    viewModel.handleEvent(RegisterScreenContract.Event.onPhoneChange(it))
                })

            Spacer(modifier = Modifier.size(30.dp))
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
                onClick = {
//                    onNavigate()
//                    viewModel.handleEvent(RegisterScreenContract.Event.register(activity))
                    viewModel.handleEvent(RegisterScreenContract.Event.registerServer)
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Registrarse", color = Color.White)
            }


        }
    }


}