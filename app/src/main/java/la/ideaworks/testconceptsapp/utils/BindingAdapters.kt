package la.ideaworks.testconceptsapp.utils

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import la.ideaworks.testconceptsapp.adapters.UsersAdapter
import la.ideaworks.testconceptsapp.models.User

/**
 * Este binding adapter carga un listado de usuarios en el recyclerview
 */
@BindingAdapter("app:loadUsersList")
fun bindUsersWithRecyclerView(recyclerView: RecyclerView, usersList: List<User>?) {
    usersList?.let {
        (recyclerView.adapter as UsersAdapter).submitList(usersList)
    }
}

/**
 * Este binding adapter muestra / oculta un progress bar
 */
@BindingAdapter("app:loadingVisibility")
fun bindLoadingVisibilityWithProgressBar(progressBar: ProgressBar, apiStatus: String?) {
    apiStatus?.let {
        when (it) {
            LOADING -> progressBar.visibility = View.VISIBLE
            else -> progressBar.visibility = View.GONE
        }
    }
}

/**
 * Este binding adapter muestra / oculta un mensaje de error, y le agrega el
 * texto que se envia
 */
@BindingAdapter("app:errorMessageVisibility", "app:errorMessageText")
fun bindErrorMessageVisibility(textView: TextView, apiStatus: String?, errorMessage: String?) {
    apiStatus?.let {
        when (it) {
            ERROR -> {
                textView.visibility = View.VISIBLE
                textView.text = errorMessage
            }
            else -> {
                textView.visibility = View.GONE
            }
        }
    }
}