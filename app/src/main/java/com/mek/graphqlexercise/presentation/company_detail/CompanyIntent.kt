package com.mek.graphqlexercise.presentation.company_detail

sealed class CompanyIntent {
    object LoadCompanyInfo : CompanyIntent()
}