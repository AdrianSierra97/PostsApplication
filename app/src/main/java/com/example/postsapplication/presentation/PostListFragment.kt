package com.example.postsapplication.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postsapplication.R
import com.example.postsapplication.databinding.FragmentPostListBinding
import com.example.postsapplication.framework.viewModel.PostListViewModel
import com.example.postsapplication.presentation.base.BaseFragment
import com.example.postsapplication.presentation.recyclerView.GenericRecyclerViewAdapter
import com.example.postsapplication.presentation.utils.mySafeCast
import data.Post
import java.text.SimpleDateFormat
import java.util.Date

class PostListFragment : BaseFragment<FragmentPostListBinding>() {

    private val viewModel: PostListViewModel by lazy {
        provideViewModel()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPostListBinding = FragmentPostListBinding.inflate(inflater, container, false)

    override fun setupView() {
        binding.floatingActionButton.setOnClickListener {
            goToPostFragment()
        }

        val postRecyclerViewAdapter =
            GenericRecyclerViewAdapter(ArrayList<Post>(), R.layout.item_post) { post, viewHolder ->
                val postMaterialCard: CardView =
                    viewHolder.itemView.findViewById(R.id.postMaterialCard)
                val titleTextView: TextView = viewHolder.itemView.findViewById(R.id.titleTextView)
                val bodyTextView: TextView = viewHolder.itemView.findViewById(R.id.bodyTextView)
                val dateTextView: TextView = viewHolder.itemView.findViewById(R.id.dateTextView)
                titleTextView.text = post.title
                bodyTextView.text = post.body
                val simpleDateFormat = SimpleDateFormat("MMM dd, HH:mm:ss")
                val resultDate = Date(post.updateTime)
                dateTextView.text =
                    "${getString(R.string.last_updated)} ${simpleDateFormat.format(resultDate)}"
                postMaterialCard.setOnClickListener {
                    goToPostFragment(post.id)
                }
            }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = postRecyclerViewAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllPosts()
    }

    override fun setupObservers() {

        viewModel.posts.observe(viewLifecycleOwner) { items ->
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.adapter?.let {
                mySafeCast<GenericRecyclerViewAdapter<Post>>(
                    it
                )?.updateItems(items.sortedByDescending { post -> post.updateTime })
            }
        }
    }

    private fun goToPostFragment(id: Int = 0) {
        navigateWith(binding.root) {
            navigate(PostListFragmentDirections.actionPostListFragmentToPostFragment(id))
        }
    }
}