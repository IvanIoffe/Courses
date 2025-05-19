package com.ioffeivan.courses.core.navigation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import java.io.Serializable

fun Fragment.navigate(actionResId: Int, hostId: Int? = null, data: Serializable? = null) {
    val navController = if (hostId == null) {
        findNavController()
    } else {
        requireActivity().findNavController(hostId)
    }

    val bundle = Bundle().apply {
        putSerializable(NAVIGATION_DATA_KEY, data)
    }

    navController.navigate(actionResId, bundle)
}

inline fun <reified T : Serializable> Fragment.getNavigationData(): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arguments?.getSerializable(NAVIGATION_DATA_KEY, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        arguments?.getSerializable(NAVIGATION_DATA_KEY) as T?
    }
}