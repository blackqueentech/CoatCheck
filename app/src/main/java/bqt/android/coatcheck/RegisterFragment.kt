package bqt.android.coatcheck

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

/**
 *
 */
class RegisterFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        var etEmail = view.findViewById<EditText>(R.id.etEmail)
        var etPassword = view.findViewById<EditText>(R.id.etPassword)
        var btnRegister = view.findViewById<Button>(R.id.register_button)
        btnRegister.setOnClickListener {view ->
            registerUser(view, etEmail.text.toString(), etPassword.text.toString())
        }
    }

    private fun registerUser(view: View, email: String, password: String) {
       // auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener()

//            .addOnCompleteListener(context, new OnComp) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    val user = auth.currentUser
//                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Toast.makeText(baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
//                    updateUI(null)
//                }
//
//                // ...
//            }
    }

}
