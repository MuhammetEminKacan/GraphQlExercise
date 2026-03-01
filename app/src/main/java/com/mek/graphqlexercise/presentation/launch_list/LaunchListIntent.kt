package com.mek.graphqlexercise.presentation.launch_list

sealed class LaunchListIntent {
    object LoadLaunches : LaunchListIntent()
}