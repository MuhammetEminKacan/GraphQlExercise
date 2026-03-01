package com.mek.graphqlexercise.domain.model

data class Launch(
    val id: String,
    val missionName: String,
    val launchDate: String,
    val imageUrl: String?
)
