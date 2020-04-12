package bqt.android.coatcheck

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 *
 */
class ClosetFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_closet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO implement a list-view of categories associated with the user
        // for each in list view
//        val categoryTv = view.findViewById<TextView>(R.id.tops_option)
//        categoryTv.setOnClickListener {
//            val closetCategoryArg = "Tops"
//            val action = ClosetFragmentDirections.nextAction(closetCategoryArg)
//            findNavController().navigate(action)
//        }

        view.findViewById<TextView>(R.id.tops_option)?.setOnClickListener {
            findNavController().navigate(R.id.next_action, null)
        }

        view.findViewById<TextView>(R.id.bottoms_option)?.setOnClickListener {
            findNavController().navigate(R.id.bottoms_category_dest, null)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

}
