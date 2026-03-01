package com.mek.graphqlexercise.presentation.launch_detail

sealed class LaunchDetailIntent {
    data class LoadLaunchDetail(val launchId: String) : LaunchDetailIntent()
}