package com.example.newsapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.newsapplication.databinding.ActivitySignBinding
import com.example.newsapplication.ui.signin.SignInFragment
import com.example.newsapplication.ui.signin.SignInGoogleFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Replace the container view with the SignInFragment
        if(savedInstanceState == null){
            supportFragmentManager.commit {
                replace(binding.fragmentContainerTop.id, SignInFragment())
//                replace(binding.fragmentContainerBottom.id,SignInGoogleFragment())
            }
        }
    }
}