package com.ioffeivan.courses.feature.onboarding.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ioffeivan.courses.core.navigation.navigate
import com.ioffeivan.courses.feature.login.presentation.LoginUiState
import com.ioffeivan.courses.feature.login.presentation.LoginViewModel
import com.ioffeivan.courses.feature.onboarding.R
import com.ioffeivan.courses.feature.onboarding.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.uiState.collect {
                    when (it) {
                        LoginUiState.Loading -> {
                            binding.onboardingFragmentLoading.root.visibility = View.VISIBLE
                            binding.onboardingFragmentContent.visibility = View.INVISIBLE
                        }

                        LoginUiState.Logged -> {
                            navigateToLoginFragment()
                        }

                        LoginUiState.NotLogged -> {
                            binding.onboardingFragmentLoading.root.visibility = View.INVISIBLE
                            binding.onboardingFragmentContent.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

        binding.buttonProceed.setOnClickListener {
            navigateToLoginFragment()
        }
    }

    private fun navigateToLoginFragment() {
        navigate(actionResId = R.id.action_onboardingFragment_to_loginFragment)
    }
}