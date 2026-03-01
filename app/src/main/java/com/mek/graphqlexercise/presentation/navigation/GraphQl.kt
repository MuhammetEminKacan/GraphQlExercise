package com.mek.graphqlexercise.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object LaunchList : NavKey

@Serializable
data class LaunchDetail(val launchId: String) : NavKey

@Serializable
data object CompanyNavKey : NavKey