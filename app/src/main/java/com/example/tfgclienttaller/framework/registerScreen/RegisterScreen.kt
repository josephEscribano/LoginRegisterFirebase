package com.example.tfgclienttaller.framework.registerScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun DataScreen(viewModel: RegisterScreenViewModel = hiltViewModel()){


    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            placeholder = { Text(text = "Email")},
            value = viewModel.email,
            onValueChange = {viewModel.handleEvent(RegisterScreenContract.Event.onEmailChange(it))}
        )
        Spacer(modifier = Modifier.size(10.dp))

        TextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = { Text(text = "Contraseña")},
            value = viewModel.pass,
            onValueChange = {viewModel.handleEvent(RegisterScreenContract.Event.onPassChange(it))}
        )
        Spacer(modifier = Modifier.size(10.dp))


        TextField(
            placeholder = { Text(text = "Nombre")},
            value = viewModel.name,
            onValueChange = {viewModel.handleEvent(RegisterScreenContract.Event.onNameChange(it))}
        )

        Spacer(modifier = Modifier.size(10.dp))

        TextField(
            placeholder = { Text(text = "Apellido")},
            value = viewModel.surname,
            onValueChange = {viewModel.handleEvent(RegisterScreenContract.Event.onSurnameChange(it))}
        )

        Spacer(modifier = Modifier.size(10.dp))

        TextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            placeholder = { Text(text = "Phone")},
            value = viewModel.phone,
            onValueChange = {viewModel.handleEvent(RegisterScreenContract.Event.onPhoneChange(it))}
        )


    }
}