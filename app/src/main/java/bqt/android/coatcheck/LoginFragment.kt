package bqt.android.coatcheck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.NonNull
import androidx.navigation.Navigation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/**
 *
 */
class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        var etEmail = view.findViewById<EditText>(R.id.etEmail)
        var etPassword = view.findViewById<EditText>(R.id.etPassword)
        var btnLogin = view.findViewById<Button>(R.id.login_button)
        btnLogin.setOnClickListener {view ->
            signIn(view, etEmail.text.toString(), etPassword.text.toString())
        }
    }

    private fun signIn (view: View, email: String, password: String) {
//        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull (Task<AuthResult>) task) {
//                if (task.isSuccessful) {
//                goToCloset()
//            } else {
//                showMessage(view, "Error: ${task.exception?.message}")
//            }
//        }}
    }

    private fun showMessage(view:View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            goToCloset()
        }
    }

    private fun goToCloset() {
        Navigation.createNavigateOnClickListener(R.id.closet_dest)
    }
}

