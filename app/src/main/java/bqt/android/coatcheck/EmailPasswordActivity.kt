package bqt.android.coatcheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class EmailPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_password)

        auth = FirebaseAuth.getInstance()
        var etEmail = findViewById<EditText>(R.id.etEmail)
        var etPassword = findViewById<EditText>(R.id.etPassword)
        var btnSignIn = findViewById<Button>(R.id.sign_in_button)
        btnSignIn.setOnClickListener {view ->
            signIn(view, etEmail.text.toString(), etPassword.text.toString())
        }
    }

    private fun signIn (view: View, email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if (task.isSuccessful) {
                goToCloset()
            } else {
                showMessage(view, "Error: ${task.exception?.message}")
            }
        })
    }

    private fun showMessage(view:View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            goToCloset()
        }
    }

    private fun goToCloset() {
        var intent = Intent(this, ClosetActivity::class.java)
        intent.putExtra("id", auth.currentUser?.email)
        startActivity(intent)
    }
}
