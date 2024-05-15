package com.example.newsapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.newsapplication.databinding.ActivityMainBinding
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

//        supportFragmentManager.beginTransaction()
//            .replace(binding.fragmentContainer.id, ArticleFragment()).commit()



        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_us_article-> {
                    Log.i("TAG: MainActivity", "setFragment: Article")
                    navController.navigate(R.id.nav_us_article)
                    true
                }

                R.id.nav_uk_article-> {
                    Log.i("TAG: MainActivity", "setFragment: Article")
                    navController.navigate(R.id.nav_uk_article)
                    true
                }

                else -> {
//                    Log.i(TAG, "setFragment: Else")
                    false
                }
            }
        }
    }
}