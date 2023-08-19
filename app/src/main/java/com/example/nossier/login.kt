package com.example.nossier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
//2


class login : AppCompatActivity() {

    private val RC_SIGN_IN = 9001
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var loadingView: View
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        FirebaseApp.initializeApp(this)
        val google = findViewById<ImageView>(R.id.google)
        val google2 = findViewById<TextView>(R.id.googletext)

        val alpha: LottieAnimationView = findViewById(R.id.alpha)
        alpha.playAnimation()
        alpha.loop(true)


        loadingView = findViewById<View>(R.id.loadingView)




        firebaseAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        google.setOnClickListener {
            val signIntent = googleSignInClient.signInIntent
            startActivityForResult(signIntent, RC_SIGN_IN)
            showLoadingView()
        }
        google2.setOnClickListener {
            val signIntent = googleSignInClient.signInIntent
            startActivityForResult(signIntent, RC_SIGN_IN)
            showLoadingView()
        }
    }
    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val userId = firebaseAuth.currentUser?.uid
                    Toast.makeText(this,userId,Toast.LENGTH_SHORT).show()
                    if(userId!=null){

                        val database = FirebaseDatabase.getInstance().reference
                        Log.d(TAG, "Before setValue call")
                        database.child("users").child(userId).setValue(true)
                            .addOnCompleteListener { userCreationTask ->
                                if (userCreationTask.isSuccessful) {
                                    Log.d(TAG, "User added successfully")
                                    Toast.makeText(this, "User added to the database", Toast.LENGTH_SHORT).show()
                                } else {
                                    Log.d(TAG, "Failed to add user to the database")
                                    Toast.makeText(this, "Failed to add user to the database", Toast.LENGTH_SHORT).show()
                                }
                            }
                        Log.d(TAG, "After setValue call")

                    }
                    hideLoadingView()
                    Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
                    val profileRegisterIntent = Intent(this, Homescreen::class.java)
                    startActivity(profileRegisterIntent)
                    finish()
                } else {
                    Toast.makeText(this, "Sorry account was not created", Toast.LENGTH_SHORT).show()
                }
            }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
//                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }





    private fun hideLoadingView() {
        loadingView.visibility = View.GONE
    }

    private fun showLoadingView() {
        loadingView.visibility = View.VISIBLE
    }

}