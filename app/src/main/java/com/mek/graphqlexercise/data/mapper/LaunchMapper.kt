package com.mek.graphqlexercise.data.mapper

import com.mek.graphqlexercise.LaunchesPastQuery
import com.mek.graphqlexercise.domain.model.Launch

fun LaunchesPastQuery.LaunchesPast.toDomain(): Launch {

    val imageUrl =
        links?.mission_patch_small
            ?: links?.mission_patch
            ?: links?.flickr_images?.firstOrNull()

    return Launch(
        id = id ?: "",
        missionName = mission_name.orEmpty(),
        launchDate = launch_date_utc.toString(),
        imageUrl = imageUrl
    )
}
