package com.example.sayaradz.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.sayaradz.Main2Activity
import com.example.sayaradz.R
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.facebook.login.LoginManager
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_before_login.*
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import java.util.*


class BeforeLoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    private val RC_SIGN_IN = 9000
    private var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        setContentView(com.example.sayaradz.R.layout.activity_before_login)


        var btnLoginFacebook = login_button

        btnLoginFacebook.setOnClickListener {
            // Login
            callbackManager = CallbackManager.Factory.create()
            //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        Log.d("MainActivity", "Facebook token: " + loginResult.accessToken.token)
                        startActivity(Intent(applicationContext, Main2Activity::class.java))
                    }

                    override fun onCancel() {
                        Log.d("MainActivity", "Facebook onCancel.")

                    }

                    override fun onError(error: FacebookException) {
                        Log.d("MainActivity", "Facebook onError.")

                    }
                })
        }

       /* var callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {

                    Log.d("tagfaacce", loginResult.accessToken.token)
                }

                override fun onCancel() {
                    // App code
                }
                override fun onError(exception: FacebookException) {
                    // App code
                }
            })*/

        val googleSignInOptions : GoogleSignInOptions = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val googleApiClient = GoogleApiClient
            .Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
            .build()
        google_button.setOnClickListener{
            val intent : Intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
            startActivityForResult(intent, RC_SIGN_IN)
            this.finish()
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN) {
            val result : GoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }

        callbackManager?.onActivityResult(requestCode, resultCode, data)
        //onActivityResult(requestCode, resultCode, data)
    }

    private fun handleSignInResult(signInResult: GoogleSignInResult) {
        if(signInResult.isSuccess) {
            // Authenticated
            val account : GoogleSignInAccount? = signInResult.signInAccount
            startActivity(Intent(applicationContext, Main2Activity::class.java))
            Log.d("Tag",  account?.id)//106534152978689414191
            this.finish()
        } else {
            // Failed
        }
    }
}
