package com.ioffeivan.courses.feature.favourites.presentation

import android.os.Bundle
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
import com.ioffeivan.courses.feature.favourites.databinding.FragmentFavouritesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouritesFragment : Fragment(), CoursesAdapter.Listener {
    private lateinit var binding: FragmentFavouritesBinding

    private val favouritesViewModel: FavouritesViewModel by viewModels()

    private var coursesAdapter: CoursesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViewCourses()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                favouritesViewModel.uiState.collect {
                    when (it) {
                        is FavouritesUiState.Content -> {
                            coursesAdapter?.submitList(it.courses.items)

                            binding.favouritesFragmentContent.visibility = View.VISIBLE
                            binding.favouritesFragmentEmptyContent.visibility = View.INVISIBLE
                        }

                        is FavouritesUiState.Error -> {}

                        FavouritesUiState.Loading -> {}

                        FavouritesUiState.EmptyContent -> {
                            binding.favouritesFragmentContent.visibility = View.INVISIBLE
                            binding.favouritesFragmentEmptyContent.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    override fun onClick(course: Course) {
        favouritesViewModel.deleteCourseFromFavourites(course)
    }

    private fun setupRecyclerViewCourses() {
        coursesAdapter = CoursesAdapter(this)
        binding.recyclerViewFavouriteCourses.adapter = coursesAdapter
    }
}