package la.ideaworks.testconceptsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import la.ideaworks.testconceptsapp.databinding.FragmentUserListBinding

class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater)


        binding.recyclerViewUsers.adapter = UsersAdapter(getUserCallback())

        val usersList = arrayListOf(
            User(0, "Otto", 23, "Jefe"),
            User(1, "Ricardo", 23, "Developer"),
            User(2, "Gaby", 23, "UI/UX"),
            User(3, "Zayda", 23, "PM"),
            User(4, "Oscar", 23, "Developer"),
            User(5, "Enrique", 23, "Developer")
        )

        (binding.recyclerViewUsers.adapter as UsersAdapter).submitList(usersList)

        return binding.root
    }

    /**
     * This is the callback used to communicate UsersAdapter with this fragment
     */
    private fun getUserCallback() = UsersAdapter.OnClickListener { user ->

        Log.i("MainActivity", "Selected user ${user.name}")
        Toast.makeText(context, "Selected user ${user.name}", Toast.LENGTH_LONG).show()

        this.findNavController().navigate(
            UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(user)
        )
    }
}