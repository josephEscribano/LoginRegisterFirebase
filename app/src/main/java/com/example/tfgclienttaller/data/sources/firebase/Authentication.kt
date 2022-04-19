package com.example.tfgclienttaller.data.sources.firebase

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject


class Authentication @Inject constructor(){

    fun login(activity: ComponentActivity,email: String, pass: String) {
        val auth : FirebaseAuth = Firebase.auth
        auth.signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener(activity){task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
//                    Toast.makeText(activity, "Te has logueado",
//                        Toast.LENGTH_SHORT).show()
                    Log.d(ConstantesFirebase.LOGINSUCCESTAG, ConstantesFirebase.LOGINSUCCESSMENSAJE)
                    auth.currentUser?.getIdToken(true)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ConstantesFirebase.ERRORLOGINGTAG, ConstantesFirebase.MENSAJETAGLOGIN, task.exception)
                    Toast.makeText(activity, ConstantesFirebase.ERRORLOGIN,
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun register(activity: ComponentActivity,email: String, pass: String){
        val auth : FirebaseAuth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    Log.d(ConstantesFirebase.REGISTROSUCCESSTAG, ConstantesFirebase.REGISTROSUCCESMENSAJE)
                    val user = auth.currentUser

                }else{
                    if (task.exception is  FirebaseAuthInvalidCredentialsException){
                        Log.w(ConstantesFirebase.REGISTROERRORCREDENTIALTAG, ConstantesFirebase.REGISTROERRORMENSAJETAG, task.exception)
                        Toast.makeText(activity, ConstantesFirebase.ERRORCORREOINVALIDO,
                            Toast.LENGTH_SHORT).show()
                    }else if(task.exception is  FirebaseAuthWeakPasswordException){
                        Log.w(ConstantesFirebase.REGISTROERRORCREDENTIALTAG, ConstantesFirebase.ERRORCONTRASEÑAINVALIDA, task.exception)
                        Toast.makeText(activity, ConstantesFirebase.ERRORMENSAJECONTRASEÑAINVALIDA,
                            Toast.LENGTH_SHORT).show()
                    }
                }

            }
    }

    fun dataUser() : String{
        val user = Firebase.auth.currentUser
        var uid = ""
        user?.let {
            uid = user.uid

        }

        return uid
    }
}