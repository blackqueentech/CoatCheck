package bqt.android.coatcheck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs


/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val safeArgs: CategoryFragmentArgs by navArgs()
        val closetCategory = safeArgs.closetCategory

        return when (closetCategory) {
            "Bottoms" -> inflater.inflate(R.layout.bottoms_category_fragment, container, false)
            else -> inflater.inflate(R.layout.tops_category_fragment, container, false)
        }
    }
}
