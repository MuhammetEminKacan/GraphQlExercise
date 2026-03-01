package com.mek.graphqlexercise.domain.model

data class LaunchDetail(
    val id: String,
    val missionName: String,
    val launchDate: String,
    val details: String?,
    val rocketName: String?,
    val rocketType: String?,
    val articleLink: String?,
    val videoLink: String?,
    val imageUrls: List<String>
)
