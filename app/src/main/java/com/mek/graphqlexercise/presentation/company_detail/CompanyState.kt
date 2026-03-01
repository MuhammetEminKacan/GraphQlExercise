package com.mek.graphqlexercise.presentation.company_detail

import com.mek.graphqlexercise.domain.model.Company

data class CompanyState(
    val isLoading: Boolean = false,
    val company: Company? = null,
    val error: String? = null
)
