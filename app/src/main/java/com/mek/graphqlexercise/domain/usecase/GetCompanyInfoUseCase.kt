package com.mek.graphqlexercise.domain.usecase

import com.mek.graphqlexercise.domain.model.Company
import com.mek.graphqlexercise.domain.repository.LaunchesRepository
import com.mek.graphqlexercise.utils.Resource
import javax.inject.Inject

class GetCompanyInfoUseCase @Inject constructor(
    private val repository: LaunchesRepository
) {
    suspend operator fun invoke(): Resource<Company> = repository.getCompanyInfo()
}