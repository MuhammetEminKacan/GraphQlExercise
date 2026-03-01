package com.mek.graphqlexercise.domain.usecase

import com.mek.graphqlexercise.domain.model.Launch
import com.mek.graphqlexercise.domain.repository.LaunchesRepository
import com.mek.graphqlexercise.utils.Resource
import javax.inject.Inject

class GetPastLaunchesUseCase @Inject constructor(
    private val repository: LaunchesRepository
) {

    suspend operator fun invoke(): Resource<List<Launch>> {
        return repository.getPastLaunches()
    }
}