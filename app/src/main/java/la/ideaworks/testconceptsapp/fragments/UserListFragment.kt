package la.ideaworks.testconceptsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import la.ideaworks.testconceptsapp.adapters.UsersAdapter
import la.ideaworks.testconceptsapp.databinding.FragmentUserListBinding
import la.ideaworks.testconceptsapp.viewmodels.UserListViewModel

class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    private var newUserId = 1

    private val viewModel: UserListViewModel by viewModels {
        UserListViewModel.UsersListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserListBinding.inflate(inflater).apply {
            lifecycleOwner = this@UserListFragment
            viewModel = this@UserListFragment.viewModel
        }

        binding.recyclerViewUsers.adapter = UsersAdapter(getUserCallback())

        binding.buttonSaveUsers.setOnClickListener {
            viewModel.saveLocalUser(userId = newUserId, name = "Ricardo")
            newUserId++
        }

        sendMessageFromFragment()

        return binding.root
    }

    private fun sendMessageFromFragment() {
        Log.i("Users", "Started call from fragment")
        viewModel.sendMessageFromViewModel("Ricardo")

        viewModel.messageFromRepository.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

    /**
     * This is the callback used to communicate UsersAdapter with this fragment
     */
    private fun getUserCallback() = UsersAdapter.OnClickListener { user ->
        this.findNavController().navigate(
            UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(user)
        )
    }
}