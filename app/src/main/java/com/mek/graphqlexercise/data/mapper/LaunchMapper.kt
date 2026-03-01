package com.mek.graphqlexercise.data.mapper

import com.mek.graphqlexercise.CompanyQuery
import com.mek.graphqlexercise.LaunchesPastQuery
import com.mek.graphqlexercise.domain.model.Company
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

fun CompanyQuery.Company.toDomain(): Company {
    return Company(
        name = name.orEmpty(),
        ceo = ceo.orEmpty(),
        employees = employees ?: 0,
        founded = founded ?: 0,
        founder = founder.orEmpty(),
        elonTwitter = links?.elon_twitter,
        flickr = links?.flickr,
        twitter = links?.twitter,
        website = links?.website
    )
}
