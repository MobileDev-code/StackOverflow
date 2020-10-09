package com.example.stackoverflow.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.example.stackoverflow.R
import com.example.stackoverflow.dal.RequestManager
import com.example.stackoverflow.dal.di.ApplicationGraph
import com.example.stackoverflow.dal.di.DaggerApplicationGraph
import com.example.stackoverflow.databinding.FragmentHomeBinding
import com.example.stackoverflow.ui.adapters.QuestionsListAdapter

open class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.questionsRecyclerview.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.questionsRecyclerview.layoutManager = LinearLayoutManager(context)
        // todo: show progress
        this.getQuestions()
    }

    fun getQuestions() {
        if (!RequestManager.isNetworkConnected()) {
            handleNoInternetConnectionError()
            return
        }

        // Create an instance of the application graph
        val applicationGraph: ApplicationGraph = DaggerApplicationGraph.create()
        // Grab an instance of Repository from the application graph
        val repository = applicationGraph.repository()

        val applicationContext = activity!!.applicationContext
        repository.getQuestions(applicationContext, Response.Listener {
            var adapter: QuestionsListAdapter? =
                QuestionsListAdapter(context!!, it)
            binding.questionsRecyclerview.adapter = adapter
            binding.questionsRecyclerview.adapter?.notifyDataSetChanged()

            if (it.items.count() == 0) {
                val networkErrorDialog: AlertDialog.Builder? = activity?.let {
                    AlertDialog.Builder(it)
                }

                networkErrorDialog?.setTitle(getString(R.string.search_error_title))
                networkErrorDialog?.setMessage(getString(R.string.search_error_description))
                networkErrorDialog?.setPositiveButton("OK") { dialog, which ->
                }
                networkErrorDialog?.show()
            }
        }, Response.ErrorListener {
            super.handleNetworkError(it)
        })
    }
}