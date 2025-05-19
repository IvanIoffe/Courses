package com.ioffeivan.courses.feature.home.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ioffeivan.courses.core.model.Course
import com.ioffeivan.courses.core.ui.courses.CoursesAdapter
import com.ioffeivan.courses.feature.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), CoursesAdapter.Listener {
    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()

    private var coursesAdapter: CoursesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViewCourses()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.uiState.collect {
                    handleHomeUiState(state = it)
                }
            }
        }

        binding.textViewSortCourses.setOnClickListener {
            homeViewModel.sortCoursesBy(SortType.PUBLISH_DATE)
        }
    }

    override fun onClick(course: Course) {
        if (course.hasLike) {
            homeViewModel.deleteCourseFromFavourites(course)
        } else {
            homeViewModel.addCourseToFavourites(course)
        }
    }

    private fun setupRecyclerViewCourses() {
        coursesAdapter = CoursesAdapter(this)
        binding.recyclerViewCourses.adapter = coursesAdapter
    }

    private fun handleHomeUiState(state: HomeUiState) {
        when (state) {
            is HomeUiState.Content -> {
                coursesAdapter?.submitList(state.courses.items)
                binding.homeFragmentLoading.root.visibility = View.INVISIBLE
                binding.homeFragmentContent.visibility = View.VISIBLE
            }

            is HomeUiState.Error -> {
                binding.homeFragmentLoading.root.visibility = View.INVISIBLE
                binding.homeFragmentError.root.visibility = View.VISIBLE
            }

            HomeUiState.Loading -> {
                binding.homeFragmentContent.visibility = View.INVISIBLE
                binding.homeFragmentLoading.root.visibility = View.VISIBLE
            }

            else -> {}
        }
    }
}