package bqt.android.coatcheck

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.Source
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_settings.*

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {
    private val TAG = "SettingsFragment"
    val db = Firebase.firestore
    val source = Source.CACHE
    val usersTable = db.collection("users")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser
        //auth = FirebaseAuth.getInstance()
        var displayNameEdit: EditText = view.findViewById(R.id.display_name_edit)
        val logoutButton: Button = view.findViewById(R.id.logout_button)
        val saveButton: Button = view.findViewById(R.id.save_button)
        val currencySpinner: Spinner = view.findViewById(R.id.currency_spinner)
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.currency_list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            currencySpinner.adapter = adapter
        }
        val accessoriesCheckBox: CheckBox = view.findViewById(R.id.accessories_check_box)
        val dressesCheckBox: CheckBox = view.findViewById(R.id.dresses_check_box)
        val topsCheckBox: CheckBox = view.findViewById(R.id.tops_check_box)
        val bottomsCheckBox: CheckBox = view.findViewById(R.id.bottoms_check_box)
        val shoesCheckBox: CheckBox = view.findViewById(R.id.shoes_check_box)
        val outerwearCheckBox: CheckBox = view.findViewById(R.id.outerwear_check_box)

        val categories: ArrayList<CheckBox> = arrayListOf(
            accessories_check_box, dresses_check_box,
            tops_check_box, bottoms_check_box, shoes_check_box,
            outerwear_check_box
        )

        logoutButton.setOnClickListener {view ->
            signOut()
        }

        saveButton.setOnClickListener { view ->
            if (user != null) {
                updateUserSettings(user)
            }
        }
    }

    private fun updateUserSettings(user: FirebaseUser?) {
        val email = user?.email
        val userDoc = usersTable.whereEqualTo("email", email)
        val currency = currency_spinner.selectedItem.toString()
        var categories = ArrayList<String>()

        onCheckboxClicked(categories)



        val updatedUserInfoMap = hashMapOf(
            "currency" to currency,
            "categories" to categories
        )
        // update currency
        // update categories
        currency_spinner.onItemSelectedListener

        db.collection("users").document()
            .set(updatedUserInfoMap)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Your data is updated!",
                Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating user", e) }
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }

    private fun onCheckboxClicked(categories: ArrayList<String>) {
        if (tops_check_box .isChecked) {
            categories.add("Tops")
        }
        if (bottoms_check_box .isChecked) {
            categories.add("Bottoms")
        }
        if (outerwear_check_box .isChecked) {
            categories.add("Outerwear")
        }
        if (shoes_check_box .isChecked) {
            categories.add("Shoes")
        }
        if (accessories_check_box .isChecked) {
            categories.add("Accessories")
        }
        if (dresses_check_box .isChecked) {
            categories.add("Dresses")
        }
    }

}
