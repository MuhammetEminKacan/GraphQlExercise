package com.mek.graphqlexercise.domain.usecase

import com.mek.graphqlexercise.domain.model.LaunchDetail
import com.mek.graphqlexercise.domain.repository.LaunchesRepository
import com.mek.graphqlexercise.utils.Resource
import javax.inject.Inject

class GetLaunchDetailUseCase @Inject constructor(
    private val repository: LaunchesRepository
) {
    suspend operator fun invoke(id: String): Resource<LaunchDetail> {
        return repository.getLaunchDetail(id)
    }
}