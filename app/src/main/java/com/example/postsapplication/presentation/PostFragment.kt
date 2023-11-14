package com.example.postsapplication.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.postsapplication.databinding.FragmentPostBinding
import com.example.postsapplication.framework.viewModel.PostViewModel
import com.example.postsapplication.presentation.base.BaseFragment
import com.example.postsapplication.presentation.utils.isNotEmpty
import data.Post

class PostFragment : BaseFragment<FragmentPostBinding>() {

    private var postId = 0
    private val viewModel: PostViewModel by lazy {
        provideViewModel()
    }

    private var currentPost = Post(
        userId = 0,
        id = 0,
        title = "",
        body = "",
        creationTime = 0,
        updateTime = 0,
        isUploaded = false
    )

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPostBinding = FragmentPostBinding.inflate(inflater, container, false)

    override fun setupView() {
        binding.apply {
            floatingActionButton.setOnClickListener {
                savePost(binding)
            }
        }
        arguments?.let {
            postId = PostFragmentArgs.fromBundle(it).postId
        }

        if(postId != 0)
            viewModel.getPost(postId)
    }

    override fun setupObservers() {
        viewModel.saved.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "Post saved", Toast.LENGTH_SHORT).show()
                navigateWith(binding.root) {
                    popBackStack()
                }
            } else Toast.makeText(requireContext(), "Post not saved", Toast.LENGTH_SHORT).show()
        }
        viewModel.currentPost.observe(viewLifecycleOwner) {post ->
            post?.let {
                currentPost = it
                binding.apply {
                    titleEditText.setText(it.title)
                    bodyEditText.setText(it.body)
                }
            }
        }
    }

    private fun savePost(binding: FragmentPostBinding) {
        binding.apply {
            if (titleEditText.isNotEmpty() || bodyEditText.isNotEmpty()) {
                val time: Long = System.currentTimeMillis()
                currentPost.title = titleEditText.text.toString()
                currentPost.body = bodyEditText.text.toString()
                currentPost.updateTime = time
                if (currentPost.id == 0)
                    currentPost.creationTime = time
                viewModel.savePost(currentPost)
            } else
                navigateWith(binding.root) {
                    popBackStack()
                }
        }
    }
}
