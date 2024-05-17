package com.example.newsapplication.ui.article

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import com.example.newsapplication.R
import com.example.newsapplication.databinding.ActivityArticleBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleActivity : AppCompatActivity() {
    lateinit var binding: ActivityArticleBinding
    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtain the FirebaseAnalytics instance.
        firebaseImpl()
        navigationHandle()

    }

    private fun firebaseImpl() {

        recordImageView()
//        crashImpl()
    }

    private fun recordImageView() {
        val id = "imageId"
        val name = "imageTitle"

//         [START image_view_event]
        Firebase.analytics.logEvent(
            FirebaseAnalytics.Event.SELECT_CONTENT,
            bundleOf(
                FirebaseAnalytics.Param.ITEM_ID to id,
                FirebaseAnalytics.Param.ITEM_NAME to name,
                FirebaseAnalytics.Param.CONTENT_TYPE to "image"
            )
        )
//         [END image_view_event]
    }

//    private fun crashImpl() {
//        // Creates a button that mimics a crash when pressed
//        val crashButton = Button(this)
//        crashButton.text = "Test Crash"
//        crashButton.setOnClickListener {
//            throw RuntimeException("Test Crash") // Force a crash
//        }
//
//        addContentView(
//            crashButton, ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//        )
//    }

    private fun navigationHandle() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(binding.fragmentContainer.id) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_us_article -> {
                    Log.i("TAG: MainActivity", "setFragment: Article")
                    navController.navigate(R.id.nav_us_article)
                    true
                }

                R.id.nav_uk_article -> {
                    Log.i("TAG: MainActivity", "setFragment: Article")
                    navController.navigate(R.id.nav_uk_article)
                    true
                }

                R.id.nav_Canada_article -> {
                    Log.i("TAG: MainActivity", "setFragment: Article")
                    navController.navigate(R.id.nav_Canada_article)
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