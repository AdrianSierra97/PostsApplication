package com.example.postsapplication.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.postsapplication.databinding.FragmentPostBinding
import com.example.postsapplication.databinding.ItemCommentBinding
import com.example.postsapplication.framework.viewModel.PostViewModel
import com.example.postsapplication.presentation.base.BaseFragment
import com.example.postsapplication.presentation.recyclerView.GenericRecyclerViewAdapter
import com.example.postsapplication.presentation.utils.DataSource
import com.example.postsapplication.presentation.utils.UiState
import com.example.postsapplication.presentation.utils.isNotEmpty
import com.example.postsapplication.presentation.utils.mySafeCast
import com.example.postsapplication.presentation.utils.showToast
import data.Comment
import data.Post

class PostFragment : BaseFragment<FragmentPostBinding>() {

    private val viewModel: PostViewModel by lazy {
        provideViewModel()
    }

    private var currentPost = Post(
        userId = 0,
        id = 0,
        title = "",
        body = "",
        updateTime = 0,
        isCreatedLocal = false
    )

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPostBinding = FragmentPostBinding.inflate(inflater, container, false)

    override fun setupView() {
        arguments?.let {
            viewModel.postId = PostFragmentArgs.fromBundle(it).postId
            if (viewModel.postId != 0)
                viewModel.getCurrentPost()
            else
                currentPost.isCreatedLocal = true
        }

        val commentRecyclerViewAdapter =
            GenericRecyclerViewAdapter(
                ArrayList<Comment>(),
                bindingInflater = { inflater, parent, attachToParent ->
                    ItemCommentBinding.inflate(
                        inflater,
                        parent,
                        attachToParent
                    )
                }) { comment, binding ->
                val commentNameTextView: TextView = binding.commentNameTextView
                val commentEmailTextView: TextView = binding.commentEmailTextView
                val commentBodyTextView: TextView = binding.commentBodyTextView
                commentNameTextView.text = comment.name
                commentEmailTextView.text = comment.email
                commentBodyTextView.text = comment.body
            }
        binding.apply {
            floatingActionButton.setOnClickListener {
                savePost(this)
            }
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = commentRecyclerViewAdapter
            }
        }
    }

    override fun setupObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        }

        viewModel.saved.observe(viewLifecycleOwner) {
            navigateWith(binding.root) {
                popBackStack()
            }
        }
        viewModel.currentPost.observe(viewLifecycleOwner) { post ->
            post?.let {
                currentPost = it
                binding.apply {
                    titleEditText.setText(it.title)
                    bodyEditText.setText(it.body)
                }
            }

            if (post?.isCreatedLocal == false)
                viewModel.loadData()

        }
    }

    private fun render(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                onLoad(uiState)
            }

            is UiState.Success -> {
                onSuccess(uiState)
            }

            is UiState.Error -> {
                onError(uiState)
            }
        }
    }

    private fun onLoad(loadingState: UiState.Loading) = with(binding) {
        when (loadingState) {
            UiState.Loading.LoadFromDb -> {
                progressBar.visibility = View.VISIBLE
                showToast("Loading from Database", Toast.LENGTH_SHORT)
            }

            UiState.Loading.LoadFromNetwork -> {
                progressBar.visibility = View.VISIBLE
                showToast("Loading from Network", Toast.LENGTH_SHORT)
            }
        }
    }

    private fun onSuccess(uiState: UiState.Success) = with(binding) {
        when (uiState.dataSource) {
            DataSource.Network -> {
                progressBar.visibility = View.GONE
                showToast("Network Request Success", Toast.LENGTH_SHORT)
            }

            DataSource.Database -> {
                progressBar.visibility = View.GONE
                showToast("Database Request Success", Toast.LENGTH_SHORT)
                mySafeCast<List<Post>>(uiState.latestData)?.let { loadRecyclerViewData(it) }
            }
        }

    }

    private fun loadRecyclerViewData(posts: List<Post>) {
        binding.recyclerView.adapter?.let {
            mySafeCast<GenericRecyclerViewAdapter<Post, ViewBinding>>(
                it
            )?.updateItems(posts)
        }
    }

    private fun onError(uiState: UiState.Error) = with(binding) {
                progressBar.visibility = View.GONE
                showToast(uiState.message, Toast.LENGTH_SHORT)
    }

    private fun savePost(binding: FragmentPostBinding) {
        binding.apply {
            if (titleEditText.isNotEmpty() || bodyEditText.isNotEmpty()) {
                currentPost.title = titleEditText.text.toString()
                currentPost.body = bodyEditText.text.toString()
                val time = System.currentTimeMillis()
                currentPost.updateTime = time
                viewModel.savePost(currentPost)
            } else
                navigateWith(binding.root) {
                    popBackStack()
                }
        }
    }
}
