package com.example.postsapplication.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.postsapplication.R
import com.example.postsapplication.databinding.FragmentPostListBinding
import com.example.postsapplication.databinding.ItemPostBinding
import com.example.postsapplication.framework.viewModel.PostListViewModel
import com.example.postsapplication.presentation.base.BaseFragment
import com.example.postsapplication.presentation.recyclerView.GenericRecyclerViewAdapter
import com.example.postsapplication.presentation.utils.DataSource
import com.example.postsapplication.presentation.utils.UiState
import com.example.postsapplication.presentation.utils.mySafeCast
import com.example.postsapplication.presentation.utils.showToast
import data.Post
import java.text.DateFormat.getDateInstance
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
            GenericRecyclerViewAdapter(
                ArrayList<Post>(),
                bindingInflater = { inflater, parent, attachToParent ->
                    ItemPostBinding.inflate(
                        inflater,
                        parent,
                        attachToParent
                    )
                }) { post, binding ->
                val postMaterialCard: CardView = binding.postMaterialCard
                val titleTextView: TextView = binding.titleTextView
                val dateTextView: TextView = binding.dateTextView
                titleTextView.text = post.title
                val simpleDateFormat = getDateInstance()
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

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadData()
    }

    override fun setupObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        }
    }

    private fun goToPostFragment(id: Int = 0) {
        navigateWith(binding.root) {
            navigate(PostListFragmentDirections.actionPostListFragmentToPostFragment(id))
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
                mySafeCast<List<Post>>(uiState.latestData)?.let { newPosts ->

                    loadRecyclerViewData(newPosts)
                }
                if (uiState.latestData.isNotEmpty())
                    showEmptyListImage(false)
                else
                    showEmptyListImage(true)
            }
        }

    }

    private fun onError(uiState: UiState.Error) = with(binding) {
        when (uiState.dataSource) {
            is DataSource.Network -> {
                progressBar.visibility = View.GONE
                showToast(uiState.message, Toast.LENGTH_SHORT)
            }

            is DataSource.Database -> {
                progressBar.visibility = View.GONE
                showToast(uiState.message, Toast.LENGTH_SHORT)
                if (binding.recyclerView.adapter?.itemCount == 0)
                    showEmptyListImage(true)
                else
                    showEmptyListImage(false)
            }
        }
    }

    private fun loadRecyclerViewData(posts: List<Post>) {
        binding.recyclerView.adapter?.let {
            mySafeCast<GenericRecyclerViewAdapter<Post, ViewBinding>>(
                it
            )?.updateItems(posts.sortedByDescending { post -> post.updateTime })
        }
    }

    private fun showEmptyListImage(show: Boolean) {
        binding.apply {
            if (show) {
                emptyListImageView.visibility = View.VISIBLE
                emptyListTextView.visibility = View.VISIBLE
            } else {
                emptyListImageView.visibility = View.GONE
                emptyListTextView.visibility = View.GONE
            }
        }
    }

}