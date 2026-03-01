package com.mek.graphqlexercise.data.repository

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.mek.graphqlexercise.domain.model.Launch
import com.mek.graphqlexercise.LaunchesPastQuery
import com.mek.graphqlexercise.data.mapper.toDomain
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
}