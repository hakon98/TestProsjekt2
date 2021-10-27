package no.usn.ruud.testprosjekt2.authenticator


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import no.usn.ruud.testprosjekt2.MainActivity
import no.usn.ruud.testprosjekt2.R


class LandingPageLoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lateinit var mAuth: FirebaseAuth

        mAuth = FirebaseAuth.getInstance()
        val application = requireNotNull(this.activity).application
        binding = LandingPageLoginFragment.inflate(
            inflater,
            container, false
        )
        binding.playButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.mainActivity)
        }
        return binding.root


        return inflater.inflate(R.layout.fragment_landing_page_login, container, false)
    }




    btnSignIn.setOnClickListener(View.OnClickListener
    {

        val emailID = email.text.toString()
        val paswd: String = editPassword.text.toString()
        if (emailID.isEmpty()) {
            email.error = "Provide your Email first!"
            email.requestFocus()
        } else if (paswd.isEmpty()) {
            editPassword.error = "your password??"
            editPassword.requestFocus()
        } else if (emailID.isEmpty() && paswd.isEmpty()) {
            Toast.makeText(activity, "Fields Empty!", Toast.LENGTH_SHORT).show()
        } else if (!(emailID.isEmpty() && paswd.isEmpty())) {
            activity?.let { it1 ->
                mAuth.signInWithEmailAndPassword(emailID, paswd)
                    .addOnCompleteListener(it1) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = mAuth.currentUser
                            (activity as MainActivity).updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                activity, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            (activity as MainActivity).updateUI(null)
                        }
                    }
            }
        }
    })
}
}

private fun TextView.setOnClickListener(sendPasswordResetEmail: Task<Void>) {

}
