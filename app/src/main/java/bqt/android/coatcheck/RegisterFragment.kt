package bqt.android.coatcheck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 *
 */
class RegisterFragment : Fragment() {

    private val TAG = "RegisterFragment"
    private lateinit var auth: FirebaseAuth

    val db = Firebase.firestore

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
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(),
            OnCompleteListener<AuthResult> { task ->
                if(task.isSuccessful){
                    addNewUserToDatabase(email)
                    goToCloset()
                } else{
                    showMessage(view,"Error: ${task.exception?.message}")
                }
            })
    }

    private fun showMessage(view:View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

    private fun addNewUserToDatabase(email: String) {
        val user = hashMapOf(
            "email" to email,
            "currency" to null,
            "categories" to listOf("Tops", "Bottoms", "Outerwear", "Shoes")
        )

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    private fun goToCloset() {
        var intent = Intent(requireActivity(), ClosetActivity::class.java)
        intent.putExtra("id", auth.currentUser?.email)
        startActivity(intent)
    }
}
