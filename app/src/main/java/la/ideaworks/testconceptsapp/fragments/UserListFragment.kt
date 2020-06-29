package la.ideaworks.testconceptsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import la.ideaworks.testconceptsapp.adapters.UsersAdapter
import la.ideaworks.testconceptsapp.databinding.FragmentUserListBinding
import la.ideaworks.testconceptsapp.utils.ERROR
import la.ideaworks.testconceptsapp.utils.LOADING
import la.ideaworks.testconceptsapp.utils.SUCCESS
import la.ideaworks.testconceptsapp.viewmodels.UserListViewModel

class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    private val viewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater)


        binding.recyclerViewUsers.adapter = UsersAdapter(getUserCallback())

        viewModel.usersList.observe(viewLifecycleOwner, Observer {
            it?.let { usersList ->
                (binding.recyclerViewUsers.adapter as UsersAdapter).submitList(usersList)
            }
        })

        viewModel.apiStatus.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    LOADING -> binding.progressBarUsersList.visibility = View.VISIBLE
                    SUCCESS -> {
                        binding.progressBarUsersList.visibility = View.GONE
                    }
                    ERROR -> {
                        binding.progressBarUsersList.visibility = View.GONE
                        binding.textErrorMessage.visibility = View.VISIBLE
                    }
                }
            }
        })

        viewModel.usersListErrorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.textErrorMessage.text = it
            }
        })

        return binding.root
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