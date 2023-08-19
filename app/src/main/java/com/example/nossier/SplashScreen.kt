package com.example.nossier
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val lottieAnimationView: LottieAnimationView = findViewById(R.id.lottieAnimationView)
        lottieAnimationView.playAnimation()
        val textView: TextView = findViewById(R.id.textView)

        val animator = ValueAnimator.ofFloat(1f, 0f)
        animator.duration = lottieAnimationView.duration + 7000
        animator.addUpdateListener { valueAnimator ->
            val alpha = valueAnimator.animatedValue as Float
            lottieAnimationView.alpha = alpha
        }
        val translateY = textView.height.toFloat()
        val translateYAnimator = ObjectAnimator.ofFloat(textView, "translationY", translateY, 0f)
        translateYAnimator.duration = 1000 // Set your desired animation duration

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(animator, translateYAnimator)
        animatorSet.start()
        val ad = lottieAnimationView.duration
        val SPLASH_DELAY   = 5000 + ad
        Handler().postDelayed({
            val mainIntent = Intent(this, GettinStarted::class.java)
            startActivity(mainIntent)
            finish() // Finish the splash activity so the user can't go back to it
        }, SPLASH_DELAY.toLong())



    }
}
