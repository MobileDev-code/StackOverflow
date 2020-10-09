package com.example.stackoverflow.ui.home

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.android.volley.VolleyError
import com.example.stackoverflow.R

open class BaseFragment : Fragment() {
    fun handleNetworkError(volleyError: VolleyError) {
        val networkErrorDialog: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        networkErrorDialog?.setTitle(getString(R.string.network_error_title))
        networkErrorDialog?.setMessage(getString(R.string.network_error_description))
        networkErrorDialog?.setPositiveButton("OK") { dialog, which ->
        }
        networkErrorDialog?.show()
        println(volleyError.message)
    }

    fun handleNoInternetConnectionError() {
        val networkErrorDialog: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        networkErrorDialog?.setTitle(getString(R.string.network_error_title))
        networkErrorDialog?.setMessage(getString(R.string.no_internet_connection))
        networkErrorDialog?.setPositiveButton("OK") { dialog, which ->
        }
        networkErrorDialog?.show()
    }
}