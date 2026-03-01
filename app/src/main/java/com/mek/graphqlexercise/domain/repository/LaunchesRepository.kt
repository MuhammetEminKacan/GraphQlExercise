package com.mek.graphqlexercise.domain.repository

import com.mek.graphqlexercise.domain.model.Company
import com.mek.graphqlexercise.domain.model.Launch
import com.mek.graphqlexercise.domain.model.LaunchDetail
import com.mek.graphqlexercise.utils.Resource

interface LaunchesRepository {
    suspend fun getPastLaunches(): Resource<List<Launch>>
    suspend fun getLaunchDetail(id: String): Resource<LaunchDetail>
    suspend fun getCompanyInfo(): Resource<Company>
}