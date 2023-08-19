package com.example.nossier

import android.content.Intent
import android.media.Image
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.MediaController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.firebase.auth.FirebaseUser
import android.widget.TextView
import android.widget.VideoView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.GoogleAuthProvider

class profile : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val selectedTextView = view.findViewById<TextView>(R.id.selectedTextView)
        val pressMe =view.findViewById<ImageView>(R.id.pressme)
        val profileImageView = view.findViewById<ImageView>(R.id.profileImageView)

        val user: FirebaseUser? = auth.currentUser
        user?.photoUrl?.let { photoUrl ->
            Glide.with(requireContext())
                .load(photoUrl)
                .transform(CircleCrop()) // Apply circular crop to the image
                .into(profileImageView)
        }
        val emailTextView = view.findViewById<TextView>(R.id.emailTextView)
        emailTextView.text = user?.email
//        val videoView = view.findViewById<VideoView>(R.id.noxo)
//        videoView.setOnCompletionListener { mediaPlayer: MediaPlayer ->
//            // Hide the VideoView when the video is completed
//            mediaPlayer.reset() // Reset the MediaPlayer for the next playback
//            videoView.visibility = View.GONE
//        }
//        pressMe.setOnClickListener {
//            videoView.visibility = View.VISIBLE
//
//            // Load and play the video
//            val videoPath = "android.resource://" + requireContext().packageName + "/" + R.raw.gama
//            val videoUri = Uri.parse(videoPath)
//            videoView.setVideoURI(videoUri)
//
//            videoView.start()
//        }

        pressMe.setOnClickListener {
            // Make the TextView visible when the button is pressed
            selectedTextView.visibility = View.VISIBLE
        }

        val logoutButton = view.findViewById<ImageView>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            auth.signOut()

            // Navigate to the "Getting Started" activity
            val intent = Intent(requireContext(), GettinStarted
            ::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }
    }

}