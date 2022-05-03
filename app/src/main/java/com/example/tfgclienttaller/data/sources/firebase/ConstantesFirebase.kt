package com.example.tfgclienttaller.data.sources.firebase

object ConstantesFirebase {
    const val LOGINSUCCESTAG= "Login success"
    const val LOGINSUCCESSMENSAJE = "Login:success"
    const val ERRORLOGINGTAG = "Fallo loguear"
    const val ERRORGETTOKENTAG = "Token no obtenido"
    const val MENSAJETAGLOGIN = "Login: Fail"
    const val MENSAJETAGTOKEN = "El token es nulo"
    const val ERRORLOGIN = "El correo o la contraseña son inválidos"
    const val REGISTROSUCCESSTAG = "Register Success"
    const val REGISTROSUCCESMENSAJE = "createUserWithEmail:success"
    const val REGISTROERRORCREDENTIALTAG = "Register fail"
    const val REGISTROERRORMENSAJETAG= "Correo inválido"
    const val REGISTROERROREMAILREPETIDO = "Correo repetido"
    const val REGISTROMENSAJEEMAILREPETIDO = "Se ha introducido un correo repetido"
    const val ERRORCORREOINVALIDO= "El correo inválido"
    const val ERRORCONTRASEÑAINVALIDA= "Contraseña invalida"
    const val ERRORMENSAJECONTRASEÑAINVALIDA=  "La contraseña tiene que tener mas de 6 caracteres"
    const val ERRORMENSAJEEMAILREPETIDO = "El correo introducido ya existe"

    // Constantes MyFirebaseMessagingService

    const val TAGMESSAGERECEIVE = "MyFirebaseMsgService"
    const val FROM= "From: "
    const val NOTIFICACION= "Contenido nofiticacion: "
    const val TITULO= "title"
    const val BODY= "body"

    const val TOKENFIREBASE= "tokenFirebase"
    const val TOKENREGISTERFAIL="Fetching FCM registration token failed"

    const val CHANGELANDNOTINAME = "notification"


    //Inidicio de sesion con Google

    const val ERRORINICIOSESIONGOOGLE = "Error Inicio Google"
    const val MENSAJEINICIOSESIONGOOGLE = "No se ha podido iniciar la ventana de google: "
    const val MENSAJEERRORONETAPGOOGLE = "Error al acceder a One tap"
    const val TAGCREDENTIALIDTOKEN= "Error credentialIdToken"
    const val MENSAJECREDENTIALIDTOKEN= "ID nulo"

    const val MENSAJEERRORLOGINSERVER= "No se ha podido loguear"
    const val TAGERRORLOGINSERVER= "Error login server"

    //Recuperar contraseña

    const val CORREOENVIADO = "El correo se ha enviado"
    const val CORREONOENVIADO= "El correo no se ha podido enviar"
    const val LANGUAGE= "es"
}