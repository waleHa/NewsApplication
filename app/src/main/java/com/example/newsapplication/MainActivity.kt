package com.example.newsapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.newsapplication.databinding.ActivityMainBinding
import com.example.newsapplication.ui.ArticleFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(binding.fragmentContainer.id) as NavHostFragment

        val navController = navHostFragment.navController

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, ArticleFragment()).commit()



//        binding.bottomNavigation.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.nav_article-> {
//                    Log.i(TAG, "setFragment: Article")
//                    navController.navigate(R.id.nav_article)
//                    true
//                }
//
//                R.id.nav_news -> {
//                    Log.i(TAG, "setFragment: News")
//                    navController.navigate(R.id.nav_news)
//                    true
//                }
//
//                else -> {
//                    Log.i(TAG, "setFragment: Else")
//                    false
//                }
//            }
//        }
    }
}