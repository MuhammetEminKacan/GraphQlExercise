package com.mek.graphqlexercise.domain.model

data class Company(
    val name: String,
    val ceo: String,
    val employees: Int,
    val founded: Int,
    val founder: String,
    val elonTwitter: String?,
    val flickr: String?,
    val twitter: String?,
    val website: String?
)
