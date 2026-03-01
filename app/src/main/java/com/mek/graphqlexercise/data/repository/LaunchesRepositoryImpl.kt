package com.mek.graphqlexercise.data.repository

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.mek.graphqlexercise.CompanyQuery
import com.mek.graphqlexercise.LaunchDetailQuery
import com.mek.graphqlexercise.domain.model.Launch
import com.mek.graphqlexercise.LaunchesPastQuery
import com.mek.graphqlexercise.data.mapper.toDomain
import com.mek.graphqlexercise.domain.model.Company
import com.mek.graphqlexercise.domain.model.LaunchDetail
import com.mek.graphqlexercise.domain.repository.LaunchesRepository
import com.mek.graphqlexercise.utils.Resource
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : LaunchesRepository {

    override suspend fun getPastLaunches(): Resource<List<Launch>> {
        return try {
            val response = apolloClient
                .query(LaunchesPastQuery())
                .execute()

            if (response.hasErrors()) {
                Resource.Error(response.errors?.first()?.message ?: "Unknown error")
            } else {
                val launches = response.data?.launchesPast
                    ?.filterNotNull()
                    ?.map { it.toDomain() }
                    ?: emptyList()
                Log.d("GraphQL", "Launch count: ${launches.size}")
                Log.d("GraphQL", "Errors: ${response.errors}")
                Log.d("GraphQL", "Data: ${response.data}")
                Resource.Success(launches)

            }

        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error")
        }
    }

    override suspend fun getLaunchDetail(id: String): Resource<LaunchDetail> {
        return try {
            val response = apolloClient.query(LaunchDetailQuery(id)).execute()
            if (response.hasErrors()) {
                Resource.Error(response.errors?.first()?.message ?: "Unknown error")
            } else {
                val launch = response.data?.launch ?: return Resource.Error("No data")

                val images = launch.links?.flickr_images?.filterNotNull() ?: emptyList()

                val detail = LaunchDetail(
                    id = id,
                    missionName = launch.mission_name.orEmpty(),
                    launchDate = launch.launch_date_utc.toString(),
                    details = launch.details,
                    rocketName = launch.rocket?.rocket_name,
                    rocketType = launch.rocket?.rocket_type,
                    articleLink = launch.links?.article_link,
                    videoLink = launch.links?.video_link,
                    imageUrls = images
                )

                Resource.Success(detail)
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error")
        }
    }

    override suspend fun getCompanyInfo(): Resource<Company> {
        return try {
            val response = apolloClient.query(CompanyQuery()).execute()
            if (response.hasErrors()) {
                Resource.Error(response.errors?.first()?.message ?: "Unknown error")
            } else {
                val company = response.data?.company?.toDomain()
                if (company != null) Resource.Success(company)
                else Resource.Error("No data")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error")
        }
    }
}