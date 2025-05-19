package com.ioffeivan.courses.feature.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ioffeivan.courses.core.navigation.navigate
import com.ioffeivan.courses.feature.auth.login.R
import com.ioffeivan.courses.feature.auth.login.databinding.FragmentLoginBinding
import com.ioffeivan.courses.feature.login.domain.model.User
import com.ioffeivan.courses.feature.login.presentation.utils.CyrillicInputFilter
import com.ioffeivan.courses.feature.login.presentation.utils.EmailValidator
import com.ioffeivan.courses.feature.login.presentation.utils.ExternalLinks
import com.ioffeivan.courses.feature.login.presentation.utils.openUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFiltersEmailEditText()

        binding.editTextEmail.doAfterTextChanged {
            updateLoginButtonEnableState()
        }

        binding.editTextPassword.doAfterTextChanged {
            updateLoginButtonEnableState()
        }

        binding.buttonLogin.setOnClickListener {
            loginViewModel.saveUser(User(binding.editTextEmail.text.toString()))
            navigate(actionResId = R.id.action_loginFragment_to_mainFragment)
        }

        binding.imageViewOpenVK.setOnClickListener {
            requireContext().openUrl(ExternalLinks.VK)
        }

        binding.imageViewOpenOK.setOnClickListener {
            requireContext().openUrl(ExternalLinks.OK)
        }
    }

    private fun setupFiltersEmailEditText() {
        binding.editTextEmail.filters = arrayOf(CyrillicInputFilter)
    }

    private fun updateLoginButtonEnableState() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        binding.buttonLogin.isEnabled = EmailValidator.isValid(email) && password.isNotBlank()
    }
}