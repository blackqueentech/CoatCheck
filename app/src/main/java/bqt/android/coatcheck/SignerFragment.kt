package bqt.android.coatcheck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

/**
 *
 */
class SignerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnSignIn)?.setOnClickListener {
            findNavController().navigate(R.id.login_dest, null)
        }

        view.findViewById<Button>(R.id.btnSignUp)?.setOnClickListener {
            findNavController().navigate(R.id.register_dest, null)
        }
    }
}
