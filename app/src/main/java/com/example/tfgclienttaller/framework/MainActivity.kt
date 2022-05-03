package com.example.tfgclienttaller.framework

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.tfgclienttaller.framework.loginscreen.Login
import com.example.tfgclienttaller.framework.loginscreen.LoginScreenContract
import com.example.tfgclienttaller.framework.loginscreen.LoginScreenViewModel
import com.example.tfgclienttaller.framework.navigation.Navegation
import com.example.tfgclienttaller.ui.theme.TFGClientTallerTheme
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewmodel : LoginScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TFGClientTallerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Navegation(activity = this@MainActivity)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val oneTapClient: SignInClient = Identity.getSignInClient(this)
        when (requestCode) {
            1 -> {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    viewmodel.handleEvent(LoginScreenContract.Event.doLoginWithGoogleFirebase(this,credential))

                } catch (e: ApiException) {
                    Log.e("ActivityResult Error","Error al recuperar las credenciales")
                }
            }
        }
    }




}
