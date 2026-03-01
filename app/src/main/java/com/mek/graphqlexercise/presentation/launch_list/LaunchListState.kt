package com.mek.graphqlexercise.presentation.launch_list

import com.mek.graphqlexercise.domain.model.Launch

data class LaunchListState(
    val isLoading: Boolean = false,
    val launches: List<Launch> = emptyList(),
    val error: String? = null
)