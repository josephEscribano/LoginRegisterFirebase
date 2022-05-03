package com.example.tfgclienttaller.framework.loginscreen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tfgclienttaller.R
import com.example.tfgclienttaller.framework.navigation.Routes
import com.example.tfgclienttaller.ui.theme.AzulSecundario
import com.example.tfgclienttaller.ui.theme.White
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Login(
    volver: Boolean,
    activity: ComponentActivity,
    viewModel: LoginScreenViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    //joseph.escribano1995 - JOSEPH28
    //TODO HACER QUE RECUPERE LA CONTRASEÑA
    //TODO LOGIN CON GOOGLE
    //TODO HACER EL CONTROL DE ERRORES DE LOS CAMPOS
    //TODO COMPROBAR LA SESION EN EL SERVER
    //TODO CUANDO QUERAMOS SABER EL TIPO DE USUARIO HACEMOS UNA LLAMADA AL SERVER Y COMPORBAMOS EL TIPO DE USUARIO CON SECURITYCONTEXT PARA OBTENER EL EMAIL
    //TODO CON ESE EMAIL BUSCAMOS EN LA BASE DE DATOS Y DEVOLVEMOS EL USAURIO CON EL TIPO

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val logueado = viewModel.loginScreenState.collectAsState().value.logueado
    Scaffold(
        scaffoldState = scaffoldState
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
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logocompletoazul),
                    contentDescription = "Imagen Logo"
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
                    onClick = {
                        viewModel.handleEvent(LoginScreenContract.Event.doLoginWithGoogle(activity))

                    }
                ) {
                    Text(
                        text = "Iniciar Sesión con Google",
                        modifier = Modifier.align(Top),
                        color = Color.Gray
                    )
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = {
                        viewModel.handleEvent(LoginScreenContract.Event.onPassChange(it))
                    })
                Text(
                    text = "Recuperar mi contraseña",
                    textDecoration = TextDecoration.Underline,
                    color = AzulSecundario,
                    modifier = Modifier
                        .clickable {
                            onNavigate(Routes.RECOVERPASSSCREEN)
                        }
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
                        onClick = { onNavigate(Routes.REGISTERSCREEN) },
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
                        onClick = {
                            viewModel.handleEvent(LoginScreenContract.Event.doLoginFirebase(activity))
                            if (logueado){
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar("Usuario logueado")
                                }
                            }

                        },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Iniciar Sesión", color = Color.White)
                    }
                }

            }

            if (volver) {
                LaunchedEffect(key1 = true) {
                    scaffoldState.snackbarHostState.showSnackbar("Usuario registrado")
                }
            }


        }
    }


}

