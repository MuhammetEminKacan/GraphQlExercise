package com.mek.graphqlexercise.presentation.launch_detail

import com.mek.graphqlexercise.domain.model.LaunchDetail

data class LaunchDetailState(
    val isLoading: Boolean = false,
    val launch: LaunchDetail? = null,
    val error: String? = null
)