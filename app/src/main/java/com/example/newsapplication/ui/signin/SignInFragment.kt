package com.example.newsapplication.ui.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.newsapplication.R
import com.example.newsapplication.ui.article.ArticleActivity
import com.example.newsapplication.databinding.FragmentSignInBinding
import com.example.newsapplication.ui.MainActivity
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    lateinit var auth: FirebaseAuth
//    private var oneTapClient: SignInClient? = null
//    private lateinit var signInRequest: BeginSignInRequest
    private var emailValidate = false
    private var passwordValidate = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        auth = FirebaseAuth.getInstance()

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonToSubmit.setOnClickListener {
            if (validateEntries())
                isVerifiedUser()
        }

//        googleSignIn()
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    private fun updateUI(user: FirebaseUser?) {
        user.let {
            goToNextActivity()
        }
    }




//    private fun googleSignIn() {
//        oneTapClient = Identity.getSignInClient(requireActivity())
//        signInRequest = BeginSignInRequest.builder()
//            .setGoogleIdTokenRequestOptions(
//                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                    .setSupported(true)
//                    // Your server's client ID, not your Android client ID.
//                    .setServerClientId(getString(R.string.default_web_client_id))
//                    // Only show accounts previously used to sign in.
//                    .setFilterByAuthorizedAccounts(false)
//                    .build())
//            .build()
//    }

    private fun isVerifiedUser() {
        auth.signInWithEmailAndPassword(
            binding.textInputEditTextUserName.text.toString(),
            binding.textInputEditTextPassword.text.toString()
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("LoginActivity", "loginUserWithEmail:success")
                val user = auth.currentUser
                goToNextActivity()
            } else {
                // If sign in fails, display a message to the user.
                Log.w("LoginActivity", "createUserWithEmail:failure", task.exception)
                Toast.makeText(
                    requireContext(),
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
    }

    private fun goToNextActivity() {
        startActivity(Intent(requireActivity(), ArticleActivity::class.java))
            .also { requireActivity().finish() }
    }



//        binding.buttonTopSignUp.setOnClickListener {
//            requireActivity().supportFragmentManager.commit {
//                replace(R.id.fragment_container, SignUpFragment())
//                addToBackStack(null)
//            }
//        }


    private fun validateEntries(): Boolean {
        val submittedEmail: String = binding.textInputEditTextUserName.text.toString()
        val submittedPassword: String = binding.textInputEditTextPassword.text.toString()

        emailValidate = submittedEmail.contains('@')
                && submittedEmail.substringAfter('@').contains('.')
        passwordValidate = submittedPassword.trim().length >= 6

        return emailValidate && passwordValidate

    }
}