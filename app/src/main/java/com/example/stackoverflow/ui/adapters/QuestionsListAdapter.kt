package com.example.stackoverflow.ui.adapters

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.stackoverflow.R
import com.example.stackoverflow.dal.models.Question
import com.example.stackoverflow.dal.models.Questions
import com.example.stackoverflow.databinding.QuestionsListItemBinding

class QuestionsListAdapter (private val context: Context,
                            private val dataSource: Questions) : RecyclerView.Adapter<QuestionsListAdapter.ViewHolder>() {
    lateinit var binding: QuestionsListItemBinding

    override fun getItemCount(): Int {
        return dataSource.items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        this.binding = DataBindingUtil.inflate(layoutInflater, R.layout.questions_list_item, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = dataSource.items[position]
        holder.bind(this, item)
    }

    class ViewHolder(val binding: QuestionsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listAdapter: QuestionsListAdapter, item: Question) {
            binding.apply {
                binding.question = item
                this.bodyTextView.text = Html.fromHtml(item.body)
                val context = binding.root.context
                this.answerCountTextView.text = String.format("%s: %s", context.getString(R.string.answer_count), item.answerCount)
                val acceptedAnswer = String.format("%s: %s", context.getString(R.string.accepted_answer), if (item.acceptedAnswerID != null) "true" else "false")
                this.acceptedAnswerTextView.text = acceptedAnswer
            }
        }
    }
}